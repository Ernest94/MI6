package nu.educom.mi6;

import java.util.Scanner;

public class ConsoleView implements IView{

    private String secretCode;
    private String serviceNumberStr;
    private Scanner scanner1;
    private Scanner scanner2;
 
    @Override
    public void triggerLogin() {
        this.showMessage("Enter your service number:");
    }
    
    @Override
    public String checkView() {
        return "console";
    }
    
    @Override
    public void showMessage(String body) {
        System.out.println(body);
    }
    
    @Override
    public boolean getResult() {
        return true;
    }
    
    @Override
    public String getServiceNumber() {
        scanner1 = new Scanner(System.in);
        serviceNumberStr = scanner1.nextLine();
        return serviceNumberStr;
    }
    
    @Override
    public String getSecretCode() {
        scanner2 = new Scanner(System.in);
        secretCode = scanner2.nextLine();
        return secretCode;
    }

}