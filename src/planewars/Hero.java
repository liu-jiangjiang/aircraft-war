package planewars;

import javax.swing.*;

/**
 * 描述的一个英雄飞机类
 * 类中创建一个对象----一个英雄飞机
 */
public class Hero {

    //图片(路径) 起始坐标x 起始坐标y 宽度 高度
    private int x;
    private int y;
    private int width;
    private int height;
    private ImageIcon heroImage = new ImageIcon("image/hero.png");


    //为了让构建Hero图片方便
    //构造方法
    public Hero(int x,int y){
        this.x = x;
        this.y = y;
        this.width = heroImage.getIconWidth();
        this.height = heroImage.getIconHeight();
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
    public ImageIcon getHeroImage() {
        return heroImage;
    }

    //以后需要操作飞机的位置
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }

}
