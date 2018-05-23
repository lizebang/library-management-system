package com.library.client.page;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.json.JSONException;

import com.library.client.Constant;
import com.library.client.models.Book;
import com.library.client.models.Event;
import com.library.client.models.User;
import com.library.client.util.LibraryException;
import com.library.client.util.Util;

public class UserPage extends JFrame implements ActionListener {

    private String title;
    private User user;

    protected JLabel usernameJLabel = new JLabel();
    protected JLabel sexJLabel = new JLabel();
    protected JLabel phoneJLabel = new JLabel();
    private JLabel isAdminJLabel = new JLabel();
    private JLabel amountJLabel = new JLabel();

    private JButton changeInfoJButton = new JButton("修改用户信息");
    private JButton viewBookedJButton = new JButton("查看借阅书籍");
    private JButton searchPanelJButton = new JButton("返回搜索界面");
    private JButton logoutJButton = new JButton("用户登出");
    private JButton exitJButton = new JButton("退出系统");

    private JTextField searchJTextField = new JTextField(50);
    private JButton searchJButton = new JButton("搜 索");
    private JButton refreshJButton = new JButton("刷 新");
    private ButtonGroup bookSearchButtonGroup = new ButtonGroup();
    private JRadioButton isbnJRadioButton = new JRadioButton("ISBN");
    private JRadioButton authorJRadioButton = new JRadioButton("作 者");
    private JRadioButton tagJRadioButton = new JRadioButton("类 别");
    private JRadioButton keywordJRadioButton = new JRadioButton("关键字");
    private JRadioButton fuzzyJRadioButton = new JRadioButton("模糊查询");

    private int page;
    private int totalPages;
    
    private JPanel bookJPanel = new JPanel();
    private JScrollPane bookJScrollPane = new JScrollPane();
    private JTable bookJTable;
    private Object[][] books;
    private DefaultTableModel bookDefaultTableModel = new DefaultTableModel() {
        @Override
        public Object getValueAt(int row, int column) {
            return books[row][column];
        }

        @Override
        public int getColumnCount() {
            return 9;
        }

        @Override
        public void setValueAt(Object value, int row, int column) {
            books[row][column] = value;
            fireTableCellUpdated(row, column);
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            if (column == 8) {
                return true;
            } else {
                return false;
            }
        }
    };

    private JPanel eventJPanel = new JPanel();
    private JScrollPane eventJScrollPane = new JScrollPane();
    private JTable eventJTable;
    private Object[][] events;
    private DefaultTableModel eventDefaultTableModel = new DefaultTableModel() {
        @Override
        public Object getValueAt(int row, int column) {
            return events[row][column];
        }

        @Override
        public int getColumnCount() {
            return 6;
        }

        @Override
        public void setValueAt(Object value, int row, int column){
            events[row][column] = value;
            fireTableCellUpdated(row, column);
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            if (column == 5) {
                return true;
            } else {
        return false;
            }
        }
    };

    private JButton previousBookPageJButton = new JButton("上一页");
    private JButton nextBookPageJButton = new JButton("下一页");
    private JTextField goToJTextField = new JTextField(2);
    private JLabel totalPagesJLabel = new JLabel();
    private JButton goToJButton = new JButton("转 到");

