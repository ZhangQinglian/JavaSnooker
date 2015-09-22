package com.will.snooker.entity;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import com.will.snooker.config.GameConfig;
import com.will.snooker.res.ImageRes;
import com.will.snooker.server.SnookerServer;

/**
 * 游戏中的球基类
 * 
 * @author qinglian
 *
 */
public class Ball {
    public enum BallType {
        white, yellow, green, coffe, blue, pink, black, red

    }

    private BallType mtype;

    private Speed mSpeed;

    private BallLocation mLocation;

    private Point mPoint;

    private BufferedImage mMap;

    private int mScore;

    private boolean mShow = true;

    private boolean mMove = false;

    private double mDelta = 7D;

    private BallLocation mOriginLocation;

    /**
     * 球类的构造函数，根据不同的球设置不同的位置属性
     * @param type
     */
    public Ball(BallType type) {
        mtype = type;
        switch (type) {
        case white:
            setmMap(ImageRes.whiteBall);
            setmLocation(GameConfig.WHITE_BALL_LOCATION);
            setmScore(mScore);
            break;
        case black:
            setmMap(ImageRes.blackBall);
            setmLocation(GameConfig.BLACK_BALL_LOCATION);
            setmScore(7);
            break;
        case blue:
            setmMap(ImageRes.blueBall);
            setmLocation(GameConfig.BLUE_BALL_LOCATION);
            setmScore(5);
            break;
        case coffe:
            setmMap(ImageRes.coffeBall);
            setmLocation(GameConfig.COFFE_BALL_LOCATION);
            setmScore(4);
            break;
        case green:
            setmMap(ImageRes.greenBall);
            setmLocation(GameConfig.GREEN_BALL_LOCATION);
            setmScore(3);
            break;
        case pink:
            setmMap(ImageRes.pinkBall);
            setmLocation(GameConfig.PINK_BALL_LOCATION);
            setmScore(6);
            break;
        case yellow:
            setmMap(ImageRes.yellowBall);
            setmLocation(GameConfig.YELLOW_BALL_LOCATION);
            setmScore(2);
            break;

        default:
            break;
        }
        mSpeed = new Speed(0, 0);
        mOriginLocation = new BallLocation(getmLocation().getmX(),
                getmLocation().getmY());
    }

    /**
     * 红球的构造函数
     * @param type
     * @param index
     */
    public Ball(BallType type, int index) {
        setmMap(ImageRes.redBall);
        mSpeed = new Speed(0, 0);
        setmScore(1);
        if (index == 1) {
            setmLocation(GameConfig.FIRST_RED_BALL_LOCATION);
            mOriginLocation = new BallLocation(getmLocation().getmX(),
                    getmLocation().getmY());
            return;
        }
        if (index <= 5) {
            int delta = index - 1;
            BallLocation location = new BallLocation();
            location.setmX(GameConfig.FIRST_RED_BALL_LOCATION.getmX() + delta
                    * (GameConfig.BALL_DIAMETER + mDelta) * Math.sqrt(3) / 2);
            location.setmY(GameConfig.FIRST_RED_BALL_LOCATION.getmY() - delta
                    * (GameConfig.BALL_DIAMETER + mDelta) / 2);
            setmLocation(location);
            mOriginLocation = new BallLocation(getmLocation().getmX(),
                    getmLocation().getmY());
            return;
        }
        if (index > 5 && index < 10) {
            int delta = index - 5;
            BallLocation location = new BallLocation();
            location.setmX(GameConfig.FIRST_RED_BALL_LOCATION.getmX() + delta
                    * (GameConfig.BALL_DIAMETER + mDelta) * Math.sqrt(3) / 2);
            location.setmY(GameConfig.FIRST_RED_BALL_LOCATION.getmY() - delta
                    * (GameConfig.BALL_DIAMETER + mDelta) / 2
                    + (GameConfig.BALL_DIAMETER + mDelta));
            setmLocation(location);
            mOriginLocation = new BallLocation(getmLocation().getmX(),
                    getmLocation().getmY());
            return;
        }

        if (index > 9 && index < 13) {
            int delta = index - 8;
            BallLocation location = new BallLocation();
            location.setmX(GameConfig.FIRST_RED_BALL_LOCATION.getmX() + delta
                    * (GameConfig.BALL_DIAMETER + mDelta) * Math.sqrt(3) / 2);
            location.setmY(GameConfig.FIRST_RED_BALL_LOCATION.getmY() - delta
                    * (GameConfig.BALL_DIAMETER + mDelta) / 2 + 2
                    * (GameConfig.BALL_DIAMETER + mDelta));
            setmLocation(location);
            mOriginLocation = new BallLocation(getmLocation().getmX(),
                    getmLocation().getmY());
            return;
        }
        if (index > 12 && index < 15) {
            int delta = index - 10;
            BallLocation location = new BallLocation();
            location.setmX(GameConfig.FIRST_RED_BALL_LOCATION.getmX() + delta
                    * (GameConfig.BALL_DIAMETER + mDelta) * Math.sqrt(3) / 2);
            location.setmY(GameConfig.FIRST_RED_BALL_LOCATION.getmY() - delta
                    * (GameConfig.BALL_DIAMETER + mDelta) / 2 + 3
                    * (GameConfig.BALL_DIAMETER + mDelta));
            setmLocation(location);
            mOriginLocation = new BallLocation(getmLocation().getmX(),
                    getmLocation().getmY());
            return;
        }
        if (index == 15) {
            int delta = index - 11;
            BallLocation location = new BallLocation();
            location.setmX(GameConfig.FIRST_RED_BALL_LOCATION.getmX() + delta
                    * (GameConfig.BALL_DIAMETER + mDelta) * Math.sqrt(3) / 2);
            location.setmY(GameConfig.FIRST_RED_BALL_LOCATION.getmY() - delta
                    * (GameConfig.BALL_DIAMETER + mDelta) / 2 + 4
                    * (GameConfig.BALL_DIAMETER + mDelta));
            setmLocation(location);
            mOriginLocation = new BallLocation(getmLocation().getmX(),
                    getmLocation().getmY());
            return;
        }
    }

