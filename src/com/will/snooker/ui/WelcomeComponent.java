package com.will.snooker.ui;

import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JComponent;

import com.will.snooker.config.GameConfig;
import com.will.snooker.contoller.GameController;
import com.will.snooker.res.ImageRes;
/**
 * 游戏欢迎面板
 * @author qinglian
 *
 */
public class WelcomeComponent extends JComponent implements Runnable {

    /**
     * 
     */
    private static final long serialVersionUID = -8341984753206907845L;

    private int x;
    private int y;
    private float alpha;
    private Image logo;
    private GameController mGameController;

    public WelcomeComponent(GameController gameController) {
        mGameController = gameController;
        x = GameConfig.WINDOW_WIDTH - ImageRes.cheerYouImage.getWidth() >> 1;
        y = GameConfig.WINDOW_HEIGHT - ImageRes.cheerYouImage.getHeight() >> 1;
        alpha = 0.0f;
        logo = ImageRes.cheerYouImage;
        setPreferredSize(new Dimension(GameConfig.WINDOW_WIDTH, GameConfig.WINDOW_HEIGHT));
        new Thread(this).start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        AlphaComposite comp = AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER, alpha);
        g2d.setComposite(comp);
        g2d.drawImage(logo, x, y, this);
    }

    @Override
    public void run() {
        while (true) {
            if (this.alpha == 0) {
                for (int i = 0; i <= 60; i++) {
                    this.alpha = (float) i / 60;
                    try {
                        Thread.sleep(40);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    repaint();
                }
            } else {
                for (int i = 90; i >= 0; i--) {
                    if (i <= 60) {
                        this.alpha = (float) i / 60;
                    } else {
                        this.alpha = 1;
                    }
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    repaint();
                }
                break;
            }
        }
        mGameController.showSnookerPanel();

    }
}
