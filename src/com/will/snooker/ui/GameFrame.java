package com.will.snooker.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.will.snooker.config.GameConfig;

/**
 * 游戏主窗口
 * 
 * @author qinglian
 *
 */
public class GameFrame extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public static int x = 10;
    public static int y = 20;
    private JPanel mGamePanel;
    
    private WelcomeComponent mWelcomeComponent;
    private SnookerPanel mSnookerPanel;

    public GameFrame() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(GameConfig.WINDOW_WIDTH, GameConfig.WINDOW_HEIGHT);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screenSize.width - GameConfig.WINDOW_WIDTH >> 1,
                screenSize.height - GameConfig.WINDOW_HEIGHT >> 1);
        setResizable(false);
        setUndecorated(true);
        setVisible(true);
    }

    public void addWelcomePanel(WelcomeComponent component) {
        setmWelcomeComponent(component);
        mGamePanel = new JPanel();
        mGamePanel.setSize(GameConfig.WINDOW_WIDTH, GameConfig.WINDOW_HEIGHT);
        mGamePanel.setVisible(true);
        mGamePanel.setBackground(Color.WHITE);
        mGamePanel.add(component);
        this.setContentPane(mGamePanel);
    }

    public SnookerPanel getmSnookerPanel() {
        return mSnookerPanel;
    }

    public void setmSnookerPanel(SnookerPanel mConfigPanel) {
        this.mSnookerPanel = mConfigPanel;
    }

    public WelcomeComponent getmWelcomeComponent() {
        return mWelcomeComponent;
    }

    public void setmWelcomeComponent(WelcomeComponent mWelcomeComponent) {
        this.mWelcomeComponent = mWelcomeComponent;
    }

}
