package com.library.client.page;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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
import com.library.client.util.Checker;
import com.library.client.util.Util;

public class UserInfoPage extends JFrame implements ActionListener {

    private String title;
    private User user;
    private JLabel usernameUP;
    private JLabel sexUP;
    private JLabel phoneUP;
    private JPanel usernameJPanel = new JPanel();
    private JPanel sexJPanel = new JPanel();
    private JPanel phoneJPanel = new JPanel();
    private JPanel oldPasswordJPanel = new JPanel();
    private JPanel newPasswordJPanel = new JPanel();
    private JComboBox typeJComboBox=new JComboBox();
    private JLabel changeJLabel = new JLabel("更改内容");
    private JLabel usernameJLabel = new JLabel("新姓名");
    private JTextField usernameJTextField = new JTextField(12);
    private JLabel sexJLabel = new JLabel("性别");
    private ButtonGroup sexButtonGroup = new ButtonGroup();
    private JRadioButton maleJRadioButton = new JRadioButton("男");
    private JRadioButton femaleJRadioButton = new JRadioButton("女");
    private JRadioButton noJRadioButton = new JRadioButton("保密");
    private JLabel newPhoneJLabel = new JLabel("新手机");
    private JTextField newPhoneJTextField = new JTextField(12);
    private JLabel oldPasswordJLabel = new JLabel("旧密码");
    private JPasswordField oldPasswordJPasswordField = new JPasswordField(12);
    private JLabel newPasswordJLabel = new JLabel("新密码");
    private JPasswordField newPasswordJPasswordField = new JPasswordField(12);
    private JButton confirmJButton = new JButton("确认");

