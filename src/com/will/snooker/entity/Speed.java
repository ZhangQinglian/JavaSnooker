package com.will.snooker.entity;


import com.will.snooker.config.GameConfig;

/**
 * 速度矢量
 * 
 * @author qinglian
 *
 */
public class Speed {

    /**
     * x轴方向的速度
     */
    private double mVx;
    /**
     * y轴方向的速度
     */
    private double mVy;

    static Speed nextSpeed;

    public Speed(double vx, double vy) {
        setmVx(vx);
        setmVy(vy);
    }

    /**
     * 获得初始速度为 speed 经过deltaTime 毫秒后的速度
     * 
     * @param speed
     * @param deltaTime
     * @return
     */
    public static Speed getSpeedByTime(Speed speed, int deltaTime) {
        nextSpeed = new Speed(0, 0);

        double ca = GameConfig.coefficientOfFriction * GameConfig.BALL_WEIGHT
                * GameConfig.G / GameConfig.BALL_WEIGHT;
        double aa = ((float) (GameConfig.airResistance * Math.pow(
                speed.value(), 2))) / GameConfig.BALL_WEIGHT;
        double ax = ca + aa;

        double newValue = speed.value() - ax * deltaTime / 1000;
        if (newValue <= 0)
            newValue = 0;
        double scale = newValue / speed.value();
        nextSpeed.setmVx(speed.getmVx() * scale);
        nextSpeed.setmVy(speed.getmVy() * scale);

        return nextSpeed;
    }

    /**
     * 求母球击球时的速度
     * 
     * @param p1
     *            母球所在位置
     * @param p2
     *            鼠标所在位置
     * 
     * @param v
     *            速度
     * @return 速度
     */
    public static Speed getOrigin(BallLocation p1, BallLocation p2, int v) {

        System.out.println("母球位置：(" + p1.getmX() + "," + p1.getmY() + ")鼠标位置：("
                + p2.getmX() + "," + p2.getmY() + ")");
        Speed speed = new Speed(0, 0);
        double vx = 0;
        double vy = 0;
        double len = Ball.getDistance(p1, p2);
        vx = v * (p2.getmX() - p1.getmX()) / len;
        vy = v * (p2.getmY() - p1.getmY()) / len;
        speed.setmVx(vx);
        speed.setmVy(vy);

        return speed;
    }

    /**
     * 获得两球的相对速度
     * @param ball1
     * @param ball2
     * @return
     */
    public static Speed getOppSpeed(Ball ball1, Ball ball2) {
        return new Speed(ball1.getmSpeed().getmVx() - ball2.getmSpeed().getmVx(),
                ball1.getmSpeed().getmVy() - ball2.getmSpeed().getmVy());

    }

    public double value() {
        return Math.sqrt(Math.pow(mVx, 2) + Math.pow(mVy, 2));
    }

    public boolean isStop() {
        return mVx == 0 && mVy == 0;
    }

    public double getmVx() {
        return mVx;
    }

    public void setmVx(double mVx) {
        this.mVx = mVx;
    }

    public double getmVy() {
        return mVy;
    }

    public void setmVy(double mVy) {
        this.mVy = mVy;
    }

}
