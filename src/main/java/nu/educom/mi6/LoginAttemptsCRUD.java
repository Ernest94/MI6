package nu.educom.mi6;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

public class LoginAttemptsCRUD {
    
    private CRUD crud;
    
    public LoginAttemptsCRUD() {
        this.crud = new CRUD();
    }
    
    public LoginAttempts readLoginAttemptsByServiceNumber(int serviceNumber) {
        String sql = "SELECT login_time,successful_login FROM login_attempts WHERE service_number=? ";
        String[] params = {Integer.toString(serviceNumber)};
        LoginAttempts loginAttempts = new LoginAttempts();
        List<HashMap<String,Object>> output = null;
        try {
            output = crud.readMultiRows(sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (output!=null) {
            loginAttempts.setAllLoginAttempts(output);
        }    
        return loginAttempts;
    }
    
    public int createLoginAttempt(int serviceNumber,boolean validity) {
        String sql = "INSERT into login_attempts (service_number,login_time,successful_login) VALUES (?,?,?)";
        String validityStr = validity ? "1" : "0";
        String[] params = {Integer.toString(serviceNumber),LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),validityStr};
        try {
            int logginAttemptId = crud.createRow(sql,params);
            return logginAttemptId;
        } catch (Exception e) {
            return -1;
        }
    }
       
}
