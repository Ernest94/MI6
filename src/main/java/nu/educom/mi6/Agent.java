package nu.educom.mi6;

import java.util.Date;

public class Agent {

//    private int id;
    private int serviceNumber;
    private String secretCode;
    private boolean activity;
    private Date licenseToKill;
    
    
    public int getServiceNumber() {
        return this.serviceNumber;
    }
    public void setServiceNumber(int serviceNumber) {
        this.serviceNumber = serviceNumber;
    }
    
    public String getSecretCode() {
        return this.secretCode;
    }
    public void setSecretCode(Object secretCode) {
        this.secretCode = secretCode.toString();

    }
    
    public boolean getActivity() {
        return activity;
    }
    public void setActivity(Object activity) {
        this.activity = (boolean)activity;
    }
    
    public Date getLicenseToKill() {
        return licenseToKill;
    }
    public void setLicenseToKill(Object licenseToKill) {
        this.licenseToKill = (Date)licenseToKill;    
    }
    
}
