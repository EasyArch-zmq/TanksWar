package com.zmq.work.math;

import com.zmq.work.entry.Dir;

/**
 * 扇形类，判断点是否落在扇形区域之内 （c是圆心 P是目标点， r是半径）
 * 1.比较P-C距离：|p-c|<r
 * 2.点积：点击可以计算两个矢量的夹角，检测p-c和u（是面向的方向）的夹角是否小于半边角（2份之一的角度）
 *  如何定义方向？等分8份，
 */
public class Sector {
 /*   private float r;//扇形的半径
    private Dir dir;//面向的方向
    private float angle;//扇形的角度60?
    */

    /**
     * 判断点是否在扇形之内，在就true，不在就false
     * @param point
     * @param r
     * @param dir
     * @param angle
     * @param target_Point
     * @return
     */
    public static boolean IsPointInSector(Point point,float r,Dir dir,float angle,Point target_Point){
        if (r<Point.getDistance(point,target_Point)){
            return false;
        }else {
            Point r_Point=Triangle.getR_Point(point,r,dir);//拿到扇形角平分线的端点
            /**
             * 利用三点求角度
             */
            float tmp_angle=Point.getAngle(r_Point,point,target_Point);
            if ((angle/2)>=tmp_angle){
                return true;
            }
            return false;
        }
    }
}
