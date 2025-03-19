package com.marionette;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainFrame extends JFrame {
    //初始化一个二维数组用于存放数字图片位置
    private int[][] imageData={
            {1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
            {13,14,15,0}
    };
    private static final String ImagePath="stone-maze/src/image/";
    public MainFrame(){
        //初始化窗口,包括窗口大小等信息
        initFrame();
        //打乱数字色块顺序
        initRandomArray();
        //初始化图片
        initImage();
        //初始化系统菜单
        initMenu();
        //绑定位置移动
        initKeyPressListener();
        this.setVisible(true);
    }

    private void initKeyPressListener() {
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()){
                    case KeyEvent.VK_UP:
                        switchAndMove(Direction.UP);
                        break;
                    case KeyEvent.VK_DOWN:
                        switchAndMove(Direction.DOWN);
                         break;
                    case KeyEvent.VK_LEFT:
                        switchAndMove(Direction.LEFT);
                         break;
                    case KeyEvent.VK_RIGHT:
                        switchAndMove(Direction.RIGHT);
                         break;
                    default:
                         break;
                }
            }
        });
    }

    private void switchAndMove(Direction direction){

    }

    private void initRandomArray() {
        for(int i=0;i<imageData.length;i++){
            for(int j=0;j<imageData[i].length;j++){
                int i1=(int)(Math.random()*4);
                int j1=(int)(Math.random()*4);
                int i2=(int)(Math.random()*4);
                int j2=(int)(Math.random()*4);
                int temp=imageData[i1][j1];
                imageData[i1][j1]=imageData[i2][j2];
                imageData[i2][j2]=temp;
            }
        }
    }

    private void initMenu() {
        JMenuBar jMenuBar=new JMenuBar();
        JMenu jMenu=new JMenu("系统");
        JMenuItem exitItem=new JMenuItem("退出");
        jMenu.add(exitItem);
        exitItem.addActionListener(e -> {
           dispose();
        });
        JMenuItem restartItem=new JMenuItem("重新开始");
        jMenu.add(restartItem);
        jMenuBar.add(jMenu);
        this.setJMenuBar(jMenuBar);
    }

    private void initImage() {
        for(int i=0;i<imageData.length;i++){
            for(int j=0;j<imageData[i].length;j++){
                String imageName=imageData[i][j]+".png";
                //创建一个图片对象
                ImageIcon imageIcon=new ImageIcon(ImagePath+imageName);
                //创建一个标签对象
                JLabel jLabel=new JLabel(imageIcon);
                //设置标签位置
                jLabel.setBounds(20+j*100,60+i*100,100,100);
                //将标签添加到窗口中
                this.add(jLabel);
            }
        }
        //初始化背景图
        ImageIcon imageIcon=new ImageIcon(ImagePath+"background.png");
        JLabel jLabel=new JLabel(imageIcon);
        jLabel.setBounds(0,0,450,484);
        this.add(jLabel);
    }

    private void initFrame(){
        this.setTitle("石头迷宫");
        this.setSize(465,575);
        this.setLocationRelativeTo(null);
        //设置窗口关闭事件
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置窗口布局方式为绝对定位
        this.setLayout(null);
        this.setLocationRelativeTo(null);


    }
}
