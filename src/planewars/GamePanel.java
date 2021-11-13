package planewars;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

@SuppressWarnings("all")
/**
 * 需要让这个GamePanel是一个panel
 */
//在这个类的中间添加一个缺省适配器模式(23种)
public class GamePanel extends JPanel implements MouseMotionListener{

    //背景(2个对象)
    private ArrayList<Background> bgList = new ArrayList();

    //飞机
    private Hero hero = new Hero(200,520);
    private HeroDestory heroDestory;

    //子弹(几颗)
    //  一堆
    private ArrayList<Bullet> bulletList = new ArrayList();

    //敌机(好多个)
    private ArrayList<Enemy> enemyList = new ArrayList();

    //爆炸(好多个)
    private ArrayList<Bomb> bombList = new ArrayList();

    //变量记录分数
    private int score = 0;


    //  数组(创建时必须指定长度 长度一旦确定不能再次发生改变-----定长且连续)
    //      第一个接触的引用数据类型
    //      创建  初始化   存值  取值  遍历
    //      底层内存结构
    //  集合(与数组类似 可以存储一组元素 存储了之后 长度还能发生改变)
    //  选择集合是因为觉得以后长度可以发生改变
    //  集合是一个很庞大的家族
    //  List(有序可重复)
    //      ArrayList       底层是数组   适合遍历      不适合插入和删除  1.5倍  非安全 性能快
    //      LinkedList      底层是链表   适合插入删除   不适合遍历
    //      Vector          底层是数组   2倍   线程安全--性能慢
    //  Set(无序不可重复)  不接受
    //      HashSet         散列表  数组+链表      hashCode + equals
    //      TreeSet         二叉树                compareTo
    //  Map(k-v key无序不可重复 value无序可重复)   覆盖
    //      HashMap         散列表
    //      TreeMap         二叉树
    //      LinkedHashMap
    //      CurrentHashMap
    //  手撕集合(自己用原生的Java代码实现一个集合类的设计  ArrayBox LinkedBox)


