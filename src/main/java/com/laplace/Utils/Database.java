package com.laplace.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Database {

    public static List<String> getDbName(String path) {
        String [] list = new File(path).list();
        List<String> dbName = new ArrayList<String>();

        for(int i =0 ;i < list.length;++i) {
            if(list[i].endsWith("db")) dbName.add(list[i]);
        }
        return dbName;


    }


}
