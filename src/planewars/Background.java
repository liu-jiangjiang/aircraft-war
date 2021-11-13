package planewars;

import javax.swing.*;

/**
 * 这是一个背景图的类
 * 每一次创建一个对象表示一张背景图对象
 * 有自己的x,y位置 有自己的宽度和高度
 * 有一张背景的图片
 */
public class Background {

    //图片(路径) 起始坐标x 起始坐标y 宽度 高度
    private int x;
    private int y;
    private ImageIcon backgroundImage = new ImageIcon("image/bg.jpg");


    //构造方法
    public Background(int x, int y){
        this.x = x;
        this.y = y;
    }

    //提供属性对应的get方法
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public ImageIcon getBackgroundImage() {
        return backgroundImage;
    }


    //子弹自己的事情  添加一个方法  让子弹的y像素值 减小
    public void move(){
        System.out.println();
        this.y += 1;//背景移动速度
    }

}
