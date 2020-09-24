package nu.educom.mi6;

import java.util.List;

public class Model {
	
    public Model() 
    {
    	
    }
     
    public boolean validateLogin(int serviceNumber,String secretCode,List<Integer> blacklist) {
    	return (validateServiceNumber(serviceNumber) && validateSecretCode(secretCode) && !onBlacklist(serviceNumber,blacklist) ? true:false);
    }
    
    public boolean validateServiceNumber(int serviceNumber) {
    	return ((serviceNumber>0) && (serviceNumber<956) ? true : false);
	}
     
    public boolean validateSecretCode(String secretCode) {
    	return (secretCode.equals("For ThE Royal QUEEN") ? true : false);
    }
    
    public boolean onBlacklist(int serviceNumber,List<Integer> blacklist) {
    	return (blacklist.contains(serviceNumber) ? true : false);
    }
    
    
	public String padLeftZeros(String serviceNumber, int length) {
	    if (serviceNumber.length() >= length) {
	        return serviceNumber;
	    }
	    StringBuilder sb = new StringBuilder();
	    while (sb.length() < length - serviceNumber.length()) {
	        sb.append('0');
	    }
	    sb.append(serviceNumber);
	 
	    return sb.toString();
	}
}