package com.library.client.page;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class BookButtonEditor extends AbstractCellEditor implements TableCellEditor {

    private JPanel panel;
    private JButton button;
    private JTable table;
    private String isbn;
    private String text;

    public BookButtonEditor(JTable table, String text) {
        this.table = table;
        this.text = text;
        initButton();
        initPanel();
        panel.add(this.button, BorderLayout.CENTER);
    }

    private void initButton() {
        button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int res = JOptionPane.showConfirmDialog(null, "Do you want to add 1 to it?",
                        "choose one", JOptionPane.YES_NO_OPTION);

                if (res == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, "message");
                }
                fireEditingStopped();
            }
        });
    }

    private void initPanel() {
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected,
            int row, int column) {
        isbn = (String) value;
        button.setText(value == null ? "Error" : this.text);
        return panel;
    }

    @Override
    public Object getCellEditorValue() {
        return isbn;
    }
}