    public void drawSelf(Graphics g) {
        mPoint = SnookerServer.transformBallLocation(mLocation);
        g.drawImage(mMap, mPoint.x - 14, mPoint.y - 14, null);
    }

    public void move(int deltaTime) {
        calcLocation(deltaTime);
    }

    /**
     * 计算deltaTime时间段 小球走的距离
     * 
     * @param deltaTime
     */
    private void calcLocation(int deltaTime) {
        Speed newSpeed = Speed.getSpeedByTime(mSpeed, deltaTime);
        double newX = mLocation.getmX() + newSpeed.getmVx() * deltaTime / 1000
                / 2;
        double newY = mLocation.getmY() + newSpeed.getmVy() * deltaTime / 1000
                / 2;
        impactDetect(this, newSpeed);
        mLocation.setLocation(newX, newY);
        setmSpeed(newSpeed);
        isMove();
    }

    /**
     * 获得当前小球一段时间后应该在的位置，不改变实际位置
     * 
     * @param time
     * @return
     */
    public BallLocation getLocationInTime(int time) {
        Speed newSpeed = Speed.getSpeedByTime(mSpeed, time);
        double newX = mLocation.getmX() + newSpeed.getmVx() * time / 1000 / 2;
        double newY = mLocation.getmY() + newSpeed.getmVy() * time / 1000 / 2;
        return new BallLocation(newX, newY);
    }

    public void isMove() {
        if (mSpeed.getmVx() == 0 && mSpeed.getmVy() == 0)
            mMove = false;
        else
            mMove = true;
    }

    /**
     * 获得两点之间的距离
     * 
     * @param p1
     * @param p2
     * @return
     */
    public static double getDistance(BallLocation p1, BallLocation p2) {
        double dis = Math.sqrt(Math.pow(p1.getmX() - p2.getmX(), 2)
                + Math.pow(p1.getmY() - p2.getmY(), 2));
        return dis;
    }

