package com.zmq.work.math;

import com.zmq.work.entry.Dir;

public class Triangle {
    /**
     * 已知方向（已知偏向角），计算扇形的角平分线的另一个端点坐标
     * L==180度, LU=225度, U=270度, RU=315度, R=0度或360度, RD=45度, D=90度, LD=135度,
     *
     */
    public static Point getR_Point(Point start_p, float r, Dir dir){
        Point end_p =null;
        double angle=45;
        angle=Math.toRadians(angle);
        float cos_res=(float) Math.cos(angle);//
        float tmp=r/cos_res;
        switch (dir){
            case L://ok 180
                end_p=new Point(start_p.getX()-r,start_p.getY());
                break;
            case LU:// ok 225
                end_p=new Point(start_p.getX()-tmp,start_p.getY()+tmp);
                break;
            case U://ok 270
                end_p=new Point(start_p.getX(),start_p.getY()+r);
                break;
            case RU://ok 315
                end_p=new Point(start_p.getX()+tmp,start_p.getY()+tmp);
                break;
            case R://ok 0
                end_p=new Point(start_p.getX()+r,start_p.getY());
                break;
            case RD://ok 45
                end_p=new Point(start_p.getX()+tmp,start_p.getY()-tmp);
                break;
            case D://ok 90
                end_p=new Point(start_p.getX(),start_p.getY()-r);
                break;
            case LD://135
                end_p=new Point(start_p.getX()-tmp,start_p.getY()-tmp);
                break;
        }
        return end_p;
    }
}
