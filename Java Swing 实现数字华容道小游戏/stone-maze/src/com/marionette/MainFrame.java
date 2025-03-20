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
    //定义一个二维数组用于判断游戏是否胜利
    private int[][] winData={
            {1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
            {13,14,15,0}
    };
    private static final String ImagePath="stone-maze/src/image/";
    private int row;
    private int col;
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

    private void switchAndMove(Direction r){
        switch (r){
            case UP:
                if(row<imageData.length-1){
                    int temp=imageData[row+1][col];
                    imageData[row+1][col]=0;
                    imageData[row][col]=temp;
                    row++;
                    initImage();
                }
                break;
            case DOWN:
                if(row>0){
                    int temp=imageData[row-1][col];
                    imageData[row-1][col]=0;
                    imageData[row][col]=temp;
                    row--;
                    initImage();
                }
                break;
            case LEFT:
                if(col<imageData.length-1){
                    int temp=imageData[row][col+1];
                    imageData[row][col+1]=0;
                    imageData[row][col]=temp;
                    col++;
                    initImage();
                }
                break;
            case RIGHT:
                if(col>0){
                    int temp=imageData[row][col-1];
                    imageData[row][col-1]=0;
                    imageData[row][col]=temp;
                    col--;
                    initImage();
                }
                break;
            default:
                break;
        }

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
        for(int i=0;i<imageData.length;i++){
            for(int j=0;j<imageData[i].length;j++){
                if(imageData[i][j]==0){
                    row=i;
                    col=j;
                }
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
        restartItem.addActionListener(e -> {
            initRandomArray();
            initImage();
        });
        jMenu.add(restartItem);
        jMenuBar.add(jMenu);
        this.setJMenuBar(jMenuBar);
    }

    private void initImage() {
        //先清空面板上的所有图层
        this.getContentPane().removeAll();
        if(isWin()){
            JLabel label=new JLabel(new ImageIcon(ImagePath+"win.png"));
            label.setBounds(124,230,266,88);
            this.add(label);
        }
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

        //刷新窗口
        this.repaint();
    }

    private boolean isWin() {
        for(int i=0;i<imageData.length;i++){
            for(int j=0;j<imageData[i].length;j++){
                if(imageData[i][j]!=winData[i][j]){
                    return false;
                }
            }
        }
        return true;
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
