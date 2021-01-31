package com.zmq.work.math;

public class Point {
    private float x;
    private float y;

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Point(float x, float y){
        this.x=x;
        this.y=y;
    }

    /**
     * 计算两点之间的距离
     */
    public static float getDistance(Point point1,Point target_Point){
       float res;
        // 计算两点间距离公式
        res = (float) Math.sqrt(Math.abs((point1.x - target_Point.x)
                * (point1.x - target_Point.x)+(point1.x - target_Point.x)
                * (point1.x - target_Point.y)));
        return res;
    }

    /**
     * 求三点之间的角度
     */
    public static float getAngle(Point r_Point,Point point,Point target){
        //向量的点乘
        double vector=(r_Point.x-point.x)*(target.x-point.x)+(r_Point.y-point.y)*(target.y-point.y);
        //向量的模乘
        double sqrt=Math.sqrt(Math.abs((r_Point.x-point.x)*(r_Point.x-point.x))
                +Math.abs((r_Point.y-point.y)*(r_Point.y-point.y))
                *(Math.abs((target.x-point.x)*(target.x-point.x)
                +Math.abs((target.y-point.y)*(target.y-point.y)))));
        //反余弦得到弧度
        double radian=Math.acos(vector/sqrt);
        //弧度转角度
        return (float)(180*radian/Math.PI);
    }
}
