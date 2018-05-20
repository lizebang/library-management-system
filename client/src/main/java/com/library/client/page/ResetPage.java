package com.library.client.page;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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
import com.library.client.util.Util;

public class ResetPage extends JFrame implements ActionListener {

    private String title;
    private JLabel usernameJLabel = new JLabel("姓名");
    private JTextField usernameJTextField = new JTextField(12);
    private JLabel phoneJLabel = new JLabel("手机");
    private JTextField phoneJTextField = new JTextField(12);
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
                JOptionPane.showMessageDialog(null, MessageDialog.InvalidPhone, MessageDialog.Fail, JOptionPane.ERROR_MESSAGE);
            } else if (username.equals("") || phone.equals("")) {
                JOptionPane.showMessageDialog(null, MessageDialog.IncompleteInformation, MessageDialog.Fail, JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    Util.resetPassword(username, phone);
                    JOptionPane.showMessageDialog(null, MessageDialog.ResetSuccess, MessageDialog.Success, JOptionPane.INFORMATION_MESSAGE);
                    new LoginPage(title);
                    this.dispose();
                }
                catch (LibraryException exception) {
                    Integer status = exception.getStatus();
                    if (status.equals(Constant.User_Not_Found)) {
                        JOptionPane.showMessageDialog(null, MessageDialog.InvalidInformation, MessageDialog.Fail, JOptionPane.ERROR_MESSAGE);
                    }
                }
                catch (JSONException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.JSONError, JOptionPane.ERROR_MESSAGE);
                }
                catch (IOException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.IOError, JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        
        if (event.getSource() == returnJButton) {
            new LoginPage(title);
            this.dispose();
        }
    }
}
