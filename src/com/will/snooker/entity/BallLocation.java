package com.will.snooker.entity;
/**
 * 球类实际位置类
 * @author qinglian
 *
 */
public class BallLocation {

    private double mX;

    private double mY;

    public BallLocation(double x, double y) {
        setmX(x);
        setmY(y);
    }
    public BallLocation() {
        setmX(0);
        setmY(0);
    }

    
    public double getmX() {
        return mX;
    }

    public void setmX(double mX) {
        this.mX = mX;
    }

    public double getmY() {
        return mY;
    }

    public void setmY(double mY) {
        this.mY = mY;
    }

    @Override
    public String toString() {
        return "[" + mX + "," + mY + "]";
    }

    public void setLocation(double x, double y) {
        mX = x;
        mY = y;
    }
}
