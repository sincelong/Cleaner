package com.laplace.UI;

import javax.swing.*;
import java.awt.*;

public class TextInput extends JFrame {
    public JTextArea  jTextArea;
    public JPanel jTextAreaPanel;
    public JButton ok;
    public JButton cancel;
    public TextInput() {
        setSize(700, 500);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        jTextArea = new JTextArea(25, 65);
        jTextAreaPanel = new JPanel();
        jTextAreaPanel.add(new JScrollPane(jTextArea));
        ok = new JButton("确认");
        cancel = new JButton("取消");
//        jTextAreaPanel.setPreferredSize(new Dimension(20, 100));
        ok.setPreferredSize(new Dimension(20, 20));
        Listener.addTextAreaOkButtonListener(ok, this);
        cancel.setPreferredSize(new Dimension(20, 30));
        add(jTextAreaPanel, BorderLayout.NORTH);
        add(ok, BorderLayout.CENTER);
        add(cancel, BorderLayout.SOUTH);
    }
}
