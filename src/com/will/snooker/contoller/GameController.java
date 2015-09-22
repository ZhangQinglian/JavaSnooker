package com.will.snooker.contoller;


import com.will.snooker.ui.SnookerPanel;
import com.will.snooker.ui.GameFrame;
import com.will.snooker.ui.WelcomeComponent;

/**
 * 游戏控制
 * 
 * @author qinglian
 *
 */
public class GameController {

    /**
     * 游戏窗口
     */
    private GameFrame mGameFrame;
    public GameFrame getmGameFrame() {
        return mGameFrame;
    }

    public void setmGameFrame(GameFrame mGameFrame) {
        this.mGameFrame = mGameFrame;
    }

    /**
     * 游戏是否开始
     */
    private boolean isGameStart = false;

    public GameController() {

        mGameFrame = new GameFrame();
        showWelcomePanel();
    }

    public void showWelcomePanel() {
        mGameFrame.addWelcomePanel(new WelcomeComponent(this));
    }

    public void showSnookerPanel() {
        
        if(mGameFrame.getmWelcomeComponent()!=null)
            mGameFrame.remove(mGameFrame.getmWelcomeComponent());
        mGameFrame.setContentPane(new SnookerPanel(this));
    }

    public boolean isGameStart() {
        return isGameStart;
    }

    public void setGameStart(boolean isGameStart) {
        this.isGameStart = isGameStart;
    }
}
