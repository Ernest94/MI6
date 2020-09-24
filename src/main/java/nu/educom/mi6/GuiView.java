package nu.educom.mi6;

//import java.util.Scanner;

import java.awt.GridLayout;
import javax.swing.*;

public class GuiView {
	
	private JPanel pane;
    private JFrame frame;
	public JTextField serviceNumberField;
	public JTextField secretCodeField;	

    public int option;

    public GuiView()
    {
    	
    }
    
    public void createLoginGui() {

    	 pane = new JPanel();
    	 pane.setLayout(new GridLayout(0, 2, 2, 2));

    	 serviceNumberField = new JTextField(0);
    	 secretCodeField = new JTextField(0);

    	 pane.add(new JLabel("Enter your service number:"));
    	 pane.add(serviceNumberField);

    	 pane.add(new JLabel("Enter the secret code:"));
    	 pane.add(secretCodeField);
        
         option = JOptionPane.showConfirmDialog(frame, pane, "Login", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    }
    
    public void showPopup(String body,String title) {
    	pane = new JPanel();
	   	pane.add(new JLabel(body));
    	JOptionPane.showMessageDialog(frame,pane, title, JOptionPane.PLAIN_MESSAGE); 
    }

}