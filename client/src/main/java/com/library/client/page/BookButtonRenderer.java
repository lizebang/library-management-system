package com.library.client.page;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class BookButtonRenderer implements TableCellRenderer {

    private JPanel panel;
    private JButton button;
    private String text;

    public BookButtonRenderer(String text) {
        this.text = text;
        initButton();
        initPanel();
        panel.add(button, BorderLayout.CENTER);
    }

    private void initButton() {
        button = new JButton();
    }

    private void initPanel() {
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {
        button.setText(this.text);
        return panel;
    }
}
