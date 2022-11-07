package com.laplace.UI;

import com.laplace.connect.SqliteCon;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import static java.lang.Math.max;

public class Main extends JFrame{

    public JPanel base;
    public JPanel tabel;
    public Content content;

    public static int selectBase = 0;
    public static int selectTabel = 0;

    public String[] dbName;
    public static ArrayList<String> tbName = new ArrayList<String>();



    public Main() throws SQLException {

        setTitle("字典管理工具 Author:Laplace");
        setVisible(true);
        setSize(1000, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
         base  = new JPanel();
         tabel = new JPanel();
         content = new Content(this);

        add(base, BorderLayout.WEST);
        add(tabel, BorderLayout.CENTER);
        add(content, BorderLayout.EAST);


    }

    public JPanel getBase() {
        return base;
    }

    public JPanel getTabel() {
        return tabel;
    }

    public Content getContent() {
        return content;
    }



    public static void main(String[] args) throws SQLException {
        Main  j = new Main();
        Base base = new Base();
        Tabel tabel = new Tabel();
        Content content = new Content(j);
        SqliteCon con = SqliteCon.getSqliteCon();
        base.fresh(j);
        tabel.fresh(j);
        j.validate();

    }
}
