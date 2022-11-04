package com.laplace.UI;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChooseFile extends MouseAdapter {
    private JLabel filePathFild;
    private Main frame;
    private FileDialog fileDialog;
    private String filePath;
    private String fileName;

    public ChooseFile(JLabel filePathFild,Main frame) {
        this.filePathFild = filePathFild;
        this.frame = frame;
    }

    public String GetFile() {
        fileDialog = new FileDialog(frame);
        fileDialog.show();
        filePath = fileDialog.getDirectory();
        fileName = fileDialog.getFile();
        if(filePath == null  || fileName == null){
        }else{
            return filePath + fileName;
        }
        return null;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        System.out.println("test");
        String file = GetFile();


    }
}