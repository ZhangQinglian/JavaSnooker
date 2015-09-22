package com.will.snooker.server;

import java.awt.Point;

import com.will.snooker.config.GameConfig;
import com.will.snooker.entity.Ball;
import com.will.snooker.entity.BallLocation;
import com.will.snooker.entity.Speed;
/**
 * 游戏服务类
 * @author qinglian
 *
 */
public class SnookerServer {
    public static double scaleX = 970.0 / 3569.0;

    public static double scaleY = 503.0 / 1778.0;

    /**
     * 将真实坐标转换为游戏坐标
     * 
     * @param location
     * @return
     */
    public static Point transformBallLocation(BallLocation location) {
        Point point = new Point();
        point.setLocation(location.getmX() * scaleX + 30, location.getmY()
                * scaleY + 30);
        return point;
    }

    /**
     * 将游戏坐标转化为真实坐标
     * 
     * @param point
     * @return
     */
    public static BallLocation transformBallPoint(Point point) {

        double x = (point.getX() - 30) / scaleX;
        double y = (point.getY() - 30) / scaleY;
        BallLocation location = new BallLocation(x, y);
        return location;
    }

    /**
     * 碰撞检测
     * 
     */
    public static void impact(Ball ball1, Ball ball2) {

        // 如果两颗小球正在远离，不进行碰撞检测
        if (Ball.getDistance(ball1.getmLocation(), ball2.getmLocation()) < Ball
                .getDistance(ball1.getLocationInTime(100),
                        ball2.getLocationInTime(100))) {
            return;
        }
        double a1, a2, b1;
        Speed s, s1;
        BallLocation loc1 = ball1.getmLocation();
        BallLocation loc2 = ball2.getmLocation();
        if (Ball.getDistance(loc1, loc2) <= GameConfig.BALL_DIAMETER) {
            a1 = (ball2.getmLocation().getmY() - ball1.getmLocation().getmY())
                    / (ball2.getmLocation().getmX() - ball1.getmLocation()
                            .getmX());
            a2 = -1D / a1;
            s = Speed.getOppSpeed(ball1, ball2);
            b1 = s.getmVy() - a2 * s.getmVx();
            s1 = new Speed(b1 / (a1 - a2), a1 * b1 / (a1 - a2));

            ball1.cutSpeed(s1);
            ball2.addSpeed(s1);
        }
    }

    /**
     * 判断两颗小球是否已经碰撞
     * 
     * @param ball1
     * @param ball2
     * @return
     */
    public static boolean isImpact(Ball ball1, Ball ball2) {
        BallLocation loc1 = ball1.getmLocation();
        BallLocation loc2 = ball2.getmLocation();
        if (Ball.getDistance(loc1, loc2) <= GameConfig.BALL_DIAMETER) {
            return true;
        }
        return false;
    }

    /**
     * 判断球是否进入了球洞
     * @param ball
     * @return
     */
    public static boolean isInHole(Ball ball) {

        boolean b1 = (ball.getmLocation().getmX() < GameConfig.holesLocation
                .get(0).getmX() && ball.getmLocation().getmY() < GameConfig.holesLocation
                .get(0).getmY());

        boolean b2 = (ball.getmLocation().getmY() < GameConfig.holesLocation
                .get(1).getmY() && ((ball.getmLocation().getmX() < GameConfig.holesLocation
                .get(1).getmX() + GameConfig.BALL_DIAMETER / 3) && (ball
                .getmLocation().getmX() > GameConfig.holesLocation.get(1)
                .getmX() - GameConfig.BALL_DIAMETER / 3)));

        boolean b3 = (ball.getmLocation().getmX() > GameConfig.holesLocation
                .get(2).getmX() && ball.getmLocation().getmY() < GameConfig.holesLocation
                .get(2).getmY());

        boolean b4 = (ball.getmLocation().getmX() < GameConfig.holesLocation
                .get(3).getmX() && ball.getmLocation().getmY() > GameConfig.holesLocation
                .get(3).getmY());

        boolean b5 = (ball.getmLocation().getmY() > GameConfig.holesLocation
                .get(4).getmY() && ((ball.getmLocation().getmX() < GameConfig.holesLocation
                .get(4).getmX() + GameConfig.BALL_DIAMETER / 4) && (ball
                .getmLocation().getmX() > GameConfig.holesLocation.get(4)
                .getmX() - GameConfig.BALL_DIAMETER / 4)));

        boolean b6 = (ball.getmLocation().getmX() > GameConfig.holesLocation
                .get(5).getmX() && ball.getmLocation().getmY() > GameConfig.holesLocation
                .get(5).getmY());

        if (b1 || b2 || b3 || b4 || b5 || b6) {
            return true;
        }

        return false;
    }
}
