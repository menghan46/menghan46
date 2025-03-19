package com.marionette.employeesystem.ui;

import com.marionette.employeesystem.bean.Employee;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class AddEmployeeFrame extends JFrame {
    private JTextField idField, nameField, ageField, phoneField, salaryField;
    private JComboBox<String> genderBox, positionBox, departmentBox;
    private JSpinner dateSpinner;
    private EmployeeManagementFrame employeeManagementFrame;

    public AddEmployeeFrame(EmployeeManagementFrame employeeManagementFrame) {
        setTitle("添加员工信息");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // 主面板
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // 创建各输入组件
        idField = new JTextField(20);
        nameField = new JTextField(20);
        genderBox = new JComboBox<>(new String[]{"男", "女"});
        ageField = new JTextField(20);
        phoneField = new JTextField(20);
        positionBox = new JComboBox<>(new String[]{"工程师", "设计师", "经理", "助理", "分析师", "程序员", "顾问", "主管", "协调员", "开发人员", "测试人员", "产品经理", "运维人员", "市场专员", "销售代表", "客服代表", "财务专员", "行政助理", "HR专员", "实习生"});
        dateSpinner = new JSpinner(new SpinnerDateModel());
        ((JSpinner.DateEditor) dateSpinner.getEditor()).getFormat().applyPattern("yyyy-MM-dd");
        salaryField = new JTextField(20);
        departmentBox = new JComboBox<>(new String[]{"技术部", "设计部", "市场部", "人事部", "财务部", "咨询部", "销售部", "行政部", "质量部", "产品部", "运维部"});

        // 将组件添加到主面板
        mainPanel.add(createLabeledPanel("ID", idField));
        mainPanel.add(createLabeledPanel("姓名", nameField));
        mainPanel.add(createLabeledPanel("性别", genderBox));
        mainPanel.add(createLabeledPanel("年龄", ageField));
        mainPanel.add(createLabeledPanel("电话", phoneField));
        mainPanel.add(createLabeledPanel("职位", positionBox));
        mainPanel.add(createLabeledPanel("入职日期", dateSpinner));
        mainPanel.add(createLabeledPanel("薪水", salaryField));
        mainPanel.add(createLabeledPanel("部门", departmentBox));

        // 按钮面板
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("添加");
        JButton cancelButton = new JButton("取消");

        // 添加事件监听器
        addButton.addActionListener(e -> {
            if (idField.getText().isEmpty() || nameField.getText().isEmpty() || ageField.getText().isEmpty() || phoneField.getText().isEmpty() || salaryField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "请填写完整信息！");
                return;
            }else {
                Employee employee = new Employee(Integer.parseInt(idField.getText()), nameField.getText(), genderBox.getSelectedItem().toString(), Integer.parseInt(ageField.getText()), phoneField.getText(), positionBox.getSelectedItem().toString(), ((Date) dateSpinner.getValue()).toString(), Double.parseDouble(salaryField.getText()), departmentBox.getSelectedItem().toString());
                employeeManagementFrame.addEmployee(employee);
            }
            JOptionPane.showMessageDialog(null, "添加成功！");
            dispose(); // 关闭当前窗口
            // 在这里可以添加保存到数据库或更新表格模型的逻辑
        });

        cancelButton.addActionListener(e -> dispose()); // 关闭当前窗口

        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);

        mainPanel.add(buttonPanel);

        add(mainPanel);
        setVisible(true);
    }

    private JPanel createLabeledPanel(String labelText, JComponent component) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(null, labelText, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        panel.add(component, BorderLayout.CENTER);
        return panel;
    }

}