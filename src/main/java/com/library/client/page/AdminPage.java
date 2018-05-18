package com.library.client.page;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.json.JSONException;

import com.library.client.Constant;
import com.library.client.models.User;
import com.library.client.util.LibraryException;
import com.library.client.util.UserUtil;

public class AdminPage extends JFrame implements ActionListener {

    private String title;
    private JComboBox typeJComboBox=new JComboBox();
    private JLabel usernameJLabel = new JLabel();
    private JLabel sexJLabel = new JLabel();
    private JLabel phoneJLabel = new JLabel();
    private JLabel isAdminJLabel = new JLabel();
    private JLabel amountJLabel = new JLabel();
    private JButton changeInfoJButton = new JButton("修改用户信息");
    private JButton viewBookedJButton = new JButton("查看借阅书籍");
    private JButton logoutJButton = new JButton("用户登出");
    private JButton exitJButton = new JButton("退出系统");
    private JTextField searchJTextField = new JTextField(50);
    private JButton searchJButton = new JButton("搜 索");
    private ButtonGroup searchButtonGroup = new ButtonGroup();
    private JRadioButton isbnJRadioButton = new JRadioButton("ISBN");
    private JRadioButton authorJRadioButton = new JRadioButton("作 者");
    private JRadioButton tagJRadioButton = new JRadioButton("类 别");
    private JRadioButton keywordJRadioButton = new JRadioButton("关键字");
    private JRadioButton fuzzyJRadioButton = new JRadioButton("模糊查询");
    public AdminPage(String title, User user) {
        super(title);
        this.title = title;
        this.setBounds(0, 0, 1440, 900);
        
        JPanel usernameAndSexJPanel = new JPanel();
        usernameJLabel.setText("姓名：" + user.getName());
        usernameAndSexJPanel.add(usernameJLabel);
        sexJLabel.setText("性别：" + Constant.getSex(user.getSex()));
        usernameAndSexJPanel.add(sexJLabel);
        usernameAndSexJPanel.setBackground(Color.LIGHT_GRAY);
        usernameAndSexJPanel.setBounds(25, 100, 250, 50);
        this.add(usernameAndSexJPanel);

        JPanel phoneJPanel = new JPanel();
        phoneJLabel.setText("手机号：" + user.getPhone());
        phoneJPanel.add(phoneJLabel);
        phoneJPanel.setBackground(Color.LIGHT_GRAY);
        phoneJPanel.setBounds(25, 150, 250, 50);
        this.add(phoneJPanel);

        JPanel isAdminAndAmountJPanel = new JPanel();
        isAdminJLabel.setText("账号类型：普通用户");
        isAdminAndAmountJPanel.add(isAdminJLabel);
        amountJLabel.setText("已借：" + user.getAmount());
        isAdminAndAmountJPanel.add(amountJLabel);
        isAdminAndAmountJPanel.setBackground(Color.LIGHT_GRAY);
        isAdminAndAmountJPanel.setBounds(25, 200, 250, 50);
        this.add(isAdminAndAmountJPanel);

        JPanel changeInfoJPanel = new JPanel();
        changeInfoJPanel.add(changeInfoJButton);
        changeInfoJPanel.setBackground(Color.LIGHT_GRAY);
        changeInfoJPanel.setBounds(25, 250, 250, 30);
        this.add(changeInfoJPanel);

        JPanel viewBookedJPanel = new JPanel();
        viewBookedJPanel.add(viewBookedJButton);
        viewBookedJPanel.setBackground(Color.LIGHT_GRAY);
        viewBookedJPanel.setBounds(25, 280, 250, 30);
        this.add(viewBookedJPanel);

        JPanel logoutJPanel = new JPanel();
        logoutJPanel.add(logoutJButton);
        logoutJPanel.setBackground(Color.LIGHT_GRAY);
        logoutJPanel.setBounds(25, 790, 250, 30);
        this.add(logoutJPanel);

        JPanel exitJPanel = new JPanel();
        exitJPanel.add(exitJButton);
        exitJPanel.setBackground(Color.LIGHT_GRAY);
        exitJPanel.setBounds(25, 820, 250, 30);
        this.add(exitJPanel);

        JPanel leftJPanel = new JPanel();
        leftJPanel.setBackground(Color.LIGHT_GRAY);
        leftJPanel.setBounds(0, 0, 300, 900);
        this.add(leftJPanel);

// --------------------------------------------------------------------------------------------

        JPanel typeJPanel = new JPanel();
        typeJComboBox.addItem("管理用户");
        typeJComboBox.addItem("管理图书");
        typeJPanel.add(typeJComboBox);
        typeJPanel.setBounds(325, 25, 120, 30);
        this.add(typeJPanel);

        JPanel searchJPanel = new JPanel();
        searchJPanel.add(searchJTextField);
        searchJPanel.add(searchJButton);
        searchJPanel.setBounds(325, 40, 1140, 30);
        this.add(searchJPanel);

        JPanel searchSelectJPanel = new JPanel();
        searchButtonGroup.add(isbnJRadioButton);
        searchButtonGroup.add(authorJRadioButton);
        searchButtonGroup.add(tagJRadioButton);
        searchButtonGroup.add(keywordJRadioButton);
        searchButtonGroup.add(fuzzyJRadioButton);
        searchSelectJPanel.add(isbnJRadioButton);
        searchSelectJPanel.add(authorJRadioButton);
        searchSelectJPanel.add(tagJRadioButton);
        searchSelectJPanel.add(keywordJRadioButton);
        searchSelectJPanel.add(fuzzyJRadioButton);
        searchSelectJPanel.setBounds(325, 80, 1140, 30);
        this.add(searchSelectJPanel);

        JPanel rightUpJPanel = new JPanel();
        rightUpJPanel.setBounds(300, 0, 1140, 150);
        this.add(rightUpJPanel);

// --------------------------------------------------------------------------------------------

        JPanel rightDownJPanel = new JPanel();
        rightDownJPanel.setBackground(Color.WHITE);
        rightDownJPanel.setBounds(300, 150, 1140, 750);
        this.add(rightDownJPanel);
                
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    @Override
    public void actionPerformed(ActionEvent event) {
    }
}
