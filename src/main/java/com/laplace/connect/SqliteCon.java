package com.laplace.connect;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.laplace.UI.Main;
import com.laplace.UI.Tabel;
import com.laplace.Utils.Database;

import static com.laplace.UI.Base.dbName;

public class SqliteCon {

    private static SqliteCon con;

    static {
        try {
            con = new SqliteCon();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<Connection> c  ;

    public void init() throws SQLException {
        if(c!=null) {
            for(int i  =0 ; i<c.size();++i) {
                c.get(i).close();
            }
            c.clear();
        }
        dbName = Database.getDbName("dict");
        if(c==null) c =  new ArrayList<Connection>();
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

    private SqliteCon() throws SQLException {
        this.init();
    }

    public static ArrayList<String> getDbName() throws SQLException {
        getSqliteCon().init();
        return  dbName;
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
                    String[] tmp = line.trim().split(" ");
                    name.add(tmp[0].trim());
                    if (tmp.length>=2) {
                        int len = tmp.length-1;
                        weight.add(Integer.valueOf(tmp[len].trim()));
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
        return Tabel.tbName.get(Main.selectTabel);
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
           String tmp[] = pwd[i].trim().split(" ");
           name.add(tmp[0].trim());
           if (tmp.length==2) {
               weight.add(Integer.valueOf(tmp[1].trim()));
           } else {
               weight.add(new Integer(0));
           }
       }

       return Dao.updateTable(SqliteCon.getSqliteCon().getSelectBaseConnection(), name, weight);
   }

   public int getPwdRow() throws SQLException {
        return Dao.getPwdRow(getSelectBaseConnection());
   }

    public void newTabel(String all) throws SQLException {
        Dao.newTabel(getSelectBaseConnection(), all);
    }

    public void freshTableName() throws SQLException {
        Dao.getTabelName(c.get(Main.selectBase));
    }
}
