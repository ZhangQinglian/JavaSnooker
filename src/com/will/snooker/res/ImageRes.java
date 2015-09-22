package com.will.snooker.res;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.will.snooker.config.GameConfig;

/**
 * 图片资源
 * 
 * @author qinglian
 *
 */
public class ImageRes {

    public static Cursor normal_cursor;

    public static Cursor hand_cursor;

    public static Cursor handf_cursor;

    public static Cursor cross_cursor;
    
    public static BufferedImage cheerYouImage;

    public static BufferedImage desktopImage;

    public static ImageIcon startBtnIamge;

    public static BufferedImage whiteBall;
    
    public static BufferedImage blackBall;
    
    public static BufferedImage blueBall;
    
    public static BufferedImage coffeBall;
    
    public static BufferedImage greenBall;
    
    public static BufferedImage pinkBall;
    
    public static BufferedImage redBall;
    
    public static BufferedImage yellowBall;
    
    private static int cursor_x = 0;
    
    private static int cursor_y = 0;

    static {

        Toolkit kit = Toolkit.getDefaultToolkit();
        try {
            normal_cursor = kit.createCustomCursor(
                    kit.getImage("res\\drawable\\cursor\\normal.png"),
                    new Point(cursor_x, cursor_y), GameConfig.NORMAL_CURSOR_NAME);
            hand_cursor = kit.createCustomCursor(kit
                    .getImage("res\\drawable\\cursor\\hand.png"), new Point(cursor_x,
                            cursor_y), GameConfig.HAND_CURSOR_NAME);

            handf_cursor = kit.createCustomCursor(
                    kit.getImage("res\\drawable\\cursor\\handf.png"),
                    new Point(cursor_x, cursor_y), GameConfig.HANDF_CURSOR_NAME);

           cross_cursor = kit.createCustomCursor(
                    kit.getImage("res\\drawable\\cursor\\cross.png"),
                    new Point(cursor_x, cursor_y), GameConfig.CROSS_CURSOR_NAME);
           
            cheerYouImage = ImageIO.read(new File(
                    "res\\drawable\\welcome.png"));
            desktopImage = ImageIO.read(new File("res\\drawable\\desktop.png"));
            startBtnIamge = new ImageIcon(
                    "res\\drawable\\string\\start_btn.png");

            whiteBall = ImageIO.read(new File(
                    "res\\drawable\\ball\\white.png"));
            
            blackBall = ImageIO.read(new File(
                    "res\\drawable\\ball\\black.png"));
            
            blueBall = ImageIO.read(new File(
                    "res\\drawable\\ball\\blue.png"));
            
            coffeBall = ImageIO.read(new File(
                    "res\\drawable\\ball\\coffe.png"));
            
            greenBall = ImageIO.read(new File(
                    "res\\drawable\\ball\\green.png"));
            
            pinkBall = ImageIO.read(new File(
                    "res\\drawable\\ball\\pink.png"));
            
            redBall = ImageIO.read(new File(
                    "res\\drawable\\ball\\red.png"));
            
            yellowBall = ImageIO.read(new File(
                    "res\\drawable\\ball\\yellow.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
