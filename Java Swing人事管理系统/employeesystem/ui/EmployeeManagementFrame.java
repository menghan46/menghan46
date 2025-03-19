package com.marionette.employeesystem.ui;

import com.marionette.employeesystem.bean.Employee;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class EmployeeManagementFrame extends JFrame {
    private JTextField searchField;
    private JTable employeeTable;
    private DefaultTableModel tableModel;
    private JPopupMenu rowMenu;
    private ArrayList<Employee> employees= new ArrayList<>();
    public EmployeeManagementFrame(String username) {
        super(username);
        initial();
    }
    private  void  initial(){
//        setTitle("员工管理系统");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 创建顶部面板
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        searchField = new JTextField(15);
        JButton searchButton = new JButton("搜索");
        JButton addEmployeeButton=new JButton("添加");
        topPanel.add(searchField);
        topPanel.add(searchButton);
        topPanel.add(addEmployeeButton);

        // 表格模型和表格
        String[] columnNames =  {"ID", "姓名","性别","年龄","电话","职位","入职日期","薪水","部门"};
        tableModel = new DefaultTableModel(columnNames, 0);
        loadSampleData(); // 加载样本数据
        employeeTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(employeeTable);

        // 右键菜单
        rowMenu = new JPopupMenu();
        JMenuItem editMenuItem = new JMenuItem("修改");
        JMenuItem deleteMenuItem = new JMenuItem("删除");
        rowMenu.add(editMenuItem);
        rowMenu.add(deleteMenuItem);

        // 添加事件监听器
        employeeTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if ( SwingUtilities.isRightMouseButton(e)) {
                    int row = employeeTable.rowAtPoint(e.getPoint());
                    if (row >= 0) {
                        employeeTable.setRowSelectionInterval(row, row);
                        rowMenu.show(employeeTable, e.getX(), e.getY());
                    }
                }
            }
        });

        addEmployeeButton.addActionListener(e -> {
            AddEmployeeFrame addEmployeeFrame=new AddEmployeeFrame(this);
            addEmployeeFrame.setVisible(true);
        });

        editMenuItem.addActionListener(e -> {
            int selectedRow = employeeTable.getSelectedRow();
            if (selectedRow != -1) {
                JOptionPane.showMessageDialog(null, "编辑第" + (selectedRow + 1) + "行数据");
                // 实现具体的编辑逻辑
            }
        });

        deleteMenuItem.addActionListener(e -> {
            int selectedRow = employeeTable.getSelectedRow();
            if (selectedRow != -1) {
//                tableModel.removeRow(selectedRow);
                int deleteId=(Integer) tableModel.getValueAt(selectedRow,0);
                deleteEmployee(deleteId);
                tableModel.removeRow(selectedRow);
//                int id= Integer.parseInt(tableModel.getValueAt(selectedRow,0));
                JOptionPane.showMessageDialog(this, "删除员工ID为："+deleteId);
            }else {
                JOptionPane.showMessageDialog(this, "请选择一行进行删除！");
            }
        });


        // 设置布局
        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    private void deleteEmployee(int deleteId) {
        for(int i=0;i<employees.size();i++){
            if(employees.get(i).getId()==deleteId){
                employees.remove(i);
            }
        }
    }

    private void loadSampleData() {
        Object[][] employees = {
                {1, "张三", "男", 28, "13800138000", "工程师", "2020-03-15", 8000, "技术部"},
                {2, "李四", "女", 25, "13900139000", "设计师", "2021-05-20", 7500, "设计部"},
                {3, "王五", "男", 35, "13600136000", "经理", "2018-01-10", 12000, "市场部"},
                {4, "赵六", "女", 23, "13700137000", "助理", "2022-02-15", 4500, "人事部"},
                {5, "钱七", "男", 29, "13500135000", "分析师", "2019-07-22", 9000, "财务部"},
                {6, "孙八", "男", 31, "13400134000", "程序员", "2017-11-30", 9500, "技术部"},
                {7, "周九", "女", 33, "13300133000", "顾问", "2016-09-14", 10000, "咨询部"},
                {8, "吴十", "男", 40, "13200132000", "主管", "2015-03-25", 11000, "销售部"},
                {9, "郑十一", "女", 27, "13100131000", "协调员", "2021-08-18", 6000, "行政部"},
                {10, "王十二", "男", 26, "13000130000", "开发人员", "2022-01-10", 7800, "技术部"},
                {11, "冯十三", "女", 24, "13810138100", "测试人员", "2022-03-15", 5500, "质量部"},
                {12, "陈十四", "男", 30, "13710137100", "产品经理", "2020-05-20", 9200, "产品部"},
                {13, "褚十五", "女", 28, "13610136100", "运维人员", "2019-07-22", 8500, "运维部"},
                {14, "卫十六", "男", 27, "13510135100", "市场专员", "2021-02-15", 6800, "市场部"},
                {15, "蒋十七", "女", 29, "13410134100", "销售代表", "2018-01-10", 7200, "销售部"},
                {16, "沈十八", "男", 32, "13310133100", "客服代表", "2017-11-30", 5800, "客服部"},
                {17, "韩十九", "女", 26, "13210132100", "财务专员", "2022-02-15", 6300, "财务部"},
                {18, "杨二十", "男", 31, "13110131100", "行政助理", "2020-03-15", 5000, "行政部"},
                {19, "朱二十一", "女", 25, "13010130100", "HR专员", "2021-05-20", 7000, "人事部"},
                {20, "秦二十二", "男", 24, "13820138200", "实习生", "2022-07-22", 3000, "实习部"}
        };

        for (Object[] employee : employees) {
            tableModel.addRow(employee);
        }
    }
    public void addEmployee(Employee employee){
        employees.add(employee);
        tableModel.addRow(new Object[]{employee.getId(),employee.getName(),employee.getSex(),employee.getAge(),employee.getPhone(),employee.getPosition(),employee.getEntryDate(),employee.getSalary(),employee.getDepartment()});
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> new EmployeeManagementFrame());
//    }
}
