package com.zmq.work.entry;

public class Bullet {
    public static final int XSPEED = 10;//炮弹在x轴上的速度，即每帧走10个像素点
    public static final int YSPEED = 10;//炮弹在y轴上的速度，即每帧走10个像素点
    private String account;//用户账号
    private int x, y;//当前所在的坐标点
    private int next_x, next_y;//即将要去的坐标点
    private Dir tank_dir;//坦克面向方向
    private Dir bullet_dir;//炮筒方向
    boolean tank_live=true;

    public static Bullet bullet_move(String info){
        return null;
    }
}
