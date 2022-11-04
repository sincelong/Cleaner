package com.laplace.connect;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.laplace.UI.Main;
import com.laplace.Utils.Database;

public class SqliteCon {

    private static SqliteCon con = new SqliteCon();
    private static ArrayList<Connection> c  ;
    private static ArrayList<String>  dbName ;

    private void init() {
        dbName =(ArrayList<String>) Database.getDbName("dict");
        c =  new ArrayList<Connection>();
        try {
            for(int i = 0; i < dbName.size();++i) {
                System.out.println("jdbc:sqlite:dict/"+dbName.get(i));
                c.add(DriverManager.getConnection("jdbc:sqlite:dict/"+dbName.get(i)));
                System.out.println("xxxxx");
            }

        } catch ( SQLException e ) {
            e.printStackTrace();
        }
    }

    private SqliteCon() {
        this.init();
    }

    public static String[] getDbName() throws SQLException {
        return (String []) dbName.toArray(new String[dbName.size()]);
    }


    public static SqliteCon getSqliteCon() {
        return con;
    }

    public static ArrayList<Connection> getCon() {
        return c;
    }

    public Connection getSelectBaseConnection() {
        System.out.println("当前数据库:"+Main.selectBase);
        return c.get(Main.selectBase);
    }

    public int inpuTabel(String path) throws SQLException, IOException {
        ArrayList<String> name = new ArrayList<String>();
        ArrayList<Integer> weight = new ArrayList<Integer>();
        File dictFile =new File(path);
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(dictFile), "UTF-8"));

        try {
            String line;
            try {
                while ((line = br.readLine()) != null) {
                    String[] tmp = line.strip().split(" ");
                    name.add(tmp[0].strip());
                    if (tmp.length==2) {
                        weight.add(Integer.valueOf(tmp[1].strip()));
                    } else weight.add(new Integer(0));
                }
            } catch (IOException var10) {
                var10.printStackTrace();
            } finally {
                if (br != null) {
                    br.close();
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return Dao.inPutTable(SqliteCon.getSqliteCon().getSelectBaseConnection() , name , weight);

    }

    public String getTabelName() {
        return Main.tbName.get(Main.selectTabel);
    }


    public static void main(String[] args) throws SQLException {
        SqliteCon x = new SqliteCon();
        x.getDbName();
    }

    public ArrayList<String> getNumPwd(int len) throws SQLException {
        return Dao.getNumPwd(con.getSelectBaseConnection() , len);
    }

   public int updatePwd(String[] pwd) throws SQLException {
       ArrayList<String> name = new ArrayList<String>();
       ArrayList<Integer> weight = new ArrayList<Integer>();
       for(int i = 0;i<pwd.length;++i) {
           String tmp[] = pwd[i].strip().split(" ");
           name.add(tmp[0].strip());
           if (tmp.length==2) {
               weight.add(Integer.valueOf(tmp[1].strip()));
           } else {
               weight.add(new Integer(0));
           }
       }

       return Dao.updateTable(SqliteCon.getSqliteCon().getSelectBaseConnection(), name, weight);
   }
}
