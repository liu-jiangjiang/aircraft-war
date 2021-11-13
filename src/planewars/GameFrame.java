package planewars;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 这是单独定义的一个窗口类型
 * 类型中创建出来的对象是一个窗口
 *
 * 这个类创建完了 说自己是窗口就是么???
 *  is-a  继承关系
 */

//  1.Swing  窗体(没有必要深入研究  HTML CSS JavaScript)
//  2.集合    ArrayList
//  3.类的关系 继承 实现 is-a  has-a  use-a
//  4.抽象类 接口  匿名内部类
//  5.语法结构 for break while
//  6.设计模式(模板 观察者)

//static final int 退出程序时关闭 = 3;//变量 可以改变   3常量  变量 属性 方法 类 特点????

//面向对象的编程思想     类和类的关系
//  对象---什么看作是对象呢???
//  万物皆对象
//  如果可以把窗体当做对象     (面板 按钮)属性
//  has-a
//  窗体里面有面板  窗体里面有按钮
public class GameFrame extends JFrame implements Runnable{

    //  1.画一个窗体     默认是隐藏状态
    //private JFrame frame = new JFrame("飞机大战");
    // 构造方法 set方法 属性 内存中类 对象加载顺序
    //  类成员一共有四个
    //  属性 方法 构造方法 代码块

    //  2.窗体内需要一个面板(透明容器 装元素 为了布局)
    private GamePanel panel = new GamePanel();//无色透明的容器  可以有好多个

    //方法重载Overload
    //方法重写Override

    //重写无参数构造方法
    public GameFrame(){
        this.addElements();
        this.addListener();
        this.setFrameSelf();
    }
    public GameFrame(String title){
        super(title);
        this.addElements();
        this.addListener();
        this.setFrameSelf();
    }

    public void setTitle(String title){
        super.setTitle(title);
    }

    //将这些元素添加在一起-----事情
    private void addElements(){
        this.add(panel);//this super区别
    }

    //将绑定了监听器的对象添加在窗体中
    private void addListener(){
        this.addMouseMotionListener(panel);
    }

    //设计这个窗体自己的一些属性
    private void setFrameSelf(){
        //  Browser/Server
        //  Client/Server
        //  设置窗体出来的默认位置
        this.setBounds(300,100,480,700);//HTML <DIV>
        //  设置窗口不能拖拽变大小
        this.setResizable(false);
        //  设置点击右上角X的时候 程序关闭
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//3可读性不强  静态常量   退出程序时关闭
        //  设置他的状态---变为可见
        this.setVisible(true);

    }


    public void run(){
        //调用一下init方法 进行初始化
        panel.init();
    }
}
