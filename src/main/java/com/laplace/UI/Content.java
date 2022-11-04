package com.laplace.UI;

import javax.swing.*;
import java.awt.*;

public class Content {

    public JPanel infoAction;
    public JPanel showPwd;
    public JPanel outPutAction;
    public JLabel inPutPwd;
    public JLabel outPutPwd;
    public JTextField outPutNum;
    public JTextArea showTxtPwd;
    public JLabel addWeight;



    public Content(Main j) {
        JPanel content = j.getContent();
        showPwd = new JPanel();
        infoAction = new JPanel();
        outPutAction = new JPanel();
        infoAction.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        outPutAction.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        showPwd.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        outPutAction.setPreferredSize(new Dimension(620, 50));
        infoAction.setPreferredSize(new Dimension(620, 50));



        content.setLayout(new BorderLayout());
        content.add(showPwd, BorderLayout.CENTER);
        content.add( outPutAction, BorderLayout.SOUTH);
        content.add( infoAction, BorderLayout.NORTH);

        inPutPwd = new JLabel("导入数据表");
        addWeight = new JLabel("批量加权");
        Listener.addInputTabelListenr(inPutPwd, j);
        Listener.addWeightListener(addWeight, j);
        infoAction.add(inPutPwd);
        infoAction.add(addWeight);

        outPutPwd = new JLabel("导出数据库");
        outPutNum = new JTextField(10);
        Listener.addOutPutPwdListener(outPutPwd, j);
        outPutAction.add(outPutNum);
        outPutAction.add(outPutPwd);

        showTxtPwd = new JTextArea(20,20);
        showPwd.add(showTxtPwd);



    }

    public void fresh(Main x, JLabel jLabel) {
        String tabelName = jLabel.getText();
        showTxtPwd.setText(tabelName);
    }
}
