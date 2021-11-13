package planewars;

import javax.swing.*;

/**
 * 这是一个子弹的类
 * 每一次创建一个对象表示一颗子弹
 * 跟昨天描述飞机是类似的
 * 有自己的x,y位置 有自己的宽度和高度
 * 有一张子弹的图片
 */
public class Bullet {

    //图片(路径) 起始坐标x 起始坐标y 宽度 高度
    private int x;
    private int y;
    private int width;
    private int height;
    private ImageIcon bulletImage = new ImageIcon("image/bullet.png");


    //为了让构建bullet方便
    //构造方法
    public Bullet(int x, int y){
        this.x = x;
        this.y = y;
        this.width = bulletImage.getIconWidth();
        this.height = bulletImage.getIconHeight();
    }

    //提供属性对应的get方法
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public ImageIcon getBulletImage() {
        return bulletImage;
    }


    //子弹自己的事情  添加一个方法  让子弹的y像素值 减小
    public void move(){
        this.y -= 4;//子弹飞行的速度
    }

}