    //设计一个方法 用来给对象做初始化
    public void init(){
        //初始化的时候 先添加一张背景
        bgList.add(new Background(0,0));
        //初始化一个飞机对象  上面已经创建完了

        long count = 0;//当做一个系数 控制子弹添加的时间

        while(true){
            count++;//2147483647

            //添加另一张背景图
            if(bgList.size()==1){
                bgList.add(new Background(0,-700));
            }
            //控制背景图片
            for(int i=0;i<bgList.size();i++){
                Background bg = bgList.get(i);
                if(bg!=null){
                    bg.move();//每一次向下移动1像素
                    if(bg.getY()>700){
                        bgList.remove(bg);
                    }
                }
            }

            //添加子弹的
            if(count % 15 == 0) {//子弹之间的间隔
                //初始化子弹对象  存储子弹的集合已经存在了  集合内没有子弹
                bulletList.add(new Bullet(hero.getX() + 45, hero.getY() - 15));
            }
            //控制子弹个数 删除 移动等等
            for(int i=0;i<bulletList.size();i++){
                Bullet bullet = bulletList.get(i);
                if(bullet!=null){
                    //刚存入集合的子弹移动一下
                    bullet.move();
                    if(bullet.getY()< - 15){//子弹已经飞出去啦
                        bulletList.remove(bullet);
                    }
                }
            }
            //java----.jar----exe4j----exe
            //添加敌机的
            if(count % 80 == 0){
                //随机计算一下敌机出现的x位置
                //x的位置需要在整个窗口的范围之内
                int tempEnemyX = (int)(Math.random()*430);
                //初始化敌机对象
                enemyList.add(new Enemy(tempEnemyX,-30));
            }
            //控制子弹个数 删除 移动等等
            for(int i=0;i<enemyList.size();i++){
                Enemy enemy = enemyList.get(i);
                if(enemy!=null){
                    if(enemy.getY() > 700){
                        enemyList.remove(enemy);
                    }
                    //当敌人向下移动以后  有可能与我们的子弹发生碰撞
                    for(int j=0;j<bulletList.size();j++){
                        //循环每一刻子弹  与当前那个enemy飞机比较看是否有碰撞
                        Bullet bullet = bulletList.get(j);
                        if(bullet!=null){//严谨判断子弹找到啦
                            if(this.isHit(enemy,bullet)){//证明碰撞了
                                enemyList.remove(enemy);
                                bulletList.remove(bullet);

                                //出现击中了的效果
                                System.out.println("击中啦。。。");
                                score += 10;
                                bombList.add(new Bomb(enemy.getX(),enemy.getY(),0));
                            }
                        }
                    }

                    //看敌机是否与英雄碰撞
                    if(this.isHit(hero,enemy)){
                        System.out.println("英雄坠毁");
                        heroDestory = new HeroDestory(hero.getX(),hero.getY());
                        hero = null;//英雄就删掉
                        bombList.add(new Bomb(enemy.getX(),enemy.getY(),0));
                        enemyList.remove(enemy);//敌人删掉
                        repaint();
                        JOptionPane.showMessageDialog(this,"游戏结束,您的得分为"+score);
                        return;
                    }
                    enemy.move();//飞机向下移动一下
                }
            }

            //控制一下爆炸图片存储的时间
            for(int i=0;i<bombList.size();i++){
                Bomb bomb = bombList.get(i);
                if(bomb!=null){
                    bomb.setTime(bomb.getTime() + 1);
                    if(bomb.getTime() >= 15){
                        bombList.remove(bomb);
                    }
                }
            }

            repaint();
            try {
                Thread.sleep(10);//10ms 基数
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    //飞机展示出来
    public void paint(Graphics g){//是一个重写方法
        //让父类的paint画完
        super.paint(g);

        //==================================
        //先画背景
        for(int i=0;i<bgList.size();i++){
            Background bg = bgList.get(i);
            g.drawImage(bg.getBackgroundImage().getImage(),bg.getX(),bg.getY(),null);
        }

        //画飞机  最好做一个严谨的判断
        if(hero!=null){
            Image image = hero.getHeroImage().getImage();
            g.drawImage(image,hero.getX(),hero.getY(),null);
        }
        //画英雄爆炸后的图片
        if(heroDestory!=null){
            g.drawImage(heroDestory.getHeroDestoryImage().getImage(),heroDestory.getX(),heroDestory.getY(),null);
        }

        //画子弹  不止一颗 每一颗都要画
        for(int i=0;i<bulletList.size();i++){
            //每一次循环 获取一颗子弹
            Bullet bullet = bulletList.get(i);
            //严谨的判断
            if(bullet!=null){
                g.drawImage(bullet.getBulletImage().getImage(),bullet.getX(),bullet.getY(),null);
            }
        }

        //画敌机
        for(int i=0;i<enemyList.size();i++){
            //每一次循环 获取一个敌机
            Enemy enemy = enemyList.get(i);
            if(enemy!=null){
                g.drawImage(enemy.getEnemyImage().getImage(),enemy.getX(),enemy.getY(),null);
            }
        }

        //画爆炸
        for(int i=0;i<bombList.size();i++){
            Bomb bomb = bombList.get(i);
            if(bomb!=null){
                g.drawImage(bomb.getBombImage().getImage(),bomb.getX(),bomb.getY(),null);
            }
        }

        //设置一下字体
        g.setFont(new Font("宋体",Font.BOLD,24));
        //直接在左上角画一个分数
        g.drawString("得分:"+score,350,30);
    }

    //=======================================================================

    //设计一个方法 计算碰撞
    //  两个参数    敌机  子弹
    //  返回值      是否碰撞 boolean
    private boolean isHit(Enemy e,Bullet b){
        //可以利用一个JDK提供的类来完成
        Rectangle eRect = new Rectangle(e.getX(),e.getY(),e.getWidth(),e.getHeight());
        //子弹的范围
        Rectangle bRect = new Rectangle(b.getX(),b.getY(),b.getWidth(),b.getHeight());

        return eRect.intersects(bRect);//   数据库intersect
    }


    //敌机和英雄撞
    private boolean isHit(Hero hero,Enemy enemy){
        Rectangle hRect = new Rectangle(hero.getX(),hero.getY(),hero.getWidth(),hero.getHeight());;
        Rectangle eRect = new Rectangle(enemy.getX(),enemy.getY(),enemy.getWidth(),enemy.getHeight());
        return hRect.intersects(eRect);
    }

    //这个方法帮我们计算飞机的位置
    private void calculatPosition(MouseEvent e){
        //拖拽
        //飞机需要根据我鼠标的位置 移动他自己的位置
        //可以用参数e来获取鼠标的位置
        int tempX = e.getX();
        int tempY = e.getY();
        if(hero!=null){
            if(tempX>10 && tempX<486) {
                hero.setX(tempX - 60);
            }
            if(tempY>150 && tempY<650) {
                hero.setY(tempY - 100);
            }
        }
        //重新画一遍
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        this.calculatPosition(e);
    }
    @Override
    public void mouseMoved(MouseEvent e){

    }
}