    /**
     * 判断是否碰撞到了边界
     * 如果碰撞到了边界就改变对应的方向
     * 
     * 如果球接触的是球洞位置，则不改变方向
     * @param ball
     * @param newSpeed
     */
    public static void impactDetect(Ball ball, Speed newSpeed) {
        if (ball.getmLocation().getmX() >= GameConfig.RIGHT_X
                && (ball.getmLocation().getmY() >= 60 && ball.getmLocation()
                        .getmY() <= 1700)) {
            if (ball.getmSpeed().getmVx() <= 0)
                return;
            ball.getmSpeed().setmVx(-ball.getmSpeed().getmVx());
            newSpeed.setmVx(-newSpeed.getmVx());
        }
        if (ball.getmLocation().getmX() <= GameConfig.LEFT_X
                && (ball.getmLocation().getmY() >= 60 && ball.getmLocation()
                        .getmY() <= 1700)) {
            if (ball.getmSpeed().getmVx() >= 0)
                return;
            ball.getmSpeed().setmVx(-ball.getmSpeed().getmVx());
            newSpeed.setmVx(-newSpeed.getmVx());
        }
        if (ball.getmLocation().getmY() >= GameConfig.BOTTOM_Y
                && ((ball.getmLocation().getmX() >= 87 && ball.getmLocation()
                        .getmX() <= 1710) || (ball.getmLocation().getmX() >= 1836 && ball
                        .getmLocation().getmX() <= 3450))) {
            if (ball.getmSpeed().getmVy() <= 0)
                return;
            ball.getmSpeed().setmVy(-ball.getmSpeed().getmVy());
            newSpeed.setmVy(-newSpeed.getmVy());
        }
        if (ball.getmLocation().getmY() <= GameConfig.TOP_Y
                && ((ball.getmLocation().getmX() >= 87 && ball.getmLocation()
                        .getmX() <= 1710) || (ball.getmLocation().getmX() >= 1836 && ball
                        .getmLocation().getmX() <= 3450))) {
            if (ball.getmSpeed().getmVy() >= 0)
                return;
            ball.getmSpeed().setmVy(-ball.getmSpeed().getmVy());
            newSpeed.setmVy(-newSpeed.getmVy());
        }
    }

    public BallType getMtype() {
        return mtype;
    }

    public void setMtype(BallType mtype) {
        this.mtype = mtype;
    }

    public Speed getmSpeed() {
        return mSpeed;
    }

    public void setmSpeed(Speed speed) {
        this.mSpeed = speed;
        mMove = !mSpeed.isStop();
    }

    /**
     * 
     * @param p1
     *            母球位置
     * @param p2
     *            鼠标
     * @return
     */
    public static int getVDirection(Point p1, Point p2) {
        // x轴和y轴的速度方向
        // 0 ：都是负方向
        // 1 ：x负y正
        // 2 ：x正y负
        // 3 ： 都是正
        int d = 0x3;
        if (p2.x < p1.x)
            d = d & 0x1;
        if (p2.y < p1.y)
            d = d & 0x2;
        return d;
    }

    /**
     * 加上一个速度
     * 
     * @param speed
     */
    public void addSpeed(Speed speed) {
        this.mSpeed.setmVx(this.mSpeed.getmVx() + speed.getmVx());
        this.mSpeed.setmVy(this.mSpeed.getmVy() + speed.getmVy());
        mMove = !mSpeed.isStop();
        isMove();
    }

    /**
     * 减去一个速度
     * 
     * @param s1
     */
    public void cutSpeed(Speed speed) {
        this.mSpeed.setmVx(this.mSpeed.getmVx() - speed.getmVx());
        this.mSpeed.setmVy(this.mSpeed.getmVy() - speed.getmVy());
        isMove();
    }

    public BallLocation getmLocation() {
        return mLocation;
    }

    public void setmLocation(BallLocation mLocation) {
        this.mLocation = mLocation;
    }

    public BufferedImage getmMap() {
        return mMap;
    }

    public void setmMap(BufferedImage mMap) {
        this.mMap = mMap;
    }

    public boolean ismShow() {
        return mShow;
    }

    public void setmShow(boolean mShow) {
        this.mShow = mShow;
    }

    public boolean ismMove() {
        return mMove;
    }

    public void setmMove(boolean mMove) {
        this.mMove = mMove;
    }

    public Point getmPoint() {
        return mPoint;
    }

    public void setmPoint(Point mPoint) {
        this.mPoint = mPoint;
    }

    public int getmScore() {
        return mScore;
    }

    public void setmScore(int mScore) {
        this.mScore = mScore;
    }

    public void resetLocation() {
        setmLocation(new BallLocation(mOriginLocation.getmX(),
                mOriginLocation.getmY()));
    }

}
