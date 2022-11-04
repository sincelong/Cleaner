package com.laplace.UI;

import com.laplace.Utils.FileUtils;
import com.laplace.connect.Dao;
import com.laplace.connect.SqliteCon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Listener {

    public static String getFile(Main x) {
       FileDialog fileDialog = new FileDialog(x);
        fileDialog.show();
        String  filePath = fileDialog.getDirectory();
        String fileName = fileDialog.getFile();
        if(filePath == null  || fileName == null){
        }else{
            return filePath + fileName;
        }
        return null;
    }

    public static void addWeightListener(JLabel jLabel, Main j) {
        jLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                TextInput input = new TextInput();
            }
        });
    }

    public static  void addInputTabelListenr(JLabel jLabel, Main j ){
        jLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String path = getFile(j);
                if(path==null) return;
                System.out.println(path);
                try {
                   int row =  SqliteCon.getSqliteCon().inpuTabel(path);
                    System.out.println("成功插入"+row+"条数据");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                } catch (UnsupportedEncodingException unsupportedEncodingException) {
                    unsupportedEncodingException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }


            }
        });
    }

    public static void addNewTabelListenr(JLabel jLabel, Main j ) {
        jLabel.addMouseListener(new ChooseFile(jLabel, j));
    }

    public static void addBaseListner(JLabel jLabel, int index, Main j) {
            jLabel.addMouseListener(new MouseAdapter() {

                public void mouseClicked(MouseEvent e) {
                    Main.selectBase = index;
                    try {

                        new Tabel().fresh(j);
                        System.out.println(index);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                }
            });
    }

    public static void addTabelListener(JLabel jLabel, int index , Main j ) {
        jLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                Main.selectTabel = index;
                System.out.println(Main.selectBase +"  " +Main.selectTabel);
                try {
                    Content tmp = new Content(j);
                    tmp.fresh(j , jLabel);

                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
    }

    public static void addOutPutPwdListener(JLabel jLabel, Main j ){
        jLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                ArrayList<String> pwd  = null;
                JPanel content =j.getContent();
                content = (JPanel) content.getComponent(1);
                int len = content.getComponentCount();
                JTextField jTextField = null;
                for(int i = 0; i < len ;++i) {
                     jTextField = content.getComponent(i) instanceof JTextField ? (JTextField)  content.getComponent(i) : null;
                     if(jTextField != null) break;
                }
                len = Integer.valueOf(jTextField.getText());
                try {
                     pwd = SqliteCon.getSqliteCon().getNumPwd(len);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                FileUtils.writePwdFile(jTextField.getText(), pwd);
            }
        });
    }

    public static void addTextAreaOkButtonListener(JButton btn, TextInput j){
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextArea jTextArea = j.jTextArea;
                ArrayList<String> list= new ArrayList<String>();
                String all = jTextArea.getText();
                String cut[] = all.strip().split("\n");
                try {
                    System.out.println(SqliteCon.getSqliteCon().updatePwd(cut));
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                j.dispose();
            }
        });
    }
}
