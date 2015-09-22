package com.will.snooker.config;

import java.util.ArrayList;
import java.util.List;

import com.will.snooker.entity.BallLocation;

/**
 * 游戏参数类
 * 
 * @author qinglian
 *
 */
public class GameConfig {

    /**
     * 游戏窗口宽度
     */
    public static final int WINDOW_WIDTH = 1023;
    /**
     * 游戏窗口高度
     */
    public static final int WINDOW_HEIGHT = 680;

    /**
     * 游戏面板宽度
     */
    public static final int CONFIG_PANEL_WIDTH = 1023;
    /**
     * 游戏面板高度
     */
    public static final int CONFIG_PANEL_HEIGHT = 680;
    

    /**
     * 球默认质量
     */
    public static final float BALL_WEIGHT = 1;

    /**
     * 鼠标名称
     */
    public static final String NORMAL_CURSOR_NAME = "normal";

    public static final String HAND_CURSOR_NAME = "hand";

    public static final String HANDF_CURSOR_NAME = "handf";

    public static final String CROSS_CURSOR_NAME = "cross";

    /**
     * 边界判定用到的值
     */
    public static final int LEFT_X = 60;

    public static final int RIGHT_X = 3505;

    public static final int TOP_Y = 60;

    public static final int BOTTOM_Y = 1712;

    public static final int BALL_RADIUS = 14;


    
    /**
     * 斯诺克球台 竞赛区标准长度
     */
    public static final double DESK_WIDTH = 3569;
    
    /**
     * 斯诺克球台 竞赛区标准宽度
     */
    public static final double DESK_HEIGHT = 1851;
    
    /**
     * 标准斯诺克球 直径
     */
    public static final double BALL_DIAMETER = 96 ;
    
    /**
     * D区域直径
     */
    public static final double AREA_D_DIAMETER = 622 ;
    
    /**
     * 白球初始化点 a
     */
    public static final BallLocation WHITE_BALL_LOCATION = new BallLocation(780 - BALL_DIAMETER *1.2, DESK_HEIGHT/2) ;
    /**
     * 真实黑球点 a
     */
    public static final BallLocation BLACK_BALL_LOCATION = new BallLocation(3297, DESK_HEIGHT/2);
    /**
     * 真实蓝球点 a
     */
    public static final BallLocation BLUE_BALL_LOCATION = new BallLocation(DESK_WIDTH/2, DESK_HEIGHT/2) ;
    /**
     * 真实粉球点 a
     */
    public static final BallLocation PINK_BALL_LOCATION = new BallLocation(2630,DESK_HEIGHT/2 );
    
    /**
     * 真实棕球点 a
     */
    public static final BallLocation COFFE_BALL_LOCATION = new BallLocation(780, DESK_HEIGHT/2);
    
    /**
     * 真实黄球点 a
     */
    public static final BallLocation YELLOW_BALL_LOCATION = new BallLocation(780, 1212);
    
    /**
     * 真实绿球点
     */
    public static final BallLocation GREEN_BALL_LOCATION = new BallLocation(780, 594);
    
    /**
     * 第一颗真实红球点
     */
    public static final BallLocation FIRST_RED_BALL_LOCATION = new BallLocation(2630 + BALL_DIAMETER + 7, DESK_HEIGHT/2);
    /**
     * 是否开启背景音乐
     */
    public static boolean isBGM = true;
    /**
     * 是否开启音效
     */
    public static boolean isSound = true;
    /**
     * 滑动摩擦系数
     */
    public static double coefficientOfFriction = 15f;
    /**
     * 空气阻力系数
     */
    public static double airResistance = 0.00013f;

    /**
     * 小球最大速度
     */
    public static final int V = 10000;
    
    /**
     * 重力加速度
     */
    public static final float G = 9.8f;
    
    /**
     * 游戏是否开始
     */
    public static boolean isGameStart = false;

    /**
     * 是否摆放母球
     */
    public static boolean isPutWhiteBall = false;

    /**
     * 是否瞄准好准备击球了
     */
    public static boolean isReady = false;
    
    public static List<BallLocation> holesLocation ;
    
    static{
        holesLocation = new ArrayList<BallLocation>() ;
        holesLocation.add(new BallLocation(51, 38)) ;
        holesLocation.add(new BallLocation(1780,10)) ;
        holesLocation.add(new BallLocation(3502, 38)) ;
        holesLocation.add(new BallLocation(51, 1718)) ;
        holesLocation.add(new BallLocation(1780,1771 )) ;
        holesLocation.add(new BallLocation(3502, 1728)) ;
    }
    

}
