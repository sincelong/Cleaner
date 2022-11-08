package com.laplace.Utils;

import com.laplace.UI.Base;
import com.laplace.UI.Main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Database {

    public static ArrayList<String> getDbName(String path) {
        String [] list = new File(path).list();
        ArrayList<String> dbName = new ArrayList<String>();

        for(int i =0 ;i < list.length;++i) {
            if(list[i].endsWith("db")) dbName.add(list[i]);
        }
        return dbName;
    }

    public static void deleteDataBaseFile() {
        String dataBaseName = Base.dbName.get(Main.selectBase);
        FileUtils.deleteFile("dict", dataBaseName);
    }


}
