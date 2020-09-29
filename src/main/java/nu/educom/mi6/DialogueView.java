package nu.educom.mi6;

import java.awt.GridLayout;
import javax.swing.*;

public class DialogueView implements IView{

    private JPanel pane;
    private JFrame frame;
    private JTextField serviceNumberField;
    private JTextField secretCodeField;	
    private int option;

    @Override
    public String getServiceNumber() {
        return this.serviceNumberField.getText();
    }
    
    @Override
    public String getSecretCode() {
        return this.secretCodeField.getText();
    }
    
    @Override
    public void triggerLogin() {
        this.createLoginGui();
    }
    
    @Override
    public String checkView() {
        return "dialogue";
    }
    
    @Override
    public void showMessage(String body) {
        pane = new JPanel();
        pane.add(new JLabel(body));
        JOptionPane.showMessageDialog(frame,pane, "", JOptionPane.PLAIN_MESSAGE); 
    }

    @Override
    public boolean getResult() {
        return (this.option == JOptionPane.OK_OPTION);
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

}