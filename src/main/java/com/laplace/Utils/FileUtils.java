package com.laplace.Utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileUtils {
    public FileUtils() {

    }

    public static void writePwdFile(String path , ArrayList<String> pwd) {
        try {
            System.out.println(path);
            System.out.println("输出文件条数"+pwd.size());
            File file = new File(path+".txt");
            if(!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            for(int i = 0;i < pwd.size() ; ++i) {
                bw.write(pwd.get(i)+"\n");
                System.out.println(pwd.get(i));
            }
            bw.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createFile(String path , String fileName) throws IOException {
        File file = new File(path + fileName+".db");
        if(!file.exists()) {
            file.createNewFile();
        }
    }



}