    public UserPage(String title, User user) {
        super(title);
        this.title = title;
        this.user = user;
        this.setBounds(0, 0, 1440, 900);
        
        JPanel usernameAndSexJPanel = new JPanel();
        usernameJLabel.setText("姓名: " + user.getName());
        usernameAndSexJPanel.add(usernameJLabel);
        sexJLabel.setText("性别: " + User.getSex(user.getSex()));
        usernameAndSexJPanel.add(sexJLabel);
        usernameAndSexJPanel.setBackground(Color.LIGHT_GRAY);
        usernameAndSexJPanel.setBounds(25, 100, 250, 50);
        this.add(usernameAndSexJPanel);

        JPanel phoneJPanel = new JPanel();
        phoneJLabel.setText("手机号: " + user.getPhone());
        phoneJPanel.add(phoneJLabel);
        phoneJPanel.setBackground(Color.LIGHT_GRAY);
        phoneJPanel.setBounds(25, 150, 250, 50);
        this.add(phoneJPanel);

        JPanel isAdminAndAmountJPanel = new JPanel();
        isAdminJLabel.setText("账号类型: " + User.getIsAdmin(user.getIsAdmin()));
        isAdminAndAmountJPanel.add(isAdminJLabel);
        amountJLabel.setText("已借: " + user.getAmount());
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

        JPanel searchPanelJPanel = new JPanel();
        searchPanelJPanel.add(searchPanelJButton);
        searchPanelJPanel.setBackground(Color.LIGHT_GRAY);
        searchPanelJPanel.setBounds(25, 310, 250, 30);
        this.add(searchPanelJPanel);

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

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        JPanel searchJPanel = new JPanel();
        searchJPanel.add(searchJTextField);
        searchJPanel.add(searchJButton);
        searchJPanel.setBounds(325, 40, 1140, 30);
        this.add(searchJPanel);

        JPanel bookSearchSelectJPanel = new JPanel();
        bookSearchButtonGroup.add(isbnJRadioButton);
        bookSearchButtonGroup.add(authorJRadioButton);
        bookSearchButtonGroup.add(tagJRadioButton);
        bookSearchButtonGroup.add(keywordJRadioButton);
        bookSearchButtonGroup.add(fuzzyJRadioButton);
        bookSearchSelectJPanel.add(isbnJRadioButton);
        bookSearchSelectJPanel.add(authorJRadioButton);
        bookSearchSelectJPanel.add(tagJRadioButton);
        bookSearchSelectJPanel.add(keywordJRadioButton);
        bookSearchSelectJPanel.add(fuzzyJRadioButton);
        bookSearchSelectJPanel.setBounds(325, 80, 1140, 30);
        this.add(bookSearchSelectJPanel);

        JPanel refreshJPanel = new JPanel();
        refreshJPanel.add(refreshJButton);
        refreshJPanel.setBounds(1300, 100, 100, 50);
        this.add(refreshJPanel);

        JPanel rightUpJPanel = new JPanel();
        rightUpJPanel.setBounds(300, 0, 1140, 150);
        this.add(rightUpJPanel);

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        try {
            this.page = 1;
            Book[] bs = Util.getAllBook(this.page);
            if (bs.length > 0) {
                goToJTextField.setText(String.valueOf(1));
                this.totalPages = bs[0].getTotalPages();
            }
            books = new Object[bs.length][];
            for (int i = 0; i < bs.length; i++) {
                this.books[i] = bs[i].toObject();
            }
        } catch (LibraryException exception) {
            System.err.println("catch (LibraryException exception)" + exception);
        } catch (JSONException exception) {
            System.err.println("catch (JSONException exception)" + exception);
        } catch (IOException exception) {
            System.err.println("catch (IOException exception)" + exception);
        }

        bookJPanel.setBackground(Color.WHITE);
        bookJPanel.setBounds(300, 150, 1140, 649);
        this.getContentPane().add(bookJPanel);
        bookJPanel.setLayout(null);

        bookJScrollPane.setBackground(Color.WHITE);
        bookJScrollPane.setBounds(20, 20, 1100, 630);
        bookJPanel.add(bookJScrollPane);


        bookDefaultTableModel.setDataVector(books, new Object[]{"ID", "ISBN", "书名", "作者", "标签", "总数", "库存", "简介", "操作"});
        bookJTable = new JTable(bookDefaultTableModel);
        bookJScrollPane.setViewportView(bookJTable);
        bookJTable.getColumnModel().getColumn(8).setCellEditor(new BookButtonEditor(bookJTable, Constant.BorrowBook));
        bookJTable.getColumnModel().getColumn(8).setCellRenderer(new BookButtonRenderer(Constant.BorrowBook));

        bookJTable.setRowSelectionAllowed(false);

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        eventJPanel.setBackground(Color.WHITE);
        eventJPanel.setBounds(300, 150, 1140, 649);
        this.getContentPane().add(eventJPanel);
        eventJPanel.setLayout(null);

        eventJScrollPane.setBackground(Color.WHITE);
        eventJScrollPane.setBounds(20, 20, 1100, 630);
        eventJPanel.add(eventJScrollPane);

        eventDefaultTableModel.setDataVector(events, new Object[]{"ID", "ISBN", "书名", "作者", "标签", "操作"});
        eventJTable = new JTable(eventDefaultTableModel);
        eventJScrollPane.setViewportView(eventJTable);
        eventJTable.getColumnModel().getColumn(5).setCellEditor(new BookButtonEditor(eventJTable, Constant.ReturnBook));
        eventJTable.getColumnModel().getColumn(5).setCellRenderer(new BookButtonRenderer(Constant.ReturnBook));

        eventJTable.setRowSelectionAllowed(false);

        eventJPanel.setVisible(false);

        JPanel goToJPanel = new JPanel();
        goToJPanel.add(previousBookPageJButton);
        goToJPanel.add(goToJTextField);
        totalPagesJLabel.setText(String.format("/%d", this.totalPages));
        goToJPanel.add(totalPagesJLabel);
        goToJPanel.add(nextBookPageJButton);
        goToJPanel.add(goToJButton);
        goToJPanel.setBackground(Color.WHITE);
        goToJPanel.setBounds(300, 805, 1140, 50);
        this.add(goToJPanel);

        JPanel rightDownJPanel = new JPanel();
        rightDownJPanel.setBackground(Color.WHITE);
        rightDownJPanel.setBounds(300, 150, 1140, 750);
        this.add(rightDownJPanel);

        JPanel endJPanel = new JPanel();
        this.add(endJPanel);

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        changeInfoJButton.addActionListener(this);
        logoutJButton.addActionListener(this);
        exitJButton.addActionListener(this);
        
        viewBookedJButton.addActionListener(this);
        searchPanelJButton.addActionListener(this);

        searchJButton.addActionListener(this);
        refreshJButton.addActionListener(this);

        previousBookPageJButton.addActionListener(this);
        nextBookPageJButton.addActionListener(this);
        goToJButton.addActionListener(this);

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        String search = searchJTextField.getText();
        String goTo = goToJTextField.getText();

        if (ae.getSource() == changeInfoJButton) {
            new UserInfoPage(title, user, usernameJLabel, sexJLabel, phoneJLabel);
        }

        if (ae.getSource() == logoutJButton) {
            try {
                Util.logout();
            } catch (LibraryException exception) {
                JOptionPane.showMessageDialog(null, MessageDialog.NotLogin, MessageDialog.Fail, JOptionPane.ERROR_MESSAGE);
            } catch (JSONException exception) {
                JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.JSONError, JOptionPane.ERROR_MESSAGE);
            } catch (IOException exception) {
                JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.IOError, JOptionPane.ERROR_MESSAGE);
            }
            new LoginPage(title);
            this.dispose();
        }

        if (ae.getSource() == exitJButton) {
            try {
                Util.logout();
            } catch (LibraryException exception) {
                JOptionPane.showMessageDialog(null, MessageDialog.NotLogin, MessageDialog.Fail, JOptionPane.ERROR_MESSAGE);
            } catch (JSONException exception) {
                JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.JSONError, JOptionPane.ERROR_MESSAGE);
            } catch (IOException exception) {
                JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.IOError, JOptionPane.ERROR_MESSAGE);
            }
            this.dispose();
            System.exit(0);
        }

        if (ae.getSource() == viewBookedJButton) {
            try {
                Event[] es = Util.getEventByPhone();

                events = new Object[es.length][];
                for (int i = 0; i < es.length; i++) {
                    events[i] = es[i].toObject();
                }

                goToJTextField.setText(String.valueOf(1));
                totalPagesJLabel.setText(String.format("/%d", 1));

                eventJScrollPane.remove(eventJTable);
                eventDefaultTableModel.setDataVector(events, new Object[]{"ID", "ISBN", "书名", "作者", "标签", "操作"});
                eventJTable = new JTable(eventDefaultTableModel);
                eventJScrollPane.setViewportView(eventJTable);
                eventJTable.getColumnModel().getColumn(5).setCellEditor(new BookButtonEditor(eventJTable, Constant.ReturnBook));
                eventJTable.getColumnModel().getColumn(5).setCellRenderer(new BookButtonRenderer(Constant.ReturnBook));
        
                eventJTable.setRowSelectionAllowed(false);

                bookJPanel.setVisible(false);
                eventJPanel.setVisible(true);
            } catch (LibraryException exception) {
                JOptionPane.showMessageDialog(null, MessageDialog.NotLogin, MessageDialog.Fail, JOptionPane.ERROR_MESSAGE);
            } catch (JSONException exception) {
                JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.JSONError, JOptionPane.ERROR_MESSAGE);
            } catch (IOException exception) {
                JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.IOError, JOptionPane.ERROR_MESSAGE);
            }
        }

        if (ae.getSource() == searchPanelJButton) {
            goToJTextField.setText(String.valueOf(page));
            totalPagesJLabel.setText(String.format("/%d", this.totalPages));
            bookJPanel.setVisible(true);
            eventJPanel.setVisible(false);
        }

        if (ae.getSource() == searchJButton) {
            if (search.isEmpty()) {
                try {
                    Book[] bs = Util.getAllBook(1);
                    if (bs.length > 0) {
                        this.totalPages = bs[0].getTotalPages();
                    }
                    goToJTextField.setText(String.valueOf(1));
                    totalPagesJLabel.setText(String.format("/%d", 1));
    
                    books = new Object[bs.length][];
                    for (int i = 0; i < bs.length; i++) {
                        books[i] = bs[i].toObject();
                    }
    
                    bookJScrollPane.remove(bookJTable);
                    bookDefaultTableModel.setDataVector(books, new Object[]{"ID", "ISBN", "书名", "作者", "标签", "总数", "库存", "简介", "操作"});
                    bookJTable = new JTable(bookDefaultTableModel);
                    bookJScrollPane.setViewportView(bookJTable);
                    bookJTable.getColumnModel().getColumn(8).setCellEditor(new BookButtonEditor(bookJTable, Constant.BorrowBook));
                    bookJTable.getColumnModel().getColumn(8).setCellRenderer(new BookButtonRenderer(Constant.BorrowBook));
            
                    bookJTable.setRowSelectionAllowed(false);
    
                    eventJPanel.setVisible(false);
                    bookJPanel.setVisible(true);
                } catch (LibraryException exception) {
                    // todo:
                    JOptionPane.showMessageDialog(null, MessageDialog.NotLogin, MessageDialog.Fail, JOptionPane.ERROR_MESSAGE);
                } catch (JSONException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.JSONError, JOptionPane.ERROR_MESSAGE);
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.IOError, JOptionPane.ERROR_MESSAGE);
                }
            }

            if (isbnJRadioButton.isSelected()) {
                try {
                    Book b = Util.getBookByIsbn(search);
                    this.page = 1;
                    this.totalPages = 1;
                    goToJTextField.setText(String.valueOf(1));
                    totalPagesJLabel.setText(String.format("/%d", 1));
    
                    books = new Object[1][];
                    books[0] = b.toObject();
    
                    bookJScrollPane.remove(bookJTable);
                    bookDefaultTableModel.setDataVector(books, new Object[]{"ID", "ISBN", "书名", "作者", "标签", "总数", "库存", "简介", "操作"});
                    bookJTable = new JTable(bookDefaultTableModel);
                    bookJScrollPane.setViewportView(bookJTable);
                    bookJTable.getColumnModel().getColumn(8).setCellEditor(new BookButtonEditor(bookJTable, Constant.BorrowBook));
                    bookJTable.getColumnModel().getColumn(8).setCellRenderer(new BookButtonRenderer(Constant.BorrowBook));
            
                    bookJTable.setRowSelectionAllowed(false);
    
                    eventJPanel.setVisible(false);
                    bookJPanel.setVisible(true);
                } catch (LibraryException exception) {
                    // todo:
                    JOptionPane.showMessageDialog(null, MessageDialog.NotLogin, MessageDialog.Fail, JOptionPane.ERROR_MESSAGE);
                } catch (JSONException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.JSONError, JOptionPane.ERROR_MESSAGE);
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.IOError, JOptionPane.ERROR_MESSAGE);
                }
            }
            
            if (authorJRadioButton.isSelected()) {
                try {
                    Book[] bs = Util.getBookByAuthor(search, 1);
                    if (bs.length > 0) {
                        this.page = 1;
                        this.totalPages = bs[0].getTotalPages();
                    }
                    goToJTextField.setText(String.valueOf(1));
                    totalPagesJLabel.setText(String.format("/%d", 1));
    
                    books = new Object[bs.length][];
                    for (int i = 0; i < bs.length; i++) {
                        books[i] = bs[i].toObject();
                    }
    
                    bookJScrollPane.remove(bookJTable);
                    bookDefaultTableModel.setDataVector(books, new Object[]{"ID", "ISBN", "书名", "作者", "标签", "总数", "库存", "简介", "操作"});
                    bookJTable = new JTable(bookDefaultTableModel);
                    bookJScrollPane.setViewportView(bookJTable);
                    bookJTable.getColumnModel().getColumn(8).setCellEditor(new BookButtonEditor(bookJTable, Constant.BorrowBook));
                    bookJTable.getColumnModel().getColumn(8).setCellRenderer(new BookButtonRenderer(Constant.BorrowBook));
            
                    bookJTable.setRowSelectionAllowed(false);
    
                    eventJPanel.setVisible(false);
                    bookJPanel.setVisible(true);
                } catch (LibraryException exception) {
                    // todo:
                    JOptionPane.showMessageDialog(null, MessageDialog.NotLogin, MessageDialog.Fail, JOptionPane.ERROR_MESSAGE);
                } catch (JSONException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.JSONError, JOptionPane.ERROR_MESSAGE);
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.IOError, JOptionPane.ERROR_MESSAGE);
                }
            }

            if (tagJRadioButton.isSelected()) {
                try {
                    Book[] bs = Util.getBookByTag(search, 1);
                    if (bs.length > 0) {
                        this.page = 1;
                        this.totalPages = bs[0].getTotalPages();
                    }
                    goToJTextField.setText(String.valueOf(1));
                    totalPagesJLabel.setText(String.format("/%d", 1));
    
                    books = new Object[bs.length][];
                    for (int i = 0; i < bs.length; i++) {
                        books[i] = bs[i].toObject();
                    }
    
                    bookJScrollPane.remove(bookJTable);
                    bookDefaultTableModel.setDataVector(books, new Object[]{"ID", "ISBN", "书名", "作者", "标签", "总数", "库存", "简介", "操作"});
                    bookJTable = new JTable(bookDefaultTableModel);
                    bookJScrollPane.setViewportView(bookJTable);
                    bookJTable.getColumnModel().getColumn(8).setCellEditor(new BookButtonEditor(bookJTable, Constant.BorrowBook));
                    bookJTable.getColumnModel().getColumn(8).setCellRenderer(new BookButtonRenderer(Constant.BorrowBook));
            
                    bookJTable.setRowSelectionAllowed(false);
    
                    eventJPanel.setVisible(false);
                    bookJPanel.setVisible(true);
                } catch (LibraryException exception) {
                    // todo:
                    JOptionPane.showMessageDialog(null, MessageDialog.NotLogin, MessageDialog.Fail, JOptionPane.ERROR_MESSAGE);
                } catch (JSONException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.JSONError, JOptionPane.ERROR_MESSAGE);
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.IOError, JOptionPane.ERROR_MESSAGE);
                }
            }

            if (keywordJRadioButton.isSelected()) {
                try {
                    Book[] bs = Util.getBookByKeyword(search, 1);
                    if (bs.length > 0) {
                        this.page = 1;
                        this.totalPages = bs[0].getTotalPages();
                    }
                    goToJTextField.setText(String.valueOf(1));
                    totalPagesJLabel.setText(String.format("/%d", 1));
    
                    books = new Object[bs.length][];
                    for (int i = 0; i < bs.length; i++) {
                        books[i] = bs[i].toObject();
                    }
    
                    bookJScrollPane.remove(bookJTable);
                    bookDefaultTableModel.setDataVector(books, new Object[]{"ID", "ISBN", "书名", "作者", "标签", "总数", "库存", "简介", "操作"});
                    bookJTable = new JTable(bookDefaultTableModel);
                    bookJScrollPane.setViewportView(bookJTable);
                    bookJTable.getColumnModel().getColumn(8).setCellEditor(new BookButtonEditor(bookJTable, Constant.BorrowBook));
                    bookJTable.getColumnModel().getColumn(8).setCellRenderer(new BookButtonRenderer(Constant.BorrowBook));
            
                    bookJTable.setRowSelectionAllowed(false);
    
                    eventJPanel.setVisible(false);
                    bookJPanel.setVisible(true);
                } catch (LibraryException exception) {
                    // todo:
                    JOptionPane.showMessageDialog(null, MessageDialog.NotLogin, MessageDialog.Fail, JOptionPane.ERROR_MESSAGE);
                } catch (JSONException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.JSONError, JOptionPane.ERROR_MESSAGE);
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.IOError, JOptionPane.ERROR_MESSAGE);
                }
            }

            if (fuzzyJRadioButton.isSelected()) {
                try {
                    Book[] bs = Util.getBookFuzzy(search, 1);
                    if (bs.length > 0) {
                        this.page = 1;
                        this.totalPages = bs[0].getTotalPages();
                    }
                    goToJTextField.setText(String.valueOf(1));
                    totalPagesJLabel.setText(String.format("/%d", 1));
    
                    books = new Object[bs.length][];
                    for (int i = 0; i < bs.length; i++) {
                        books[i] = bs[i].toObject();
                    }
    
                    bookJScrollPane.remove(bookJTable);
                    bookDefaultTableModel.setDataVector(books, new Object[]{"ID", "ISBN", "书名", "作者", "标签", "总数", "库存", "简介", "操作"});
                    bookJTable = new JTable(bookDefaultTableModel);
                    bookJScrollPane.setViewportView(bookJTable);
                    bookJTable.getColumnModel().getColumn(8).setCellEditor(new BookButtonEditor(bookJTable, Constant.BorrowBook));
                    bookJTable.getColumnModel().getColumn(8).setCellRenderer(new BookButtonRenderer(Constant.BorrowBook));
            
                    bookJTable.setRowSelectionAllowed(false);
    
                    eventJPanel.setVisible(false);
                    bookJPanel.setVisible(true);
                } catch (LibraryException exception) {
                    // todo:
                    JOptionPane.showMessageDialog(null, MessageDialog.NotLogin, MessageDialog.Fail, JOptionPane.ERROR_MESSAGE);
                } catch (JSONException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.JSONError, JOptionPane.ERROR_MESSAGE);
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.IOError, JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        if (ae.getSource() == refreshJButton) {
            if (search.isEmpty()) {
                try {
                    Book[] bs = Util.getAllBook(this.page);
                    if (bs.length > 0) {
                        this.totalPages = bs[0].getTotalPages();
                    }
                    goToJTextField.setText(String.valueOf(this.page));
                    totalPagesJLabel.setText(String.format("/%d", this.totalPages));
    
                    books = new Object[bs.length][];
                    for (int i = 0; i < bs.length; i++) {
                        books[i] = bs[i].toObject();
                    }
    
                    bookJScrollPane.remove(bookJTable);
                    bookDefaultTableModel.setDataVector(books, new Object[]{"ID", "ISBN", "书名", "作者", "标签", "总数", "库存", "简介", "操作"});
                    bookJTable = new JTable(bookDefaultTableModel);
                    bookJScrollPane.setViewportView(bookJTable);
                    bookJTable.getColumnModel().getColumn(8).setCellEditor(new BookButtonEditor(bookJTable, Constant.BorrowBook));
                    bookJTable.getColumnModel().getColumn(8).setCellRenderer(new BookButtonRenderer(Constant.BorrowBook));
            
                    bookJTable.setRowSelectionAllowed(false);
        
                    eventJPanel.setVisible(false);
                    bookJPanel.setVisible(true);
                } catch (LibraryException exception) {
                    // todo:
                    JOptionPane.showMessageDialog(null, MessageDialog.NotLogin, MessageDialog.Fail, JOptionPane.ERROR_MESSAGE);
                } catch (JSONException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.JSONError, JOptionPane.ERROR_MESSAGE);
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.IOError, JOptionPane.ERROR_MESSAGE);
                }
            }

            if (isbnJRadioButton.isSelected()) {
                try {
                    Book b = Util.getBookByIsbn(search);
                    this.page = 1;
                    this.totalPages = 1;
                    goToJTextField.setText(String.valueOf(1));
                    totalPagesJLabel.setText(String.format("/%d", 1));
    
                    books = new Object[1][];
                    books[0] = b.toObject();
    
                    bookJScrollPane.remove(bookJTable);
                    bookDefaultTableModel.setDataVector(books, new Object[]{"ID", "ISBN", "书名", "作者", "标签", "总数", "库存", "简介", "操作"});
                    bookJTable = new JTable(bookDefaultTableModel);
                    bookJScrollPane.setViewportView(bookJTable);
                    bookJTable.getColumnModel().getColumn(8).setCellEditor(new BookButtonEditor(bookJTable, Constant.BorrowBook));
                    bookJTable.getColumnModel().getColumn(8).setCellRenderer(new BookButtonRenderer(Constant.BorrowBook));
            
                    bookJTable.setRowSelectionAllowed(false);
    
                    eventJPanel.setVisible(false);
                    bookJPanel.setVisible(true);
                } catch (LibraryException exception) {
                    // todo:
                    JOptionPane.showMessageDialog(null, MessageDialog.NotLogin, MessageDialog.Fail, JOptionPane.ERROR_MESSAGE);
                } catch (JSONException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.JSONError, JOptionPane.ERROR_MESSAGE);
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.IOError, JOptionPane.ERROR_MESSAGE);
                }
            }
            
            if (authorJRadioButton.isSelected()) {
                try {
                    Book[] bs = Util.getBookByAuthor(search, this.page);
                    if (bs.length > 0) {
                        this.totalPages = bs[0].getTotalPages();
                    }
                    goToJTextField.setText(String.valueOf(this.page));
                    totalPagesJLabel.setText(String.format("/%d", this.totalPages));
    
                    books = new Object[bs.length][];
                    for (int i = 0; i < bs.length; i++) {
                        books[i] = bs[i].toObject();
                    }
    
                    bookJScrollPane.remove(bookJTable);
                    bookDefaultTableModel.setDataVector(books, new Object[]{"ID", "ISBN", "书名", "作者", "标签", "总数", "库存", "简介", "操作"});
                    bookJTable = new JTable(bookDefaultTableModel);
                    bookJScrollPane.setViewportView(bookJTable);
                    bookJTable.getColumnModel().getColumn(8).setCellEditor(new BookButtonEditor(bookJTable, Constant.BorrowBook));
                    bookJTable.getColumnModel().getColumn(8).setCellRenderer(new BookButtonRenderer(Constant.BorrowBook));
            
                    bookJTable.setRowSelectionAllowed(false);
    
                    eventJPanel.setVisible(false);
                    bookJPanel.setVisible(true);
                } catch (LibraryException exception) {
                    // todo:
                    JOptionPane.showMessageDialog(null, MessageDialog.NotLogin, MessageDialog.Fail, JOptionPane.ERROR_MESSAGE);
                } catch (JSONException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.JSONError, JOptionPane.ERROR_MESSAGE);
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.IOError, JOptionPane.ERROR_MESSAGE);
                }
            }

            if (tagJRadioButton.isSelected()) {
                try {
                    Book[] bs = Util.getBookByTag(search, this.page);
                    if (bs.length > 0) {
                        this.totalPages = bs[0].getTotalPages();
                    }
                    goToJTextField.setText(String.valueOf(this.page));
                    totalPagesJLabel.setText(String.format("/%d", this.totalPages));
    
                    books = new Object[bs.length][];
                    for (int i = 0; i < bs.length; i++) {
                        books[i] = bs[i].toObject();
                    }
    
                    bookJScrollPane.remove(bookJTable);
                    bookDefaultTableModel.setDataVector(books, new Object[]{"ID", "ISBN", "书名", "作者", "标签", "总数", "库存", "简介", "操作"});
                    bookJTable = new JTable(bookDefaultTableModel);
                    bookJScrollPane.setViewportView(bookJTable);
                    bookJTable.getColumnModel().getColumn(8).setCellEditor(new BookButtonEditor(bookJTable, Constant.BorrowBook));
                    bookJTable.getColumnModel().getColumn(8).setCellRenderer(new BookButtonRenderer(Constant.BorrowBook));
            
                    bookJTable.setRowSelectionAllowed(false);
    
                    eventJPanel.setVisible(false);
                    bookJPanel.setVisible(true);
                } catch (LibraryException exception) {
                    // todo:
                    JOptionPane.showMessageDialog(null, MessageDialog.NotLogin, MessageDialog.Fail, JOptionPane.ERROR_MESSAGE);
                } catch (JSONException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.JSONError, JOptionPane.ERROR_MESSAGE);
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.IOError, JOptionPane.ERROR_MESSAGE);
                }
            }

            if (keywordJRadioButton.isSelected()) {
                try {
                    Book[] bs = Util.getBookByKeyword(search, this.page);
                    if (bs.length > 0) {
                        this.totalPages = bs[0].getTotalPages();
                    }
                    goToJTextField.setText(String.valueOf(this.page));
                    totalPagesJLabel.setText(String.format("/%d", this.totalPages));
    
                    books = new Object[bs.length][];
                    for (int i = 0; i < bs.length; i++) {
                        books[i] = bs[i].toObject();
                    }
    
                    bookJScrollPane.remove(bookJTable);
                    bookDefaultTableModel.setDataVector(books, new Object[]{"ID", "ISBN", "书名", "作者", "标签", "总数", "库存", "简介", "操作"});
                    bookJTable = new JTable(bookDefaultTableModel);
                    bookJScrollPane.setViewportView(bookJTable);
                    bookJTable.getColumnModel().getColumn(8).setCellEditor(new BookButtonEditor(bookJTable, Constant.BorrowBook));
                    bookJTable.getColumnModel().getColumn(8).setCellRenderer(new BookButtonRenderer(Constant.BorrowBook));
            
                    bookJTable.setRowSelectionAllowed(false);
    
                    eventJPanel.setVisible(false);
                    bookJPanel.setVisible(true);
                } catch (LibraryException exception) {
                    // todo:
                    JOptionPane.showMessageDialog(null, MessageDialog.NotLogin, MessageDialog.Fail, JOptionPane.ERROR_MESSAGE);
                } catch (JSONException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.JSONError, JOptionPane.ERROR_MESSAGE);
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.IOError, JOptionPane.ERROR_MESSAGE);
                }
            }

            if (fuzzyJRadioButton.isSelected()) {
                try {
                    Book[] bs = Util.getBookFuzzy(search, this.page);
                    if (bs.length > 0) {
                        this.page = 1;
                        this.totalPages = bs[0].getTotalPages();
                    }
                    goToJTextField.setText(String.valueOf(this.page));
                    totalPagesJLabel.setText(String.format("/%d", this.totalPages));

                    books = new Object[bs.length][];
                    for (int i = 0; i < bs.length; i++) {
                        books[i] = bs[i].toObject();
                    }
    
                    bookJScrollPane.remove(bookJTable);
                    bookDefaultTableModel.setDataVector(books, new Object[]{"ID", "ISBN", "书名", "作者", "标签", "总数", "库存", "简介", "操作"});
                    bookJTable = new JTable(bookDefaultTableModel);
                    bookJScrollPane.setViewportView(bookJTable);
                    bookJTable.getColumnModel().getColumn(8).setCellEditor(new BookButtonEditor(bookJTable, Constant.BorrowBook));
                    bookJTable.getColumnModel().getColumn(8).setCellRenderer(new BookButtonRenderer(Constant.BorrowBook));
            
                    bookJTable.setRowSelectionAllowed(false);
    
                    eventJPanel.setVisible(false);
                    bookJPanel.setVisible(true);
                } catch (LibraryException exception) {
                    // todo:
                    JOptionPane.showMessageDialog(null, MessageDialog.NotLogin, MessageDialog.Fail, JOptionPane.ERROR_MESSAGE);
                } catch (JSONException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.JSONError, JOptionPane.ERROR_MESSAGE);
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.IOError, JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        if (ae.getSource() == previousBookPageJButton) {
            if (page <= 1) {
                JOptionPane.showMessageDialog(null, MessageDialog.AlreadyFirstPage, MessageDialog.NumberFormatFail, JOptionPane.INFORMATION_MESSAGE);
            }

            if (search.isEmpty()) {
                try {
                    Book[] bs = Util.getAllBook(this.page - 1);
                    if (bs.length > 0) {
                        this.page = page - 1;
                        this.totalPages = bs[0].getTotalPages();
                    }
                    goToJTextField.setText(String.valueOf(this.page));
                    totalPagesJLabel.setText(String.format("/%d", this.totalPages));
    
                    books = new Object[bs.length][];
                    for (int i = 0; i < bs.length; i++) {
                        books[i] = bs[i].toObject();
                    }

                    bookJScrollPane.remove(bookJTable);
                    bookDefaultTableModel.setDataVector(books, new Object[]{"ID", "ISBN", "书名", "作者", "标签", "总数", "库存", "简介", "操作"});
                    bookJTable = new JTable(bookDefaultTableModel);
                    bookJScrollPane.setViewportView(bookJTable);
                    bookJTable.getColumnModel().getColumn(8).setCellEditor(new BookButtonEditor(bookJTable, Constant.BorrowBook));
                    bookJTable.getColumnModel().getColumn(8).setCellRenderer(new BookButtonRenderer(Constant.BorrowBook));
            
                    bookJTable.setRowSelectionAllowed(false);
    
                    eventJPanel.setVisible(false);
                    bookJPanel.setVisible(true);
                } catch (LibraryException exception) {
                    // todo:
                    JOptionPane.showMessageDialog(null, MessageDialog.NotLogin, MessageDialog.Fail, JOptionPane.ERROR_MESSAGE);
                } catch (JSONException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.JSONError, JOptionPane.ERROR_MESSAGE);
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.IOError, JOptionPane.ERROR_MESSAGE);
                }
            }

            if (isbnJRadioButton.isSelected()) {
                try {
                    Book b = Util.getBookByIsbn(search);
                    this.page = 1;
                    this.totalPages = 1;
                    goToJTextField.setText(String.valueOf(1));
                    totalPagesJLabel.setText(String.format("/%d", 1));
     
                    books = new Object[1][];
                    books[0] = b.toObject();
    
                    bookJScrollPane.remove(bookJTable);
                    bookDefaultTableModel.setDataVector(books, new Object[]{"ID", "ISBN", "书名", "作者", "标签", "总数", "库存", "简介", "操作"});
                    bookJTable = new JTable(bookDefaultTableModel);
                    bookJScrollPane.setViewportView(bookJTable);
                    bookJTable.getColumnModel().getColumn(8).setCellEditor(new BookButtonEditor(bookJTable, Constant.BorrowBook));
                    bookJTable.getColumnModel().getColumn(8).setCellRenderer(new BookButtonRenderer(Constant.BorrowBook));
            
                    bookJTable.setRowSelectionAllowed(false);

                    eventJPanel.setVisible(false);
                    bookJPanel.setVisible(true);
                } catch (LibraryException exception) {
                    // todo:
                    JOptionPane.showMessageDialog(null, MessageDialog.NotLogin, MessageDialog.Fail, JOptionPane.ERROR_MESSAGE);
                } catch (JSONException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.JSONError, JOptionPane.ERROR_MESSAGE);
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.IOError, JOptionPane.ERROR_MESSAGE);
                }
            }
            
            if (authorJRadioButton.isSelected()) {
                try {
                    Book[] bs = Util.getBookByAuthor(search, page - 1);
                    if (bs.length > 0) {
                        this.page = page - 1;
                        this.totalPages = bs[0].getTotalPages();
                    }
                    goToJTextField.setText(String.valueOf(this.page));
                    totalPagesJLabel.setText(String.format("/%d", this.totalPages));
    
                    books = new Object[bs.length][];
                    for (int i = 0; i < bs.length; i++) {
                        books[i] = bs[i].toObject();
                    }
    
                    bookJScrollPane.remove(bookJTable);
                    bookDefaultTableModel.setDataVector(books, new Object[]{"ID", "ISBN", "书名", "作者", "标签", "总数", "库存", "简介", "操作"});
                    bookJTable = new JTable(bookDefaultTableModel);
                    bookJScrollPane.setViewportView(bookJTable);
                    bookJTable.getColumnModel().getColumn(8).setCellEditor(new BookButtonEditor(bookJTable, Constant.BorrowBook));
                    bookJTable.getColumnModel().getColumn(8).setCellRenderer(new BookButtonRenderer(Constant.BorrowBook));
            
                    bookJTable.setRowSelectionAllowed(false);
    
                    eventJPanel.setVisible(false);
                    bookJPanel.setVisible(true);
                } catch (LibraryException exception) {
                    // todo:
                    JOptionPane.showMessageDialog(null, MessageDialog.NotLogin, MessageDialog.Fail, JOptionPane.ERROR_MESSAGE);
                } catch (JSONException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.JSONError, JOptionPane.ERROR_MESSAGE);
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.IOError, JOptionPane.ERROR_MESSAGE);
                }
            }

            if (tagJRadioButton.isSelected()) {
                try {
                    Book[] bs = Util.getBookByTag(search, page - 1);
                    if (bs.length > 0) {
                        this.page = page - 1;
                        this.totalPages = bs[0].getTotalPages();
                    }
                    goToJTextField.setText(String.valueOf(this.page));
                    totalPagesJLabel.setText(String.format("/%d", this.totalPages));
    
                    books = new Object[bs.length][];
                    for (int i = 0; i < bs.length; i++) {
                        books[i] = bs[i].toObject();
                    }
    
                    bookJScrollPane.remove(bookJTable);
                    bookDefaultTableModel.setDataVector(books, new Object[]{"ID", "ISBN", "书名", "作者", "标签", "总数", "库存", "简介", "操作"});
                    bookJTable = new JTable(bookDefaultTableModel);
                    bookJScrollPane.setViewportView(bookJTable);
                    bookJTable.getColumnModel().getColumn(8).setCellEditor(new BookButtonEditor(bookJTable, Constant.BorrowBook));
                    bookJTable.getColumnModel().getColumn(8).setCellRenderer(new BookButtonRenderer(Constant.BorrowBook));
            
                    bookJTable.setRowSelectionAllowed(false);
    
                    eventJPanel.setVisible(false);
                    bookJPanel.setVisible(true);
                } catch (LibraryException exception) {
                    // todo:
                    JOptionPane.showMessageDialog(null, MessageDialog.NotLogin, MessageDialog.Fail, JOptionPane.ERROR_MESSAGE);
                } catch (JSONException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.JSONError, JOptionPane.ERROR_MESSAGE);
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.IOError, JOptionPane.ERROR_MESSAGE);
                }
            }

            if (keywordJRadioButton.isSelected()) {
                try {
                    Book[] bs = Util.getBookByKeyword(search, page - 1);
                    if (bs.length > 0) {
                        this.page = page - 1;
                        this.totalPages = bs[0].getTotalPages();
                    }
                    goToJTextField.setText(String.valueOf(this.page));
                    totalPagesJLabel.setText(String.format("/%d", this.totalPages));

                    books = new Object[bs.length][];
                    for (int i = 0; i < bs.length; i++) {
                        books[i] = bs[i].toObject();
                    }
    
                    bookJScrollPane.remove(bookJTable);
                    bookDefaultTableModel.setDataVector(books, new Object[]{"ID", "ISBN", "书名", "作者", "标签", "总数", "库存", "简介", "操作"});
                    bookJTable = new JTable(bookDefaultTableModel);
                    bookJScrollPane.setViewportView(bookJTable);
                    bookJTable.getColumnModel().getColumn(8).setCellEditor(new BookButtonEditor(bookJTable, Constant.BorrowBook));
                    bookJTable.getColumnModel().getColumn(8).setCellRenderer(new BookButtonRenderer(Constant.BorrowBook));
            
                    bookJTable.setRowSelectionAllowed(false);
    
                    eventJPanel.setVisible(false);
                    bookJPanel.setVisible(true);
                } catch (LibraryException exception) {
                    // todo:
                    JOptionPane.showMessageDialog(null, MessageDialog.NotLogin, MessageDialog.Fail, JOptionPane.ERROR_MESSAGE);
                } catch (JSONException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.JSONError, JOptionPane.ERROR_MESSAGE);
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.IOError, JOptionPane.ERROR_MESSAGE);
                }
            }

            if (fuzzyJRadioButton.isSelected()) {
                try {
                    Book[] bs = Util.getBookFuzzy(search, page - 1);
                    if (bs.length > 0) {
                        this.page = page - 1;
                        this.totalPages = bs[0].getTotalPages();
                    }
                    goToJTextField.setText(String.valueOf(this.page));
                    totalPagesJLabel.setText(String.format("/%d", this.totalPages));
    
                    books = new Object[bs.length][];
                    for (int i = 0; i < bs.length; i++) {
                        books[i] = bs[i].toObject();
                    }
    
                    bookJScrollPane.remove(bookJTable);
                    bookDefaultTableModel.setDataVector(books, new Object[]{"ID", "ISBN", "书名", "作者", "标签", "总数", "库存", "简介", "操作"});
                    bookJTable = new JTable(bookDefaultTableModel);
                    bookJScrollPane.setViewportView(bookJTable);
                    bookJTable.getColumnModel().getColumn(8).setCellEditor(new BookButtonEditor(bookJTable, Constant.BorrowBook));
                    bookJTable.getColumnModel().getColumn(8).setCellRenderer(new BookButtonRenderer(Constant.BorrowBook));
            
                    bookJTable.setRowSelectionAllowed(false);
    
                    eventJPanel.setVisible(false);
                    bookJPanel.setVisible(true);
                } catch (LibraryException exception) {
                    // todo:
                    JOptionPane.showMessageDialog(null, MessageDialog.NotLogin, MessageDialog.Fail, JOptionPane.ERROR_MESSAGE);
                } catch (JSONException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.JSONError, JOptionPane.ERROR_MESSAGE);
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.IOError, JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        if (ae.getSource() == nextBookPageJButton) {
            if (page >= totalPages) {
                JOptionPane.showMessageDialog(null, MessageDialog.AlreadyFirstPage, MessageDialog.NumberFormatFail, JOptionPane.INFORMATION_MESSAGE);
            }

            if (search.isEmpty()) {
                try {
                    Book[] bs = Util.getAllBook(page + 1);
                    if (bs.length > 0) {
                        this.page = page + 1;
                        this.totalPages = bs[0].getTotalPages();
                    }
                    goToJTextField.setText(String.valueOf(this.page));
                    totalPagesJLabel.setText(String.format("/%d", this.totalPages));
    
                    books = new Object[bs.length][];
                    for (int i = 0; i < bs.length; i++) {
                        books[i] = bs[i].toObject();
                    }
    
                    bookJScrollPane.remove(bookJTable);
                    bookDefaultTableModel.setDataVector(books, new Object[]{"ID", "ISBN", "书名", "作者", "标签", "总数", "库存", "简介", "操作"});
                    bookJTable = new JTable(bookDefaultTableModel);
                    bookJScrollPane.setViewportView(bookJTable);
                    bookJTable.getColumnModel().getColumn(8).setCellEditor(new BookButtonEditor(bookJTable, Constant.BorrowBook));
                    bookJTable.getColumnModel().getColumn(8).setCellRenderer(new BookButtonRenderer(Constant.BorrowBook));
            
                    bookJTable.setRowSelectionAllowed(false);
    
                    eventJPanel.setVisible(false);
                    bookJPanel.setVisible(true);
                } catch (LibraryException exception) {
                    // todo:
                    JOptionPane.showMessageDialog(null, MessageDialog.NotLogin, MessageDialog.Fail, JOptionPane.ERROR_MESSAGE);
                } catch (JSONException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.JSONError, JOptionPane.ERROR_MESSAGE);
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.IOError, JOptionPane.ERROR_MESSAGE);
                }
            }

            if (isbnJRadioButton.isSelected()) {
                try {
                    Book b = Util.getBookByIsbn(search);
                    this.page = 1;
                    this.totalPages = 1;
                    goToJTextField.setText(String.valueOf(1));
                    totalPagesJLabel.setText(String.format("/%d", 1));

                    books = new Object[1][];
                    books[0] = b.toObject();
    
                    bookJScrollPane.remove(bookJTable);
                    bookDefaultTableModel.setDataVector(books, new Object[]{"ID", "ISBN", "书名", "作者", "标签", "总数", "库存", "简介", "操作"});
                    bookJTable = new JTable(bookDefaultTableModel);
                    bookJScrollPane.setViewportView(bookJTable);
                    bookJTable.getColumnModel().getColumn(8).setCellEditor(new BookButtonEditor(bookJTable, Constant.BorrowBook));
                    bookJTable.getColumnModel().getColumn(8).setCellRenderer(new BookButtonRenderer(Constant.BorrowBook));
            
                    bookJTable.setRowSelectionAllowed(false);
    
                    eventJPanel.setVisible(false);
                    bookJPanel.setVisible(true);
                } catch (LibraryException exception) {
                    // todo:
                    JOptionPane.showMessageDialog(null, MessageDialog.NotLogin, MessageDialog.Fail, JOptionPane.ERROR_MESSAGE);
                } catch (JSONException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.JSONError, JOptionPane.ERROR_MESSAGE);
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.IOError, JOptionPane.ERROR_MESSAGE);
                }
            }
            
            if (authorJRadioButton.isSelected()) {
                try {
                    Book[] bs = Util.getBookByAuthor(search, page + 1);
                    if (bs.length > 0) {
                        this.page = page + 1;
                        this.totalPages = bs[0].getTotalPages();
                    }
                    goToJTextField.setText(String.valueOf(this.page));
                    totalPagesJLabel.setText(String.format("/%d", this.totalPages));
    
                    books = new Object[bs.length][];
                    for (int i = 0; i < bs.length; i++) {
                        books[i] = bs[i].toObject();
                    }
    
                    bookJScrollPane.remove(bookJTable);
                    bookDefaultTableModel.setDataVector(books, new Object[]{"ID", "ISBN", "书名", "作者", "标签", "总数", "库存", "简介", "操作"});
                    bookJTable = new JTable(bookDefaultTableModel);
                    bookJScrollPane.setViewportView(bookJTable);
                    bookJTable.getColumnModel().getColumn(8).setCellEditor(new BookButtonEditor(bookJTable, Constant.BorrowBook));
                    bookJTable.getColumnModel().getColumn(8).setCellRenderer(new BookButtonRenderer(Constant.BorrowBook));
            
                    bookJTable.setRowSelectionAllowed(false);
    
                    eventJPanel.setVisible(false);
                    bookJPanel.setVisible(true);
                } catch (LibraryException exception) {
                    // todo:
                    JOptionPane.showMessageDialog(null, MessageDialog.NotLogin, MessageDialog.Fail, JOptionPane.ERROR_MESSAGE);
                } catch (JSONException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.JSONError, JOptionPane.ERROR_MESSAGE);
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.IOError, JOptionPane.ERROR_MESSAGE);
                }
            }

            if (tagJRadioButton.isSelected()) {
                try {
                    Book[] bs = Util.getBookByTag(search, page + 1);
                    if (bs.length > 0) {
                        this.page = page + 1;
                        this.totalPages = bs[0].getTotalPages();
                    }
                    goToJTextField.setText(String.valueOf(this.page));
                    totalPagesJLabel.setText(String.format("/%d", this.totalPages));
    
                    books = new Object[bs.length][];
                    for (int i = 0; i < bs.length; i++) {
                        books[i] = bs[i].toObject();
                    }
    
                    bookJScrollPane.remove(bookJTable);
                    bookDefaultTableModel.setDataVector(books, new Object[]{"ID", "ISBN", "书名", "作者", "标签", "总数", "库存", "简介", "操作"});
                    bookJTable = new JTable(bookDefaultTableModel);
                    bookJScrollPane.setViewportView(bookJTable);
                    bookJTable.getColumnModel().getColumn(8).setCellEditor(new BookButtonEditor(bookJTable, Constant.BorrowBook));
                    bookJTable.getColumnModel().getColumn(8).setCellRenderer(new BookButtonRenderer(Constant.BorrowBook));
            
                    bookJTable.setRowSelectionAllowed(false);
    
                    eventJPanel.setVisible(false);
                    bookJPanel.setVisible(true);
                } catch (LibraryException exception) {
                    // todo:
                    JOptionPane.showMessageDialog(null, MessageDialog.NotLogin, MessageDialog.Fail, JOptionPane.ERROR_MESSAGE);
                } catch (JSONException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.JSONError, JOptionPane.ERROR_MESSAGE);
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.IOError, JOptionPane.ERROR_MESSAGE);
                }
            }

            if (keywordJRadioButton.isSelected()) {
                try {
                    Book[] bs = Util.getBookByKeyword(search, page + 1);
                    if (bs.length > 0) {
                        this.page = page + 1;
                        this.totalPages = bs[0].getTotalPages();
                    }
                    goToJTextField.setText(String.valueOf(this.page));
                    totalPagesJLabel.setText(String.format("/%d", this.totalPages));
    
                    books = new Object[bs.length][];
                    for (int i = 0; i < bs.length; i++) {
                        books[i] = bs[i].toObject();
                    }
    
                    bookJScrollPane.remove(bookJTable);
                    bookDefaultTableModel.setDataVector(books, new Object[]{"ID", "ISBN", "书名", "作者", "标签", "总数", "库存", "简介", "操作"});
                    bookJTable = new JTable(bookDefaultTableModel);
                    bookJScrollPane.setViewportView(bookJTable);
                    bookJTable.getColumnModel().getColumn(8).setCellEditor(new BookButtonEditor(bookJTable, Constant.BorrowBook));
                    bookJTable.getColumnModel().getColumn(8).setCellRenderer(new BookButtonRenderer(Constant.BorrowBook));
            
                    bookJTable.setRowSelectionAllowed(false);
    
                    eventJPanel.setVisible(false);
                    bookJPanel.setVisible(true);
                } catch (LibraryException exception) {
                    // todo:
                    JOptionPane.showMessageDialog(null, MessageDialog.NotLogin, MessageDialog.Fail, JOptionPane.ERROR_MESSAGE);
                } catch (JSONException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.JSONError, JOptionPane.ERROR_MESSAGE);
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.IOError, JOptionPane.ERROR_MESSAGE);
                }
            }

            if (fuzzyJRadioButton.isSelected()) {
                try {
                    Book[] bs = Util.getBookFuzzy(search, page + 1);
                    if (bs.length > 0) {
                        this.page = page + 1;
                        this.totalPages = bs[0].getTotalPages();
                    }
                    goToJTextField.setText(String.valueOf(this.page));
                    totalPagesJLabel.setText(String.format("/%d", this.totalPages));
    
                    books = new Object[bs.length][];
                    for (int i = 0; i < bs.length; i++) {
                        books[i] = bs[i].toObject();
                    }
    
                    bookJScrollPane.remove(bookJTable);
                    bookDefaultTableModel.setDataVector(books, new Object[]{"ID", "ISBN", "书名", "作者", "标签", "总数", "库存", "简介", "操作"});
                    bookJTable = new JTable(bookDefaultTableModel);
                    bookJScrollPane.setViewportView(bookJTable);
                    bookJTable.getColumnModel().getColumn(8).setCellEditor(new BookButtonEditor(bookJTable, Constant.BorrowBook));
                    bookJTable.getColumnModel().getColumn(8).setCellRenderer(new BookButtonRenderer(Constant.BorrowBook));
            
                    bookJTable.setRowSelectionAllowed(false);
    
                    eventJPanel.setVisible(false);
                    bookJPanel.setVisible(true);
                } catch (LibraryException exception) {
                    // todo:
                    JOptionPane.showMessageDialog(null, MessageDialog.NotLogin, MessageDialog.Fail, JOptionPane.ERROR_MESSAGE);
                } catch (JSONException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.JSONError, JOptionPane.ERROR_MESSAGE);
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.IOError, JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        if (ae.getSource() == goToJButton) {
            int goToPage = page;
            try {
                goToPage = Integer.parseInt(goTo);
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.NumberFormatFail, JOptionPane.ERROR_MESSAGE);
            }

            if (page >= totalPages) {
                JOptionPane.showMessageDialog(null, MessageDialog.AlreadyFirstPage, MessageDialog.NumberFormatFail, JOptionPane.INFORMATION_MESSAGE);
            }

            if (search.isEmpty()) {
                try {
                    Book[] bs = Util.getAllBook(goToPage);
                    if (bs.length > 0) {
                        this.page = goToPage;
                        this.totalPages = bs[0].getTotalPages();
                    }
                    goToJTextField.setText(String.valueOf(this.page));
                    totalPagesJLabel.setText(String.format("/%d", this.totalPages));
    
                    books = new Object[bs.length][];
                    for (int i = 0; i < bs.length; i++) {
                        books[i] = bs[i].toObject();
                    }
    
                    bookJScrollPane.remove(bookJTable);
                    bookDefaultTableModel.setDataVector(books, new Object[]{"ID", "ISBN", "书名", "作者", "标签", "总数", "库存", "简介", "操作"});
                    bookJTable = new JTable(bookDefaultTableModel);
                    bookJScrollPane.setViewportView(bookJTable);
                    bookJTable.getColumnModel().getColumn(8).setCellEditor(new BookButtonEditor(bookJTable, Constant.BorrowBook));
                    bookJTable.getColumnModel().getColumn(8).setCellRenderer(new BookButtonRenderer(Constant.BorrowBook));
            
                    bookJTable.setRowSelectionAllowed(false);
    
                    eventJPanel.setVisible(false);
                    bookJPanel.setVisible(true);
                } catch (LibraryException exception) {
                    // todo:
                    JOptionPane.showMessageDialog(null, MessageDialog.NotLogin, MessageDialog.Fail, JOptionPane.ERROR_MESSAGE);
                } catch (JSONException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.JSONError, JOptionPane.ERROR_MESSAGE);
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.IOError, JOptionPane.ERROR_MESSAGE);
                }
            }

            if (isbnJRadioButton.isSelected()) {
                try {
                    Book b = Util.getBookByIsbn(search);
                    this.page = 1;
                    this.totalPages = 1;
                    goToJTextField.setText(String.valueOf(1));
                    totalPagesJLabel.setText(String.format("/%d", 1));

                    books = new Object[1][];
                    books[0] = b.toObject();
    
                    bookJScrollPane.remove(bookJTable);
                    bookDefaultTableModel.setDataVector(books, new Object[]{"ID", "ISBN", "书名", "作者", "标签", "总数", "库存", "简介", "操作"});
                    bookJTable = new JTable(bookDefaultTableModel);
                    bookJScrollPane.setViewportView(bookJTable);
                    bookJTable.getColumnModel().getColumn(8).setCellEditor(new BookButtonEditor(bookJTable, Constant.BorrowBook));
                    bookJTable.getColumnModel().getColumn(8).setCellRenderer(new BookButtonRenderer(Constant.BorrowBook));
            
                    bookJTable.setRowSelectionAllowed(false);
    
                    eventJPanel.setVisible(false);
                    bookJPanel.setVisible(true);
                } catch (LibraryException exception) {
                    // todo:
                    JOptionPane.showMessageDialog(null, MessageDialog.NotLogin, MessageDialog.Fail, JOptionPane.ERROR_MESSAGE);
                } catch (JSONException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.JSONError, JOptionPane.ERROR_MESSAGE);
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.IOError, JOptionPane.ERROR_MESSAGE);
                }
            }
            
            if (authorJRadioButton.isSelected()) {
                try {
                    Book[] bs = Util.getBookByAuthor(search, goToPage);
                    if (bs.length > 0) {
                        this.page = goToPage;
                        this.totalPages = bs[0].getTotalPages();
                    }
                    goToJTextField.setText(String.valueOf(this.page));
                    totalPagesJLabel.setText(String.format("/%d", this.totalPages));
    
                    books = new Object[bs.length][];
                    for (int i = 0; i < bs.length; i++) {
                        books[i] = bs[i].toObject();
                    }
    
                    bookJScrollPane.remove(bookJTable);
                    bookDefaultTableModel.setDataVector(books, new Object[]{"ID", "ISBN", "书名", "作者", "标签", "总数", "库存", "简介", "操作"});
                    bookJTable = new JTable(bookDefaultTableModel);
                    bookJScrollPane.setViewportView(bookJTable);
                    bookJTable.getColumnModel().getColumn(8).setCellEditor(new BookButtonEditor(bookJTable, Constant.BorrowBook));
                    bookJTable.getColumnModel().getColumn(8).setCellRenderer(new BookButtonRenderer(Constant.BorrowBook));
            
                    bookJTable.setRowSelectionAllowed(false);
    
                    eventJPanel.setVisible(false);
                    bookJPanel.setVisible(true);
                } catch (LibraryException exception) {
                    // todo:
                    JOptionPane.showMessageDialog(null, MessageDialog.NotLogin, MessageDialog.Fail, JOptionPane.ERROR_MESSAGE);
                } catch (JSONException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.JSONError, JOptionPane.ERROR_MESSAGE);
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.IOError, JOptionPane.ERROR_MESSAGE);
                }
            }

            if (tagJRadioButton.isSelected()) {
                try {
                    Book[] bs = Util.getBookByTag(search, goToPage);
                    if (bs.length > 0) {
                        this.page = goToPage;
                        this.totalPages = bs[0].getTotalPages();
                    }
                    goToJTextField.setText(String.valueOf(this.page));
                    totalPagesJLabel.setText(String.format("/%d", this.totalPages));
    
                    books = new Object[bs.length][];
                    for (int i = 0; i < bs.length; i++) {
                        books[i] = bs[i].toObject();
                    }
    
                    bookJScrollPane.remove(bookJTable);
                    bookDefaultTableModel.setDataVector(books, new Object[]{"ID", "ISBN", "书名", "作者", "标签", "总数", "库存", "简介", "操作"});
                    bookJTable = new JTable(bookDefaultTableModel);
                    bookJScrollPane.setViewportView(bookJTable);
                    bookJTable.getColumnModel().getColumn(8).setCellEditor(new BookButtonEditor(bookJTable, Constant.BorrowBook));
                    bookJTable.getColumnModel().getColumn(8).setCellRenderer(new BookButtonRenderer(Constant.BorrowBook));
            
                    bookJTable.setRowSelectionAllowed(false);
    
                    eventJPanel.setVisible(false);
                    bookJPanel.setVisible(true);
                } catch (LibraryException exception) {
                    // todo:
                    JOptionPane.showMessageDialog(null, MessageDialog.NotLogin, MessageDialog.Fail, JOptionPane.ERROR_MESSAGE);
                } catch (JSONException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.JSONError, JOptionPane.ERROR_MESSAGE);
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.IOError, JOptionPane.ERROR_MESSAGE);
                }
            }

            if (keywordJRadioButton.isSelected()) {
                try {
                    Book[] bs = Util.getBookByKeyword(search, goToPage);
                    if (bs.length > 0) {
                        this.page = goToPage;
                        this.totalPages = bs[0].getTotalPages();
                    }
                    goToJTextField.setText(String.valueOf(this.page));
                    totalPagesJLabel.setText(String.format("/%d", this.totalPages));
    
                    books = new Object[bs.length][];
                    for (int i = 0; i < bs.length; i++) {
                        books[i] = bs[i].toObject();
                    }
    
                    bookJScrollPane.remove(bookJTable);
                    bookDefaultTableModel.setDataVector(books, new Object[]{"ID", "ISBN", "书名", "作者", "标签", "总数", "库存", "简介", "操作"});
                    bookJTable = new JTable(bookDefaultTableModel);
                    bookJScrollPane.setViewportView(bookJTable);
                    bookJTable.getColumnModel().getColumn(8).setCellEditor(new BookButtonEditor(bookJTable, Constant.BorrowBook));
                    bookJTable.getColumnModel().getColumn(8).setCellRenderer(new BookButtonRenderer(Constant.BorrowBook));
            
                    bookJTable.setRowSelectionAllowed(false);
    
                    eventJPanel.setVisible(false);
                    bookJPanel.setVisible(true);
                } catch (LibraryException exception) {
                    // todo:
                    JOptionPane.showMessageDialog(null, MessageDialog.NotLogin, MessageDialog.Fail, JOptionPane.ERROR_MESSAGE);
                } catch (JSONException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.JSONError, JOptionPane.ERROR_MESSAGE);
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.IOError, JOptionPane.ERROR_MESSAGE);
                }
            }

            if (fuzzyJRadioButton.isSelected()) {
                try {
                    Book[] bs = Util.getBookFuzzy(search, goToPage);
                    if (bs.length > 0) {
                        this.page = goToPage;
                        this.totalPages = bs[0].getTotalPages();
                    }
                    goToJTextField.setText(String.valueOf(this.page));
                    totalPagesJLabel.setText(String.format("/%d", this.totalPages));
    
                    books = new Object[bs.length][];
                    for (int i = 0; i < bs.length; i++) {
                        books[i] = bs[i].toObject();
                    }
    
                    bookJScrollPane.remove(bookJTable);
                    bookDefaultTableModel.setDataVector(books, new Object[]{"ID", "ISBN", "书名", "作者", "标签", "总数", "库存", "简介", "操作"});
                    bookJTable = new JTable(bookDefaultTableModel);
                    bookJScrollPane.setViewportView(bookJTable);
                    bookJTable.getColumnModel().getColumn(8).setCellEditor(new BookButtonEditor(bookJTable, Constant.BorrowBook));
                    bookJTable.getColumnModel().getColumn(8).setCellRenderer(new BookButtonRenderer(Constant.BorrowBook));
            
                    bookJTable.setRowSelectionAllowed(false);
    
                    eventJPanel.setVisible(false);
                    bookJPanel.setVisible(true);
                } catch (LibraryException exception) {
                    // todo:
                    JOptionPane.showMessageDialog(null, MessageDialog.NotLogin, MessageDialog.Fail, JOptionPane.ERROR_MESSAGE);
                } catch (JSONException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.JSONError, JOptionPane.ERROR_MESSAGE);
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(null, exception.toString(), MessageDialog.IOError, JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
