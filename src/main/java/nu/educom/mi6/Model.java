package nu.educom.mi6;

import java.util.Date;
import java.util.HashMap;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.lang.Math; 

public class Model {
   
    private AgentCRUD agentCRUD;
    private LoginAttemptsCRUD login_attemptCRUD;
    private Agent agent;
    private LoginAttempts loginAttempts;
    
    public Model() {
        this.agentCRUD  = new AgentCRUD();
        this.login_attemptCRUD = new LoginAttemptsCRUD();
    }
    
    public int convertServiceNumber(String serviceNumberStr) {
        try {
            return Integer.parseInt(serviceNumberStr);
        } catch (NumberFormatException nfe) {
            return (int)-1;
        }
    }
   
    public boolean validateServiceNumber(int serviceNumber) { 
        return (serviceNumber>0 && serviceNumber<956);
    }
    
    public void getAgent(int serviceNumber) {
        this.agent = agentCRUD.readAgentByServiceNumber(serviceNumber);
        this.loginAttempts = login_attemptCRUD.readLoginAttemptsByServiceNumber(serviceNumber);

    }
    
    public boolean validateLoginAttempts() {
        Date allowedToLoginTimeDateByLoginAttempts = getAllowedToLoginTimeDateByLastLoginAttempts();
        return new Date().equals(allowedToLoginTimeDateByLoginAttempts) || 
                new Date().after(allowedToLoginTimeDateByLoginAttempts) ?  
                true : false;
    }
    
    public int getNumberOfLastFalseLogins() {
        int nFalseLogins = 0;
        List<HashMap<String, Object>> login_attempts = loginAttempts.getAllLoginAttempts();
        while ( (nFalseLogins < (int)login_attempts.size()) && 
                login_attempts.get(login_attempts.size() - (nFalseLogins+1)).get("successful_login").toString().equals("false")) {
            nFalseLogins++;
        }
        return nFalseLogins;
    }
    
    public long getFreezeTime_mSec(int nFalseLogins) {
        return (long)(nFalseLogins==0 ? 0 : (60 * 1000 * Math.pow(2, (nFalseLogins-1))));
    }
    
    public Date getAllowedToLoginTimeDateByLastLoginAttempts() {
        int nLastFalseLogins = getNumberOfLastFalseLogins();
        List<HashMap<String, Object>> login_attempts = loginAttempts.getAllLoginAttempts();
        if (nLastFalseLogins!=0) {
            Date lastFalseLoginDateTime = (Date) login_attempts.get(login_attempts.size() - 1).get("login_time");
            return new Date(lastFalseLoginDateTime.getTime() + getFreezeTime_mSec(nLastFalseLogins));
        } else {
            return new Date();
        }
    }  
    public String getNewAllowedToLoginTime() {
        int nLastFalseLogins = getNumberOfLastFalseLogins();
        int nFalseLogins = nLastFalseLogins + 1;
        long freezeTime_mSec = getFreezeTime_mSec(nFalseLogins);
        Date newAllowedLoginTime = new Date(new Date().getTime() + freezeTime_mSec);
        return (new SimpleDateFormat("HH:mm:ss")).format(newAllowedLoginTime);
    }
       
    public boolean validateSecretCode(String secretCodeInput) {
        return secretCodeInput.equals(agent.getSecretCode());
  }
    
    public boolean validateActivity() {
        return agent.getActivity();
    }
    
    public boolean validateLicenseToKill() {
        return new Date().equals(agent.getLicenseToKill()) || 
                new Date().before(agent.getLicenseToKill()) ?  
                true : false;
    }
    
    public String getPaddedServiceNumberStr() {
        return padLeftZeros(Integer.toString(agent.getServiceNumber()),3);
    }
    
    public String getLicenseToKillMessage() {
        return (validateLicenseToKill() ? 
                "you have a license to kill till " + new SimpleDateFormat("dd-MM-yyyy").format(agent.getLicenseToKill()) + "." : 
                " you don't have a license to kill.");
    }
    
    public Object[][] getLastLoginAttempts() {
        List<HashMap<String, Object>> login_attempts = loginAttempts.getAllLoginAttempts();
        List<HashMap<String, Object>> lastLogins = new ArrayList<HashMap<String,Object>>();
        
        //select all unsuccessful logins till a successful login
        int i = 1;
        while (i<=login_attempts.size() && !(boolean) login_attempts.get(login_attempts.size() - i).get("successful_login")) {
            lastLogins.add(login_attempts.get(login_attempts.size() - i));
            i++;
        }
        if (i<=login_attempts.size()) {
            lastLogins.add(login_attempts.get(login_attempts.size() - i++));
        }
        Object[][] lastLoginsDataArray = new Object[lastLogins.size()][2];
        for (int j = 0; j < lastLoginsDataArray.length; j++) {
            lastLoginsDataArray[j][0] = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy ").format((Date)lastLogins.get(j).get("login_time"));
            lastLoginsDataArray[j][1] = (boolean)lastLogins.get(j).get("successful_login");
            }
        return lastLoginsDataArray;
    }
    
    public int createLoginAttempt(boolean validity) {
        return login_attemptCRUD.createLoginAttempt(agent.getServiceNumber(),validity);
    }

    public String padLeftZeros(String serviceNumberStr, int length) {
        if (serviceNumberStr.length() >= length) return serviceNumberStr;
        return String.format("%0" + (length-serviceNumberStr.length()) + "d%s", 0, serviceNumberStr); 
    }
}