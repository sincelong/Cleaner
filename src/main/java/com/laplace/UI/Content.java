package com.laplace.UI;

import com.laplace.connect.Dao;
import com.laplace.connect.SqliteCon;
import org.sqlite.util.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class Content extends JPanel {

    public JPanel infoAction;
    public JPanel showPwd;
    public JPanel outPutAction;
    public JLabel inPutPwd;
    public JLabel outPutPwd;
    public JTextField outPutNum;
    public JTextArea showTxtPwd;
    public JLabel addWeight;
    public JLabel showRowNum;
    public JLabel outPutTextAreaPwd;



    public Content(Main j) {
        Content content = j.getContent();
        showPwd = new JPanel();
        infoAction = new JPanel();
        outPutAction = new JPanel();
        infoAction.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        outPutAction.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        showPwd.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        outPutAction.setPreferredSize(new Dimension(620, 50));
        infoAction.setPreferredSize(new Dimension(620, 50));




        setLayout(new BorderLayout());
        add(showPwd, BorderLayout.CENTER);
        add( outPutAction, BorderLayout.SOUTH);
        add( infoAction, BorderLayout.NORTH);

        inPutPwd = new JLabel("导入数据表");
        addWeight = new JLabel("批量加权");
        Listener.addInputTabelListenr(inPutPwd, j);
        Listener.addWeightListener(addWeight, j);
        infoAction.add(inPutPwd);
        infoAction.add(addWeight);

        //导出到文本框
        outPutTextAreaPwd = new JLabel("导出到文本框");
        //导出数据库的label
        outPutPwd = new JLabel("导出到txt");
        //输入导出数据的条数
        outPutNum = new JTextField(10);
        Listener.addOutPutPwdListener(outPutPwd, j);
        Listener.addOutPutTextAreaPwdListener(outPutTextAreaPwd, j);
        outPutAction.add(outPutNum);
        outPutAction.add(outPutTextAreaPwd);
        outPutAction.add(outPutPwd);

        //显示数据条数的label
        showRowNum = new JLabel();
        outPutAction.add(showRowNum);



        showTxtPwd = new JTextArea(20,20);
        showPwd.add(showTxtPwd);



    }

    public void fresh(Main x) throws SQLException {
        String tabelName = x.tabel.selectTable();
        if(tabelName.equals("")) {
            showTxtPwd.setText("当前未选中数据库");
            showRowNum.setText("当前数据库条数为:"+ 0);
        }
        else {
            ArrayList<String> pwd = SqliteCon.getSqliteCon().getNumPwd(20);
            String res = tabelName + "\n\n" + StringUtils.join(pwd, "\n");
            showTxtPwd.setText(res);
            showRowNum.setText("当前数据库条数为:"+ SqliteCon.getSqliteCon().getPwdRow());
        }

    }
}
