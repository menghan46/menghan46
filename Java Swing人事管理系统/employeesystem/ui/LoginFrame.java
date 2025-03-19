package com.marionette.employeesystem.ui;

import com.marionette.employeesystem.bean.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LoginFrame extends JFrame implements ActionListener{
    private JTextField userField;
    private JPasswordField passwordField;
    private JButton loginButton, registerButton;
    private static ArrayList<User> users=new ArrayList<>();

    //初始化几个用户信息
    static{
        users.add(new User("admin","123456"));
        users.add(new User("user","123456"));
        users.add(new User("test","123456"));
    }
    public LoginFrame() {
        setTitle("人事管理系统 - 登录");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // 窗口居中显示

        // 主面板使用GridBagLayout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // 组件之间的间距

        // 标题标签
        JLabel titleLabel = new JLabel("欢迎来到人事管理系统", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 20));
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(titleLabel, gbc);

        // 用户名输入框
        JLabel userLabel = new JLabel("用户名:");
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        mainPanel.add(userLabel, gbc);
        userField = new JTextField(20);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        mainPanel.add(userField, gbc);

        // 密码输入框
        JLabel passwordLabel = new JLabel("密码:");
        gbc.gridwidth = 1;
        mainPanel.add(passwordLabel, gbc);
        passwordField = new JPasswordField(20);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        mainPanel.add(passwordField, gbc);

        // 登录和注册按钮
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        loginButton = new JButton("登录");
        registerButton = new JButton("注册");
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        mainPanel.add(buttonPanel, gbc);

        // 添加监听器
        loginButton.addActionListener(this);

        registerButton.addActionListener(this);

        add(mainPanel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn=(JButton) e.getSource();
        if(btn==loginButton){
            login();
        }
        else {
            JOptionPane.showMessageDialog(this,"注册功能未实现");
        }
    }
    private void login(){
        String username=userField.getText();
        String password=new String(passwordField.getPassword());
        User user=getUserByLoginName(username);
        if(user!=null){
            if(user.getPassword().equals(password)){
                JOptionPane.showMessageDialog(this,"登录成功");
                new EmployeeManagementFrame(user.getUsername());
                this.dispose();
            }
            else {
                JOptionPane.showMessageDialog(this,"密码错误");
            }
        }
        else {
            JOptionPane.showMessageDialog(this,"用户名不存在");
        }

    }
    private User getUserByLoginName(String username){
        for(int i=0;i<users.size();i++){
            if(users.get(i).getUsername().equals(username)){
                return users.get(i);
            }
        }
        return null;
    }
}