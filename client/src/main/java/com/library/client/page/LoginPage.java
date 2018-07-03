package com.library.client.page;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import org.json.JSONException;
import com.library.client.util.LibraryException;
import com.library.client.util.Checker;
import com.library.client.util.Util;
import com.library.client.Constant;
import com.library.client.models.User;

public class LoginPage extends JFrame implements ActionListener {

    private String title;
    private JLabel usernameJLabel = new JLabel("账号");
    private JTextField usernameJTextField = new JTextField(12);
    private JLabel passwordJLabel = new JLabel("密码");
    private JPasswordField passwordJPasswordField = new JPasswordField(12);
    private JButton loginJButton = new JButton("登陆");
    private JButton registerJButton = new JButton("用户注册");
    private JButton resetJButton = new JButton("重置密码");

    public LoginPage(String title) {
        super(title);
        this.title = title;
        this.setSize(1440, 900);
        passwordJPasswordField.setEchoChar('*');

        JPanel upJPanel = new JPanel();
        upJPanel.add(usernameJLabel);
        upJPanel.add(usernameJTextField);
        upJPanel.add(registerJButton);
        upJPanel.setBounds(470, 350, 500, 50);
        this.add(upJPanel);

        JPanel downJPanel = new JPanel();
        downJPanel.add(passwordJLabel);
        downJPanel.add(passwordJPasswordField);
        downJPanel.add(resetJButton);
        downJPanel.setBounds(470, 400, 500, 50);
        this.add(downJPanel);

        JPanel loginJPanel = new JPanel();
        loginJPanel.add(loginJButton);
        loginJPanel.setBounds(470, 450, 500, 50);
        this.add(loginJPanel);

        JPanel endJPanel = new JPanel();
        this.add(endJPanel);

        loginJButton.addActionListener(this);
        registerJButton.addActionListener(this);
        resetJButton.addActionListener(this);

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String username = usernameJTextField.getText();
        String password = passwordJPasswordField.getText();

        if (event.getSource() == loginJButton) {
            if (!Checker.isPhone(username)) {
                JOptionPane.showMessageDialog(null, MessageDialog.InvalidName, MessageDialog.Fail,
                        JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    User user = Util.login(username, password);

                    Integer isAdmin = user.getIsAdmin();
                    if (isAdmin.equals(Constant.NormalUser)) {
                        new UserPage(title, user);
                        this.dispose();
                    }

                    if (isAdmin.equals(Constant.AdminUser)) {
                        new AdminPage(title, user);
                        this.dispose();
                    }
                } catch (LibraryException exception) {
                    JOptionPane.showMessageDialog(null, MessageDialog.LoginFail, MessageDialog.Fail,
                            JOptionPane.ERROR_MESSAGE);
                } catch (JSONException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString(),
                            MessageDialog.JSONError, JOptionPane.ERROR_MESSAGE);
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.IOError,
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        if (event.getSource() == registerJButton) {
            new RegisterPage(title);
            this.dispose();
        }

        if (event.getSource() == resetJButton) {
            new ResetPage(title);
            this.dispose();
        }
    }
}
