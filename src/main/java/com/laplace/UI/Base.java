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
    public JLabel deleteDataBase ;

    public void fresh(Main x) throws SQLException {
        removeAll();
        SqliteCon con = SqliteCon.getSqliteCon();
        dbName = con.getDbName();

        int cnt= 0;
        int all = max(dbName.size()+3, 18);
        setLayout(new GridLayout(all, 1));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        for(int i =0;i<dbName.size();++i) {
            cnt++;
            JLabel tmp  = new JLabel(dbName.get(i));
            tmp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            tmp.setBackground(new Color(224,224,224));
            tmp.setOpaque(false);
            Listener.addBaseListner(tmp, i , x);
            add(tmp);
        }

        cnt++;
        JLabel newBase = new JLabel("新建数据库");
        newBase.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        Listener.addNewDatabaseListener(newBase, x);
        add(newBase);

        deleteDataBase = new JLabel("删除当前数据库");
        deleteDataBase.setBorder(BorderFactory.createLineBorder(Color.RED));
        Listener.addDeleteDataBase(deleteDataBase, x);
         //将删除与添加分开，防止误操作
        for(int i ;cnt<all-1;cnt++ ) {
            add(new JLabel(" "));
        }
        add(deleteDataBase);
        x.validate();
    }
}
