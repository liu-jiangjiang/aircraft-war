package planewars;

import javax.swing.*;

/**
 * 这是一个敌机的类
 * 每一次创建一个对象表示一架敌机
 * 跟昨天描述飞机是类似的
 * 有自己的x,y位置 有自己的宽度和高度
 * 有一张敌机的图片
 */
public class Enemy {

    //图片(路径) 起始坐标x 起始坐标y 宽度 高度
    private int x;
    private int y;
    private int width;
    private int height;
    private ImageIcon enemyImage = new ImageIcon("image/enemy.png");


    //为了让构建bullet方便
    //构造方法
    public Enemy(int x, int y){
        this.x = x;
        this.y = y;
        this.width = enemyImage.getIconWidth();
        this.height = enemyImage.getIconHeight();
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
    public ImageIcon getEnemyImage() {
        return enemyImage;
    }


    //敌人自己的事情  添加一个方法  让敌机的y像素值 增加
    public void move(){
        this.y += 3;//敌人飞行的速度
    }

}
