package com.library.client.page;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.json.JSONException;

import com.library.client.Constant;
import com.library.client.util.LibraryException;
import com.library.client.util.Checker;
import com.library.client.util.UserUtil;

public class ResetPage extends JFrame implements ActionListener {

    private String title;
    private JLabel usernameJLabel = new JLabel("姓名");
    private JTextField usernameJTextField = new JTextField(10);
    private JLabel phoneJLabel = new JLabel("手机");
    private JTextField phoneJTextField = new JTextField(10);
    private JButton resetJButton = new JButton("重置");
    private JButton returnJButton = new JButton("返回");
   
    public ResetPage(String title) {
        super(title);
        this.title = title;
        this.setSize(1440, 900);

        JPanel usernameJPanel = new JPanel();
        usernameJPanel.add(usernameJLabel);
        usernameJPanel.add(usernameJTextField);
        usernameJPanel.setBounds(470, 350, 500, 50);
        this.add(usernameJPanel);
        
        JPanel phoneJPanel = new JPanel();
        phoneJPanel.add(phoneJLabel);
        phoneJPanel.add(phoneJTextField);
        phoneJPanel.setBounds(470, 400, 500, 50);
        this.add(phoneJPanel);

        JPanel resetJPanel = new JPanel();
        resetJPanel.add(resetJButton);
        resetJPanel.add(returnJButton);
        resetJPanel.setBounds(470, 450, 500, 50);
        this.add(resetJPanel);

        JPanel endJPanel = new JPanel();
        this.add(endJPanel);

        resetJButton.addActionListener(this);
        returnJButton.addActionListener(this);
        
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    @Override
    public void actionPerformed(ActionEvent event) {
        String username = usernameJTextField.getText();
        String phone = phoneJTextField.getText();
        
        if (event.getSource() == resetJButton) {
            if (!Checker.isPhone(phone)) {
                JOptionPane.showMessageDialog(null, "请检查手机号是否正确", "失败", JOptionPane.ERROR_MESSAGE);
            } else if (username.equals("") || phone.equals("")) {
                JOptionPane.showMessageDialog(null, "请将信息填写完整", "失败", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    UserUtil.resetPassword(username, phone);
                    JOptionPane.showMessageDialog(null, "密码重置为 123456，登陆后请立即修改密码", "成功", JOptionPane.INFORMATION_MESSAGE);
                    new LoginPage(title);
                    this.dispose();
                }
                catch (JSONException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString(), "错误", JOptionPane.ERROR_MESSAGE);
                }
                catch (LibraryException exception) {
                    Integer status = exception.getStatus();
                    if (status.equals(Constant.User_Not_Found)) {
                        JOptionPane.showMessageDialog(null, "信息填写有误，请认真检查", "失败", JOptionPane.ERROR_MESSAGE);
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
