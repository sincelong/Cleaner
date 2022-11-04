package com.laplace.UI;

import com.laplace.connect.Dao;
import com.laplace.connect.SqliteCon;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import static java.lang.Math.max;

public class Tabel {


//    public ArrayList<JLabel> tabel = new ArrayList<JLabel>();

    public  String[] getTabel(Connection c) throws SQLException {
        return Dao.getTabelName(c);
    }

    public void fresh(Main x) throws SQLException {
//        tabel.clear();
        String[] tbName = getTabel(SqliteCon.getCon().get(x.selectBase));
        x.tabel.removeAll();
        x.tbName.clear();
        x.tabel.setLayout(new GridLayout(max(tbName.length+1, 15), 1));
        x.tabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        for(int i =0;i<tbName.length;++i) {
            JLabel tmp  = new JLabel(tbName[i]);
            System.out.println(tbName[i]);
            x.tbName.add(tbName[i]);
            tmp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            tmp.setBackground(new Color(224,224,224));
            tmp.setOpaque(false);
            Listener.addTabelListener(tmp, i, x);
            x.tabel.add(tmp);
//            tabel.add(tmp);
        }
        x.tabel.add(new JLabel("新建表"));
        x.validate();
    }
}
