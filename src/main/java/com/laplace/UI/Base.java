package com.laplace.UI;

import com.laplace.connect.SqliteCon;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import static java.lang.Math.max;

public class Base extends JPanel {

    public static ArrayList<String> dbName = new ArrayList<String>() ;

    public void fresh(Main x) throws SQLException {
        removeAll();
        SqliteCon con = SqliteCon.getSqliteCon();
        dbName = con.getDbName();

        setLayout(new GridLayout(max(dbName.size()+1, 18), 1));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        for(int i =0;i<dbName.size();++i) {
            JLabel tmp  = new JLabel(dbName.get(i));
            tmp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            tmp.setBackground(new Color(224,224,224));
            tmp.setOpaque(false);
            Listener.addBaseListner(tmp, i , x);
            add(tmp);
        }

        JLabel newBase = new JLabel("新建数据库");
        Listener.addNewDatabaseListener(newBase, x);
        add(newBase);

        JLabel inPutBase = new JLabel("导入数据库");
        x.validate();
    }
}
