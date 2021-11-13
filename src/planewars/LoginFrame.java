package planewars;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 登录页面
 */
public class LoginFrame extends JFrame {

    //需要一个panel 用来展示
    private JLabel label = new JLabel();

    public LoginFrame(){
        label.setBounds(0,0,480,800);
        //label上面可以直接添加图片
        label.setIcon(new ImageIcon("image/login.jpg"));
        this.add(label);

        //点击
        label.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if(e.getX()>=120 && e.getX()<=355 && e.getY()>=565 && e.getY()<=630){
                    LoginFrame.this.setVisible(false);//垃圾回收还没有执行
                    //开一个新窗口
                    new Thread(new GameFrame()).start();
                }else{

                }
            }
        });
        //移动
        label.addMouseMotionListener(new MouseAdapter() {
            public void mouseMoved(MouseEvent e) {
                if(e.getX()>=120 && e.getX()<=355 && e.getY()>=565 && e.getY()<=630){
                    label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                }else{
                    label.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                }
            }
        });

        this.setBounds(300,20,480,800);
        this.setVisible(true);
    }


}
