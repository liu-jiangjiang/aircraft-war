package planewars;

import javax.swing.*;

public class HeroDestory {

    //图片(路径) 起始坐标x 起始坐标y 宽度 高度
    private int x;
    private int y;
    private ImageIcon heroDestoryImage = new ImageIcon("image/hero_destory.png");


    //为了让构建Hero图片方便
    //构造方法
    public HeroDestory(int x, int y){
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
    public ImageIcon getHeroDestoryImage() {
        return heroDestoryImage;
    }



}
