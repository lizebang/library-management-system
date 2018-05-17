package com.library.client.page;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.json.JSONException;

import com.library.client.Constant;
import com.library.client.util.LibraryException;
import com.library.client.util.UserUtil;
import com.library.client.models.User;

public class Login extends JFrame implements ActionListener {

    private JLabel usernameJLabel = new JLabel("username");
    private JTextField usernameJTextField = new JTextField(10);
    private JLabel passwordJLabel = new JLabel("password");
    private JPasswordField passwordJPasswordField = new JPasswordField(10);
    private JButton loginJButton = new JButton("log in");
    private JButton logupJButton = new JButton("log up");
    public Login(String title) {
        this.setSize(1440, 900);
        passwordJPasswordField.setEchoChar('*');

        JPanel upJPanel = new JPanel();
        upJPanel.add(usernameJLabel);
        upJPanel.add(usernameJTextField);
        upJPanel.add(logupJButton);
        upJPanel.setBounds(570, 350, 300, 50);
        this.add(upJPanel);
        
        JPanel downJPanel = new JPanel();
        downJPanel.add(passwordJLabel);
        downJPanel.add(passwordJPasswordField);
        downJPanel.add(loginJButton);
        downJPanel.setBounds(570, 400, 300, 50);
        this.add(downJPanel);

        JPanel endJPanel = new JPanel();
        this.add(endJPanel);

        usernameJTextField.addActionListener(this);
        passwordJPasswordField.addActionListener(this);
        loginJButton.addActionListener(this);
        logupJButton.addActionListener(this);
        
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    @Override
    public void actionPerformed(ActionEvent event) {
        String username = usernameJTextField.getText();
        String password = passwordJPasswordField.getText();
        if (event.getSource() == loginJButton) {
            try {
                User user = UserUtil.login(username, password);
                    JOptionPane.showMessageDialog(null, "Login"+user.getName()+user.getId(), "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (JSONException exception) {
                JOptionPane.showMessageDialog(null, exception.toString(), "Program Error", JOptionPane.ERROR_MESSAGE);
            } catch (LibraryException exception) {
                JOptionPane.showMessageDialog(null, "Incorrect username or password"+exception.toString(), "Fail", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (event.getSource() == logupJButton) {
            this.dispose();
            new Logup();
        }
    }
}