    public UserInfoPage(String title, User user, JLabel usernameUP, JLabel sexUP, JLabel phoneUP) {
        super(title);
        this.title = title;
        this.user = user;
        this.usernameUP = usernameUP;
        this.sexUP = sexUP;
        this.phoneUP = phoneUP;
        this.setBounds(520, 325, 400, 250);
        oldPasswordJPasswordField.setEchoChar('*');
        newPasswordJPasswordField.setEchoChar('*');

        JPanel nameJPanel = new JPanel();
        JLabel nameJLabel = new JLabel("姓名: " + user.getName());
        JLabel idJLabel = new JLabel(" ID: " + user.getId());
        nameJLabel.setForeground(Color.BLUE);
        idJLabel.setForeground(Color.BLUE);
        nameJPanel.add(nameJLabel);
        nameJPanel.add(idJLabel);
        nameJPanel.setBounds(75, 25, 250, 30);
        this.add(nameJPanel);

        JPanel typeJPanel = new JPanel();
        typeJPanel.add(changeJLabel);
        typeJComboBox.addItem("信息");
        typeJComboBox.addItem("手机");
        typeJComboBox.addItem("密码");
        typeJPanel.add(typeJComboBox);
        typeJPanel.setBounds(75, 55, 250, 30);
        this.add(typeJPanel);

        usernameJPanel.add(usernameJLabel);
        usernameJPanel.add(usernameJTextField);
        usernameJTextField.setText(user.getName());
        usernameJPanel.setBounds(75, 85, 250, 30);
        this.add(usernameJPanel);

        sexButtonGroup.add(maleJRadioButton);
        sexButtonGroup.add(femaleJRadioButton);
        sexButtonGroup.add(noJRadioButton);
        sexJPanel.add(sexJLabel);
        sexJPanel.add(maleJRadioButton);
        sexJPanel.add(femaleJRadioButton);
        sexJPanel.add(noJRadioButton);
        if (user.getSex() == Constant.SexNo) {
            noJRadioButton.setSelected(true);
        } else if (user.getSex() == Constant.SexMale) {
            maleJRadioButton.setSelected(true);
        } else if (user.getSex() == Constant.SexFemale) {
            femaleJRadioButton.setSelected(true);
        }
        sexJPanel.setBounds(75, 115, 250, 50);
        this.add(sexJPanel);

        phoneJPanel.add(newPhoneJLabel);
        phoneJPanel.add(newPhoneJTextField);
        phoneJPanel.setBounds(75, 100, 250, 30);
        phoneJPanel.setVisible(false);
        this.add(phoneJPanel);

        oldPasswordJPanel.add(oldPasswordJLabel);
        oldPasswordJPanel.add(oldPasswordJPasswordField);
        oldPasswordJPanel.setBounds(75, 85, 250, 30);
        oldPasswordJPanel.setVisible(false);
        this.add(oldPasswordJPanel);

        newPasswordJPanel.add(newPasswordJLabel);
        newPasswordJPanel.add(newPasswordJPasswordField);
        newPasswordJPanel.setBounds(75, 115, 250, 30);
        newPasswordJPanel.setVisible(false);
        this.add(newPasswordJPanel);

        JPanel buttonJPanel = new JPanel();
        buttonJPanel.add(confirmJButton);
        buttonJPanel.setBounds(75, 165, 250, 30);
        this.add(buttonJPanel);
        

        JPanel endJPanel = new JPanel();
        this.add(endJPanel);

        confirmJButton.addActionListener(this);
        typeJComboBox.addActionListener(this);
        
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String username = usernameJTextField.getText();
        Integer sex = -1;
        String newPhone = newPhoneJTextField.getText();
        String oldPassword = oldPasswordJPasswordField.getText();
        String newPassword = newPasswordJPasswordField.getText();

        if (maleJRadioButton.isSelected()) {
            sex = Constant.SexMale;
        }
        if (femaleJRadioButton.isSelected()) {
            sex = Constant.SexFemale;
        }
        if (noJRadioButton.isSelected()) {
            sex = Constant.SexNo;
        }

        if (event.getSource() == confirmJButton) {
            switch (typeJComboBox.getSelectedIndex()) {
                case 0:
                if (username.equals("") || sex == -1) {
                    JOptionPane.showMessageDialog(null, MessageDialog.IncompleteInformation, MessageDialog.Fail, JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        Util.changeInfo(username, String.valueOf(sex));
                        usernameUP.removeAll();
                        usernameUP.setText("姓名: " + username);
                        sexUP.removeAll();
                        sexUP.setText("性别: " + User.getSex(sex));
                        JOptionPane.showMessageDialog(null, MessageDialog.ChangeInformationSuccess, MessageDialog.Success, JOptionPane.INFORMATION_MESSAGE);
                        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    } catch (LibraryException exception) {
                        Integer status = exception.getStatus();
                        if (status.equals(Constant.Bad_Request)) {
                            JOptionPane.showMessageDialog(null, MessageDialog.InvalidInformation, MessageDialog.Fail, JOptionPane.ERROR_MESSAGE);
                        }
                        if (status.equals(Constant.Not_Login)) {
                            JOptionPane.showMessageDialog(null, MessageDialog.NotLogin, MessageDialog.Fail, JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (JSONException exception) {
                        JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.JSONError, JOptionPane.ERROR_MESSAGE);
                    } catch (IOException exception) {
                        JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.IOError, JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
                
                case 1:
                if (newPhone.equals("")) {
                    JOptionPane.showMessageDialog(null, MessageDialog.InvalidPhone, MessageDialog.Fail, JOptionPane.ERROR_MESSAGE);
                } else if (!Checker.isPhone(newPhone)) {
                    JOptionPane.showMessageDialog(null, MessageDialog.InvalidPhone, MessageDialog.Fail, JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        Util.changePhone(newPhone);
                        phoneUP.removeAll();
                        phoneUP.setText("手机号: " + newPhone);
                        JOptionPane.showMessageDialog(null, MessageDialog.ChangePhoneSuccess, MessageDialog.Success, JOptionPane.INFORMATION_MESSAGE);
                        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    } catch (LibraryException exception) {
                        Integer status = exception.getStatus();
                        if (status.equals(Constant.Bad_Request)) {
                            JOptionPane.showMessageDialog(null, MessageDialog.InvalidInformation, MessageDialog.Fail, JOptionPane.ERROR_MESSAGE);
                        }
                        if (status.equals(Constant.Repeated)) {
                            JOptionPane.showMessageDialog(null, MessageDialog.PhoneRepeated, MessageDialog.Fail, JOptionPane.ERROR_MESSAGE);
                        }
                        if (status.equals(Constant.Not_Login)) {
                            JOptionPane.showMessageDialog(null, MessageDialog.NotLogin, MessageDialog.Fail, JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (JSONException exception) {
                        JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.JSONError, JOptionPane.ERROR_MESSAGE);
                    } catch (IOException exception) {
                        JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.IOError, JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
                
                case 2:
                if (oldPassword.equals("") || newPassword.equals("")) {
                    JOptionPane.showMessageDialog(null, MessageDialog.IncompleteInformation, MessageDialog.Fail, JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        Util.changePassword(oldPassword, newPassword);
                        JOptionPane.showMessageDialog(null, MessageDialog.ChangePasswordSuccess, MessageDialog.Success, JOptionPane.INFORMATION_MESSAGE);
                        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    } catch (LibraryException exception) {
                        Integer status = exception.getStatus();
                        if (status.equals(Constant.Wrong_Password)) {
                            JOptionPane.showMessageDialog(null, MessageDialog.InvalidPassword, MessageDialog.Fail, JOptionPane.ERROR_MESSAGE);
                        }
                        if (status.equals(Constant.Not_Login)) {
                            JOptionPane.showMessageDialog(null, MessageDialog.NotLogin, MessageDialog.Fail, JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (JSONException exception) {
                        JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.JSONError, JOptionPane.ERROR_MESSAGE);
                    } catch (IOException exception) {
                        JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.IOError, JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
            }
        }

        if (typeJComboBox.getSelectedIndex() == 0) {
            usernameJPanel.setVisible(true);
            sexJPanel.setVisible(true);
            phoneJPanel.setVisible(false);
            oldPasswordJPanel.setVisible(false);
            newPasswordJPanel.setVisible(false);
        }
        if (typeJComboBox.getSelectedIndex() == 1) {
            usernameJPanel.setVisible(false);
            sexJPanel.setVisible(false);
            phoneJPanel.setVisible(true);
            oldPasswordJPanel.setVisible(false);
            newPasswordJPanel.setVisible(false);
        }
        if (typeJComboBox.getSelectedIndex() == 2) {
            usernameJPanel.setVisible(false);
            sexJPanel.setVisible(false);
            phoneJPanel.setVisible(false);
            oldPasswordJPanel.setVisible(true);
            newPasswordJPanel.setVisible(true);
        }
    }
}
