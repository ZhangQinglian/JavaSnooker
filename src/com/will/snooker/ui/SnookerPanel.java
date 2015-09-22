package com.will.snooker.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import com.will.snooker.config.GameConfig;
import com.will.snooker.contoller.GameController;
import com.will.snooker.entity.Ball;
import com.will.snooker.entity.Ball.BallType;
import com.will.snooker.entity.BallLocation;
import com.will.snooker.entity.Speed;
import com.will.snooker.res.ImageRes;
import com.will.snooker.server.SnookerServer;
/**
 * 游戏面板类
 * @author qinglian
 *
 */
public class SnookerPanel extends JPanel implements Runnable, ActionListener,
        MouseMotionListener, MouseInputListener {

    private static final long serialVersionUID = 3080988635417598226L;
    private JButton mStartBtn;
    private JButton mExitBtn;
    private JButton mApplyBtn;
    private GameController mGameController;
    private JButton mSoundBtn;
    private JButton mBgmBtn;

    private String mSoundOnStr = "音效：开";
    private String mSoundOffStr = "音效：关";
    private String mBgmOnStr = "背景音乐：开";
    private String mBgmOffStr = "背景音乐：关";

    public static final float C = 400.0f;

    public static final float A = 10000.0f;

    private int delta = 1000;

    int fps = 50;

    private Ball mWhiteBall;

    private List<Ball> mBalls;

    private GameThread mGameThread;

    private GamePaintThread mGamePaintThread;

    private Point mCurrentMouseLocation;

    Stroke dash = new BasicStroke(1f, BasicStroke.CAP_ROUND,
            BasicStroke.JOIN_ROUND, 1f, new float[] { 5, 5, }, 10f);

    public SnookerPanel(GameController gameController) {
        setmGameController(gameController);
        setLayout(null);
        setSize(GameConfig.CONFIG_PANEL_WIDTH, GameConfig.CONFIG_PANEL_HEIGHT);
        setLocation(0 - delta, 0);
        setBackground(Color.WHITE);
        initView();
        setCursor(ImageRes.normal_cursor);
        setVisible(true);
        addMouseMotionListener(this);
        addMouseListener(this);
        new Thread(this).start();

    }

    private void initView() {
        mStartBtn = new JButton("开始游戏");
        mExitBtn = new JButton("退出");
        mApplyBtn = new JButton("应用设置");
        mSoundBtn = new JButton(mSoundOnStr);
        mBgmBtn = new JButton(mBgmOnStr);

        mStartBtn.setBounds(900, 620, 100, 40);
        mExitBtn.setBounds(770, 620, 100, 40);
        mApplyBtn.setBounds(640, 620, 100, 40);
        mBgmBtn.setBounds(490, 620, 120, 40);
        mSoundBtn.setBounds(360, 620, 100, 40);

        mStartBtn.addActionListener(this);
        mExitBtn.addActionListener(this);
        mApplyBtn.addActionListener(this);
        mSoundBtn.addActionListener(this);
        mBgmBtn.addActionListener(this);

        add(mExitBtn);
        add(mStartBtn);
        add(mApplyBtn);
        add(mSoundBtn);
        add(mBgmBtn);
    }

    private void initEntity() {
        mWhiteBall = new Ball(BallType.white);
        mBalls = new ArrayList<Ball>();
        mBalls.add(mWhiteBall);
        mBalls.add(new Ball(BallType.black));
        mBalls.add(new Ball(BallType.blue));
        mBalls.add(new Ball(BallType.coffe));
        mBalls.add(new Ball(BallType.green));
        mBalls.add(new Ball(BallType.pink));
        mBalls.add(new Ball(BallType.yellow));
        mBalls.add(new Ball(BallType.red, 1));
        mBalls.add(new Ball(BallType.red, 2));
        mBalls.add(new Ball(BallType.red, 3));
        mBalls.add(new Ball(BallType.red, 4));
        mBalls.add(new Ball(BallType.red, 5));
        mBalls.add(new Ball(BallType.red, 6));
        mBalls.add(new Ball(BallType.red, 7));
        mBalls.add(new Ball(BallType.red, 8));
        mBalls.add(new Ball(BallType.red, 9));
        mBalls.add(new Ball(BallType.red, 10));
        mBalls.add(new Ball(BallType.red, 11));
        mBalls.add(new Ball(BallType.red, 12));
        mBalls.add(new Ball(BallType.red, 13));
        mBalls.add(new Ball(BallType.red, 14));
        mBalls.add(new Ball(BallType.red, 15));
    }


    @Override
    public void run() {
        int d = 1;
        int D = 0;
        while (true) {
            if (!GameConfig.isGameStart) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                D += d;
                setLocation(getLocation().x + d, getLocation().y);
                if (D == delta) {
                    break;
                }
            }
        }

    }

    private void freshBgmBtnText() {
        if (GameConfig.isBGM)
            mBgmBtn.setText(mBgmOnStr);
        else
            mBgmBtn.setText(mBgmOffStr);
    }

    private void freshSoundBtnText() {
        if (GameConfig.isSound)
            mSoundBtn.setText(mSoundOnStr);
        else
            mSoundBtn.setText(mSoundOffStr);
    }

    /**
     * 游戏线程，用来计算小球的位置
     * @author qinglian
     *
     */
    private class GameThread extends Thread {

        @Override
        public void run() {
            while (GameConfig.isGameStart) {
                long t1 = System.currentTimeMillis();
                calcBallLocatopn();
                long t2 = System.currentTimeMillis();
                try {
                    if (t2 - t1 < 10)
                        Thread.sleep(10 - (t2 - t1));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 游戏画图线程，用来不断绘制游戏界面
     * @author qinglian
     *
     */
    private class GamePaintThread extends Thread {
        @Override
        public void run() {
            while (GameConfig.isGameStart) {
                repaint();
            }
        }
    }

    /**
     * 计算小球下一时刻的位置
     */
    private void calcBallLocatopn() {

        for (int i = 0; i < mBalls.size(); i++) {
            for (int j = i + 1; j < mBalls.size(); j++) {
                if (mBalls.get(i).ismMove() || mBalls.get(j).ismMove())
                    SnookerServer.impact(mBalls.get(i), mBalls.get(j));
            }
        }
        for (Ball ball : mBalls)
            if (ball.ismMove()) {
                ball.move(10);
                if(SnookerServer.isInHole(ball)){
                    ball.setmMove(false);
                    ball.setmShow(false);
                    ball.setmSpeed(new Speed(0, 0));
                    ball.setmLocation(new BallLocation(100000, 1000000));
                }
            }
        if (allStop()) {
            GameConfig.isReady = false;
        }
    }


    /**
     * 是否所有的小球都停止
     * @return
     */
    private boolean allStop() {
        boolean flag = true;
        for (Ball ball : mBalls)
            if (ball.ismMove()) {
                flag = false;
                return flag;
            }
        return flag;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(ImageRes.desktopImage, 0, 0, null);
        if (GameConfig.isGameStart) {
            for (Ball ball : mBalls) {
                if (ball.ismShow())
                    ball.drawSelf(g);
            }
        }
        if (GameConfig.isGameStart && GameConfig.isPutWhiteBall
                && !GameConfig.isReady) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setStroke(dash);
            g2d.setColor(Color.WHITE);
            g2d.drawLine(mWhiteBall.getmPoint().x, mWhiteBall.getmPoint().y,
                    mCurrentMouseLocation.x + 14, mCurrentMouseLocation.y + 14);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mApplyBtn) {
            System.out.println("apply");
            return;
        }
        if (e.getSource() == mBgmBtn) {
            GameConfig.isBGM = !GameConfig.isBGM;
            freshBgmBtnText();
            return;
        }
        if (e.getSource() == mSoundBtn) {
            GameConfig.isSound = !GameConfig.isSound;
            freshSoundBtnText();
            return;
        }
        if (e.getSource() == mExitBtn) {
            int result = JOptionPane.showConfirmDialog(this, "确定要退出游戏？", "提示",
                    JOptionPane.YES_NO_OPTION);
            if (result == 0) {
                System.exit(1);
            }
            return;
        }
        if (e.getSource() == mStartBtn) {
            int result = JOptionPane.showConfirmDialog(this, "确定要开始游戏？", "提示",
                    JOptionPane.YES_NO_OPTION);
            if (result == 0) {
                if (!GameConfig.isGameStart)
                    startGame();
                else{
                    restartGame();
                }
            }
            return;
        }

    }

    /**
     * 开始游戏
     */
    private void startGame() {
        initEntity();
        GameConfig.isGameStart = true;
        GameConfig.isPutWhiteBall = false;
        GameConfig.isReady = false;
        if (GameConfig.isGameStart && !GameConfig.isPutWhiteBall) {
            setCursor(ImageRes.hand_cursor);
        }
        mGameThread = new GameThread();
        mGameThread.start();
        mGamePaintThread = new GamePaintThread();
        mGamePaintThread.start();
    }

    /**
     * 重新开始游戏
     */
    private void restartGame() {
        GameConfig.isGameStart = true;
        GameConfig.isPutWhiteBall = false;
        GameConfig.isReady = false;
        if (GameConfig.isGameStart && !GameConfig.isPutWhiteBall) {
            setCursor(ImageRes.hand_cursor);
        }
        for (Ball ball : mBalls) {
            ball.setmMove(false);
            ball.resetLocation();
            ball.setmShow(true);
            ball.setmSpeed(new Speed(0, 0));
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

        mCurrentMouseLocation = e.getPoint();
        if (GameConfig.isGameStart && !GameConfig.isPutWhiteBall) {
            if (isImpact() || !canPutWhiteBall(SnookerServer.transformBallPoint(e.getPoint()))) {
                if (getCursor().getName() != GameConfig.HANDF_CURSOR_NAME)
                    setCursor(ImageRes.handf_cursor);
                mWhiteBall.setmLocation(SnookerServer.transformBallPoint(e
                        .getPoint()));
            } else {
                if (getCursor().getName() != GameConfig.HAND_CURSOR_NAME) {
                    setCursor(ImageRes.hand_cursor);
                }
                mWhiteBall.setmLocation(SnookerServer.transformBallPoint(e
                        .getPoint()));
            }
        }
        if (GameConfig.isGameStart && GameConfig.isPutWhiteBall) {
            if (getCursor().getName() != GameConfig.CROSS_CURSOR_NAME)
                setCursor(ImageRes.cross_cursor);

        }

    }

    /**
     * 判断当前位置是否可以摆放母球
     * @param location
     * @return
     */
    private boolean canPutWhiteBall(BallLocation location) {

        if (location.getmX() > 780
                || Ball.getDistance(location, GameConfig.COFFE_BALL_LOCATION) > GameConfig.AREA_D_DIAMETER / 2)
            return false;
        return true;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
        mCurrentMouseLocation = e.getPoint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println(e.getPoint().toString());
        // TODO Auto-generated method stub
        if (GameConfig.isGameStart && !GameConfig.isPutWhiteBall) {
            if(getCursor().getName() == GameConfig.HANDF_CURSOR_NAME)
                return ;
            if (getCursor().getName() == GameConfig.HAND_CURSOR_NAME)
                GameConfig.isPutWhiteBall = true;
            setCursor(ImageRes.cross_cursor);
            return;
        }
        if (GameConfig.isGameStart && GameConfig.isPutWhiteBall
                && !GameConfig.isReady) {
            GameConfig.isReady = true;
            mWhiteBall.setmSpeed(Speed.getOrigin(mWhiteBall.getmLocation(),
                    SnookerServer.transformBallPoint(getTrueLocation(e
                            .getPoint())), GameConfig.V));

        }

    }
    
    /**
     * 判断母球是否和其他球有碰撞，在白球是用到
     * @return
     */
    private boolean isImpact(){
        for(int i = 1 ;i<mBalls.size();i++){
            if(SnookerServer.isImpact(mWhiteBall, mBalls.get(i)))
                return true ;
        }
        return false ;
    }

    private Point getTrueLocation(Point point) {
        return new Point(point.x + 15, point.y + 15);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    public List<Ball> getmBalls() {
        return mBalls;
    }

    public void setmBalls(List<Ball> mColorBalls) {
        this.mBalls = mColorBalls;
    }

    public GameController getmGameController() {
        return mGameController;
    }

    public void setmGameController(GameController mGameController) {
        this.mGameController = mGameController;
    }
}
