package com.library.client.page;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.json.JSONException;

import com.library.client.Constant;
import com.library.client.util.LibraryException;
import com.library.client.util.Checker;
import com.library.client.util.UserUtil;

public class RegisterPage extends JFrame implements ActionListener {

    private String title;
    private JLabel usernameJLabel = new JLabel("姓名");
    private JTextField usernameJTextField = new JTextField(10);
    private JLabel passwordJLabel = new JLabel("密码");
    private JPasswordField passwordJPasswordField = new JPasswordField(10);
    private JLabel phoneJLabel = new JLabel("手机");
    private JTextField phoneJTextField = new JTextField(10);
    private JLabel sexJLabel = new JLabel("性别");
    private ButtonGroup sexButtonGroup = new ButtonGroup();
    private JRadioButton maleJRadioButton = new JRadioButton("男");
    private JRadioButton femaleJRadioButton = new JRadioButton("女");
    private JRadioButton noJRadioButton = new JRadioButton("保密");
    private JButton registerJButton = new JButton("注册");
    private JButton returnJButton = new JButton("返回");
   
    public RegisterPage(String title) {
        super(title);
        this.title = title;
        this.setSize(1440, 900);
        passwordJPasswordField.setEchoChar('*');

        JPanel usernameJPanel = new JPanel();
        usernameJPanel.add(usernameJLabel);
        usernameJPanel.add(usernameJTextField);
        usernameJPanel.setBounds(470, 250, 500, 50);
        this.add(usernameJPanel);
        
        JPanel passwordJPanel = new JPanel();
        passwordJPanel.add(passwordJLabel);
        passwordJPanel.add(passwordJPasswordField);
        passwordJPanel.setBounds(470, 300, 500, 50);
        this.add(passwordJPanel);

        JPanel phoneJPanel = new JPanel();
        phoneJPanel.add(phoneJLabel);
        phoneJPanel.add(phoneJTextField);
        phoneJPanel.setBounds(470, 350, 500, 50);
        this.add(phoneJPanel);

        JPanel sexJPanel = new JPanel();
        sexButtonGroup.add(maleJRadioButton);
        sexButtonGroup.add(femaleJRadioButton);
        sexButtonGroup.add(noJRadioButton);
        sexJPanel.add(sexJLabel);
        sexJPanel.add(maleJRadioButton);
        sexJPanel.add(femaleJRadioButton);
        sexJPanel.add(noJRadioButton);
        sexJPanel.setBounds(470, 400, 500, 50);
        this.add(sexJPanel);

        JPanel registerJPanel = new JPanel();
        registerJPanel.add(registerJButton);
        registerJPanel.add(returnJButton);
        registerJPanel.setBounds(470, 450, 500, 50);
        this.add(registerJPanel);

        JPanel endJPanel = new JPanel();
        this.add(endJPanel);

        registerJButton.addActionListener(this);
        returnJButton.addActionListener(this);
        
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    @Override
    public void actionPerformed(ActionEvent event) {
        String username = usernameJTextField.getText();
        String password = passwordJPasswordField.getText();
        String phone = phoneJTextField.getText();
        Integer sex = Constant.SexNo;
        
        if (maleJRadioButton.isSelected()) {
            sex = Constant.SexMale;
        }
        if (femaleJRadioButton.isSelected()) {
            sex = Constant.SexFemale;
        }
        if (noJRadioButton.isSelected()) {
            sex = Constant.SexNo;
        }

        if (event.getSource() == registerJButton) {
            if (!Checker.isPhone(phone)) {
                JOptionPane.showMessageDialog(null, "请检查手机号是否正确", "失败", JOptionPane.ERROR_MESSAGE);
            } else if (username.equals("") || password.equals("") || phone.equals("") || !(maleJRadioButton.isSelected() || femaleJRadioButton.isSelected() || noJRadioButton.isSelected())) {
                JOptionPane.showMessageDialog(null, "请将信息填写完整", "失败", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    UserUtil.addUser(username, sex, phone, password);
                    
                    JOptionPane.showMessageDialog(null, "注册用户成功", "成功", JOptionPane.INFORMATION_MESSAGE);
                    
                    new LoginPage(title);
                    this.dispose();
                }
                catch (JSONException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString(), "错误", JOptionPane.ERROR_MESSAGE);
                }
                catch (LibraryException exception) {
                    Integer status = exception.getStatus();
                    if (status.equals(Constant.Bad_Request)) {
                        JOptionPane.showMessageDialog(null, "信息填写有误，请认真检查", "失败", JOptionPane.ERROR_MESSAGE);
                    }
                    if (status.equals(Constant.Repeated)) {
                        JOptionPane.showMessageDialog(null, "手机号已被占用", "失败", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }

        if (event.getSource() == returnJButton) {
            new LoginPage(title);
            this.dispose();
        }
    }
}
