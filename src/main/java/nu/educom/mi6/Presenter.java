package nu.educom.mi6;

import java.util.ArrayList;
import java.util.List;

//import javax.swing.JOptionPane;

public class Presenter {
 
    private ConsoleView view;
//    private GuiView view;
    private Model model;
//    private int serviceNumber;
//    private String secretCode;
//    private String paddedSecretNumber;
    
    boolean stopped = false;
	List<Integer> blacklist = new ArrayList<Integer>();

    
    public Presenter(ConsoleView view) 
//    public Presenter(GuiView view) 
    {
		this.view = view;
		this.model  = new Model();
    }
 
//    public void run() {
//		
//        while(!stopped) {
//        	view.createLoginGui();
//			if (view.option == JOptionPane.OK_OPTION) {
//				try {
//					serviceNumber = Integer.parseInt(view.serviceNumberField.getText());
//					secretCode = view.secretCodeField.getText();
//					if (model.validateLogin(serviceNumber,secretCode,blacklist)) {
//						paddedSecretNumber = model.padLeftZeros(view.serviceNumberField.getText(),3);
//						view.showPopup("Welcome agent " + paddedSecretNumber, "");
//					} else {
//						view.showPopup("ACCESS DENIED","");
//						blacklist.add(serviceNumber);
//					}
//				} catch (Exception e) {
//					view.showPopup("ACCESS DENIED","");
//				}
//			}
//        }
//    }
//}


 //USE CODE BELOW FOR CONSOLEVIEW
	public void run() {
		
	    while(!stopped) {
			view.showMessage("Enter your service number:");
			try {
				int serviceNumber = view.getServiceNumber();
				if ((model.validateServiceNumber(serviceNumber)) && (!model.onBlacklist(serviceNumber,blacklist))) {
					view.showMessage("Enter the secret code:");
					String secretCode = view.getSecretCode();
					if (model.validateSecretCode(secretCode)) { //For ThE Royal QUEEN
						String paddedNumber = model.padLeftZeros(String.valueOf(serviceNumber), 3);
			    		String msg = ("Welcome agent " + paddedNumber);
			    		view.showMessage(msg);
					} else {
			    		view.showMessage("Enemy!!!");
			    		blacklist.add(serviceNumber);
					}
				} else {
					view.showMessage("You are not welcome");
				}
			} catch (Exception e) {
				view.showMessage("You are not welcome");    			
			}
		}
	}
}
