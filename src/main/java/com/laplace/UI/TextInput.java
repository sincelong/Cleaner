package com.laplace.UI;

import javax.swing.*;
import java.awt.*;

public class TextInput extends JFrame {
    public JTextArea  jTextArea;
    public JPanel jTextAreaPanel;
    public JButton ok;
    public JButton cancel;
    public Main main;
    public TextInput() {};
    public TextInput(String arr , Main j) {
        this.main = j;
        setSize(700, 550);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        jTextArea = new JTextArea(23, 60);
        jTextAreaPanel = new JPanel();
        jTextAreaPanel.add(new JScrollPane(jTextArea));

        ok = new JButton("确认");
        cancel = new JButton("取消");
//        jTextAreaPanel.setPreferredSize(new Dimension(20, 100));
        ok.setPreferredSize(new Dimension(20, 20));
        if(arr.equals("addWeight")) Listener.addTextAreaOkButtonListener(ok, this);
        if(arr.equals("NewDataBase")) Listener.addNewDatabaseTextAreaOkButtonListener(ok, this, main);
        if(arr.equals("NewTabel")) Listener.addNewTabelTextAreaOkButtonListener(ok, this, main);
        cancel.setPreferredSize(new Dimension(20, 40));
        add(jTextAreaPanel, BorderLayout.NORTH);
        add(ok, BorderLayout.CENTER);
        add(cancel, BorderLayout.SOUTH);
    }
}
