package nu.educom.mi6;

import java.util.Scanner;

//import javax.swing.JOptionPane;

public class ConsoleView {
	
    private String secretCode;
    private int serviceNumber;

    public ConsoleView()
    {
    }
     
    public void showMessage(String msg) {
		System.out.println(msg);
    }
    
    public int getServiceNumber() {
		Scanner scanner1 = new Scanner(System.in);
		serviceNumber = scanner1.nextInt();
		return serviceNumber;
    }
    
    public String getSecretCode() {
		Scanner scanner2 = new Scanner(System.in);
		secretCode = scanner2.nextLine();
		return secretCode;
    }
    
}