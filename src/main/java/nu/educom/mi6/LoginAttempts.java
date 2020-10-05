package nu.educom.mi6;

import java.util.HashMap;
import java.util.List;

public class LoginAttempts {

    private List<HashMap<String, Object>> loginAttempts;

    public List<HashMap<String, Object>> getAllLoginAttempts() {
        return this.loginAttempts;
    }
    public void setAllLoginAttempts(List<HashMap<String,Object>> loginAttempts) {
        this.loginAttempts = loginAttempts;
    }
    
}
