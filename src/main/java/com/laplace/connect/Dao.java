package com.laplace.connect;

import com.laplace.UI.Content;
import com.laplace.UI.Tabel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.laplace.UI.Tabel.tbName;

public class Dao {


    public static int getTabelNameRow(Connection c ) throws SQLException {
        int rowCount = 0;
        String sql = "SELECT count(name)  FROM sqlite_master WHERE type='table' ORDER BY name;";
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()) {
            rowCount = rs.getInt(1);
        }
        return rowCount;
    }

    public static void getTabelName(Connection c) throws SQLException {
        tbName.clear();
        String sql = "SELECT name FROM sqlite_master WHERE type='table' ORDER BY name;";
        Statement stmt = c.createStatement();
        try {
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                tbName.add(rs.getString(1));
                System.out.println(rs.getString(1));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            stmt.close();
        }
    }

    public static void main(String[] args) throws SQLException {
        getTabelName(SqliteCon.getCon().get(0));
    }

    public static  int inPutTable(Connection c, ArrayList<String> name, ArrayList<Integer> weight) throws SQLException {
        c.setAutoCommit(false);
        String tbName = SqliteCon.getSqliteCon().getTabelName();
        String sql = "INSERT INTO \"" + tbName + "\" (name, weight)" + "VALUES (? , ?)";
        PreparedStatement ps = c.prepareStatement(sql);
        for(int i = 0; i < name.size(); ++i) {
           ps.setString(1, name.get(i));
           ps.setInt(2, weight.get(i));
           System.out.println(sql);
           ps.execute();
        }
        ps.close();
        c.commit();
        return name.size();
    }

    public static int updateTable(Connection c , ArrayList<String> name, ArrayList<Integer> weight) throws SQLException {
        c.setAutoCommit(false);
        String tbName = SqliteCon.getSqliteCon().getTabelName();
        ArrayList<String> now  = getNumPwd(c , getPwdRow(c));
        int len = name.size();
        for(int i = 0; i < name.size(); ++i) {
            System.out.println(i+"/"+len);
            if( now.contains(name.get(i)) )  {
                    ArrayList<String[]> tmp = getData(c, name.get(i));
                    addWeight(c, name.get(i) , Integer.valueOf(tmp.get(0)[1])+weight.get(i) );
            }
            else {
                insertDate(c , name.get(i) , weight.get(i));
            }
        }
        c.commit();
        return name.size();
    }

    public static String insertDate(Connection c , String name, int weight) throws SQLException {
        String tbName = SqliteCon.getSqliteCon().getTabelName();
        String sql = "INSERT INTO \"" + tbName + "\" (name, weight)" + "VALUES (? , ?)";
        PreparedStatement st = c.prepareStatement(sql);
        st.setString(1, name);
        st.setInt(2, weight);
        st.execute();
        st.close();
        return name;
    }

    public static boolean dataExist(Connection c, String name) throws SQLException {
        boolean flag= false;
        String tbName= SqliteCon.getSqliteCon().getTabelName();
        String sql = "SELECT * FROM " + "\"" + tbName + "\"" + " WHERE name = ?" ;
        PreparedStatement ps =  c.prepareStatement(sql);
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
        if(rs.next()) flag=true;
        return flag;
    }

    public static ArrayList<String[]> getData(Connection c, String name) throws SQLException {
        ArrayList<String[]> list = new ArrayList<String[]>();
        String tbName = SqliteCon.getSqliteCon().getTabelName();
        String sql = "SELECT * FROM " + "\"" + tbName + "\"" + " WHERE name = ?" ;
        PreparedStatement ps =  c.prepareStatement(sql);
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            String[] tmp = new String[2];
            tmp[0] = rs.getString(1);
            tmp[1] = String.valueOf(rs.getInt(2));
            list.add(tmp);
        }
        return list;
    }

    public static int addWeight(Connection c, String name, int weight) throws SQLException {
        String tbName = SqliteCon.getSqliteCon().getTabelName();
        String sql = "UPDATE " + "\"" + tbName + "\"" + "SET weight = ? WHERE NAME = ?";
        PreparedStatement st = c.prepareStatement(sql);
        st.setInt(1, weight);
        st.setString(2, name);
        st.executeUpdate();
        st.close();
        return weight;
    }

    public static int getPwdRow(Connection c) throws SQLException {
        int rowCnt = 0;
        String tbName = SqliteCon.getSqliteCon().getTabelName();
        String Sql = "SELECT COUNT(*) FROM" +"\"" + tbName + "\"" ;
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery(Sql);
        while(rs.next()) {
            rowCnt = rs.getInt(1);
        }
        return rowCnt;
    }

    public static ArrayList<Integer> getWeight(Connection c, int len) throws SQLException {
        ArrayList<Integer> list = new ArrayList<Integer>();
        c.setAutoCommit(false);
        String tbName = SqliteCon.getSqliteCon().getTabelName();
        String sql = "SELECT * FROM " + "\"" + tbName + "\"" + "ORDER BY weight DESC LIMIT ?" ;
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setInt(1, len);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            list.add(rs.getInt("weight"));
        }
        c.commit();
        return list;

    }

    public static ArrayList<String> getNumPwd(Connection c, int len) throws SQLException {
        ArrayList<String> list = new ArrayList<String>();
        c.setAutoCommit(false);
        String tbName = SqliteCon.getSqliteCon().getTabelName();
        String sql = "SELECT * FROM " + "\"" + tbName + "\"" + "ORDER BY weight DESC LIMIT ?" ;
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setInt(1, len);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            list.add(rs.getString("name"));
        }
        c.commit();
        return list;
    }

    public static void newTabel(Connection c, String all) throws SQLException {
        String sql = "CREATE TABLE " + "\"" + all + "\"" + " (name  TEXT , weight INT );";
        System.out.println(sql);
        Statement st = c.createStatement();
        st.execute(sql);
        st.close();

    }
}
