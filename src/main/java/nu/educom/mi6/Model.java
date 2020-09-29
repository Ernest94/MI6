package nu.educom.mi6;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private List<String> blacklist = new ArrayList<String>();
    
//  public boolean validateLogin(String serviceNumberStr,String secretCode) {
//  return (validateServiceNumber(serviceNumberStr) && 
//          validateSecretCode(secretCode) && 
//          !onBlacklist(serviceNumberStr));
//}

    public int convertServiceNumber(String serviceNumberStr) {
        try {
            return Integer.parseInt(serviceNumberStr);
        } catch (Exception NumberFormatException) {
            return (int)-1;
        }
    }
    
    public boolean validateServiceNumber(String serviceNumberStr) {
        int serviceNumber = this.convertServiceNumber(serviceNumberStr);
        return (serviceNumber>0 && 
                serviceNumber<956);
    }
 
    public boolean validateSecretCode(String secretCode) {
        return secretCode.equals("For ThE Royal QUEEN");
    }

    public boolean onBlacklist(String serviceNumberStr) {
        return this.blacklist.contains(serviceNumberStr);
    }
    public void addToBlacklist(String serviceNumberStr) {
        this.blacklist.add(serviceNumberStr);
    }

    public boolean checkExit(String serviceNumberStr) {
        return (serviceNumberStr.equals("exit"));
    }

    public String padLeftZeros(String serviceNumberStr, int length) {
        if (serviceNumberStr.length() >= length) return serviceNumberStr;
        return String.format("%0" + (length-serviceNumberStr.length()) + "d%s", 0, serviceNumberStr); 
    }
}