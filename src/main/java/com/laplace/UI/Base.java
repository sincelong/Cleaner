package com.laplace.UI;

import com.laplace.connect.SqliteCon;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import static java.lang.Math.max;

public class Base {

    public static ArrayList<JLabel> baseLabel = new ArrayList<JLabel>();


    public void fresh(Main x) throws SQLException {
        x.base.removeAll();
        SqliteCon con = SqliteCon.getSqliteCon();
        String[] dbName = con.getDbName();

        x.base.setLayout(new GridLayout(max(dbName.length+1, 18), 1));
        x.base.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        for(int i =0;i<dbName.length;++i) {
            JLabel tmp  = new JLabel(dbName[i]);
            tmp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            tmp.setBackground(new Color(224,224,224));
            tmp.setOpaque(false);
            Listener.addBaseListner(tmp, i , x);
            baseLabel.add(tmp);
            x.base.add(tmp);
        }

        JLabel newBase = new JLabel("新建数据库");
        Listener.addNewDatabaseListener(newBase, x);
        x.base.add(newBase);

        JLabel inPutBase = new JLabel("导入数据库");
    }
}
