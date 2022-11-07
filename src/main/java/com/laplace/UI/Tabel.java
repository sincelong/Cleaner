package com.laplace.UI;

import com.laplace.connect.Dao;
import com.laplace.connect.SqliteCon;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import static java.lang.Math.max;

public class Tabel extends JPanel {


    public static ArrayList<String> tbName = new ArrayList<String>() ;

    public String selectTable() {
        String selectTableName = null;
        if(tbName.size()<=Main.selectTabel) return "";
        else return tbName.get(Main.selectTabel);
    }

    public void fresh(Main x) throws SQLException {
        SqliteCon.getSqliteCon().freshTableName();
        removeAll();
        setLayout(new GridLayout(max(tbName.size()+3, 15), 1));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        for(int i =0;i<tbName.size();++i) {
            JLabel tmp  = new JLabel(tbName.get(i));
            System.out.println(tbName.get(i));
            tmp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            tmp.setBackground(new Color(224,224,224));
            tmp.setOpaque(false);
            Listener.addTabelListener(tmp, i, x);
            add(tmp);
//            tabel.add(tmp);
        }
        JLabel newTabel = new JLabel("新建表");
        Listener.addNewTabelListenr(newTabel , x);
        add(newTabel);
        x.validate();
    }
}
