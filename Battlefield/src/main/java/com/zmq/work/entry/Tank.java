package com.zmq.work.entry;

import java.util.ArrayList;
import java.util.List;

public class Tank {
    public static final int XSPEED = 5;//坦克在x轴上的速度，即每帧走5个像素点
    public static final int YSPEED = 5;//坦克在y轴上的速度，即每帧走5个像素点
    private String account;//用户账号
    private int x, y;//当前所在的坐标点
    private int next_x, next_y;//即将要去的坐标点
    private Dir tank_dir;//坦克面向方向
    private Dir bullet_dir;//炮筒方向
    boolean tank_live=true;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Dir getTank_dir() {
        return tank_dir;
    }

    public void setTank_dir(Dir tank_dir) {
        this.tank_dir = tank_dir;
    }

    public Dir getBullet_dir() {
        return bullet_dir;
    }

    public void setBullet_dir(Dir bullet_dir) {
        this.bullet_dir = bullet_dir;
    }

    public boolean isTank_live() {
        return tank_live;
    }

    public void setTank_live(boolean tank_live) {
        this.tank_live = tank_live;
    }

    /**
     * 处理坦克移动，返回移动后的坐标
     * @param x
     * @param y
     * @param next_x
     * @param next_y
     * @return
     */
    public static List<Tank> tank_move(int x, int y, int next_x, int next_y){
        int i=0,j=0;
        List <Tank>list=new ArrayList<Tank>();
        if (next_x<=20&&next_y<=20){
            for (i=x,j=y;i<=next_x&&j<=next_y;){
                i+=XSPEED;
                j+=YSPEED;
                Tank tank=new Tank();
                tank.setX(i);
                tank.setY(j);
                list.add(tank);
            }
        }
        return null;
    }

}
