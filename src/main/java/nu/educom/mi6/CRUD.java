package nu.educom.mi6;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.sql.ResultSetMetaData;
import java.sql.Statement; 

public class CRUD {

    public static Connection createConnection() {
        // db parameters
        String url = "jdbc:mysql://127.0.0.1:3307/mi6";
        String user = "mi6_java_development";
        String password = "Htx5528Tvq4CeM";
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            return conn;
        } catch (SQLException ex) {
            ex.printStackTrace();
            Connection conn = null;
            return conn;
        }
    }
    
    public static PreparedStatement prepareAndBind(String sql, String[] params) {
        PreparedStatement statement = null;
        try {
            Connection conn = createConnection();
            statement = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            int n = 1;
            for (String param : params) {
                statement.setString(n, param);
                n++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    public int createRow(String sql, String[] params) {
        PreparedStatement statement = prepareAndBind(sql,params);
        ResultSet result = null;
        int logginAttemptId = -1;
        try {
            int rowAffected = statement.executeUpdate();
            if(rowAffected == 1) {
                result = statement.getGeneratedKeys();
                if (result.next()) logginAttemptId = result.getInt(1);  
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return logginAttemptId;
    }
    
    public HashMap<String,Object> readOneRow(String sql, String[] params) throws SQLException {
        PreparedStatement statement = prepareAndBind(sql,params);
        ResultSet rs = null;
        HashMap<String,Object> output = null;
        rs = statement.executeQuery();
        if (rs!=null) {
            output = convertResultSetToHashMap(rs);
        }
        return output;

    }
    
    public List<HashMap<String,Object>> readMultiRows(String sql, String[] params) throws SQLException {
        PreparedStatement statement = prepareAndBind(sql,params);
        ResultSet rs = null;
        rs = statement.executeQuery();
        List<HashMap<String,Object>> output = convertResultSetToList(rs);
        return output;
    }

    public List<HashMap<String,Object>> convertResultSetToList(ResultSet rs) throws SQLException {
        ResultSetMetaData md = rs.getMetaData();
        int columns = md.getColumnCount();
        List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
        while (rs.next()) {
            HashMap<String,Object> row = new HashMap<String, Object>(columns);
            for(int i=1; i<=columns; ++i) {
                row.put(md.getColumnName(i),rs.getObject(i));
            }
            list.add(row);
        }
        return list;
    }
    
    public HashMap<String,Object> convertResultSetToHashMap(ResultSet rs) throws SQLException {
        ResultSetMetaData md = rs.getMetaData();
        int columns = md.getColumnCount();
        HashMap<String,Object> row = null;
        while (rs.next()) {
            row = new HashMap<String, Object>(columns);
            for(int i=1; i<=columns; ++i) {
                row.put(md.getColumnName(i),rs.getObject(i));
            }
        }
        return row;
    }
}
