package nu.educom.mi6;

import java.awt.Color;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class FrameView implements IView{
    
    private JPanel contentPane;
    private JFrame frame;
    private JTextField serviceNumberField;
    private JTextField secretCodeField; 
    private JLabel lblServiceNumber;
    private JLabel lblSecretCode;
    private JLabel lblMessage;
    private JButton loginButton;
    private Object myLockObj = new Object();
    private LinkedList <String> pendingActions = new LinkedList<String>();
    
    public FrameView() {
        this.createFrame();
    }
    
    private void createFrame() {
        frame = new JFrame();
        frame.setBounds(450, 190, 1014, 597);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        
        contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setVisible(true);
        
        JLabel lblNewLabel = new JLabel("Login");
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 46));
        lblNewLabel.setBounds(423, 13, 273, 93);
        contentPane.add(lblNewLabel);
        
        serviceNumberField = new JTextField();
        serviceNumberField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        serviceNumberField.setBounds(481, 170, 181, 48);
        contentPane.add(serviceNumberField);
        serviceNumberField.setColumns(10);

        secretCodeField = new JTextField();
        secretCodeField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        secretCodeField.setBounds(481, 236, 181, 48);
        contentPane.add(secretCodeField);
        secretCodeField.setColumns(10);
        
//        JPasswordField passwordField = new JPasswordField();
//        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
//        passwordField.setBounds(481, 286, 181, 48);
//        contentPane.add(passwordField);

        lblServiceNumber = new JLabel("Enter your service number:");
        lblServiceNumber.setBackground(Color.BLACK);
        lblServiceNumber.setForeground(Color.BLACK);
        lblServiceNumber.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblServiceNumber.setBounds(250, 166, 193, 52);
        contentPane.add(lblServiceNumber);

        lblSecretCode = new JLabel("Enter the secret code:");
        lblSecretCode.setForeground(Color.BLACK);
        lblSecretCode.setBackground(Color.CYAN);
        lblSecretCode.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblSecretCode.setBounds(250, 236, 193, 52);
        contentPane.add(lblSecretCode);
        
        lblMessage = new JLabel("");
        lblMessage.setForeground(Color.RED);
        lblMessage.setBackground(Color.RED);
        lblMessage.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblMessage.setBounds(400, 300, 193, 52);
        contentPane.add(lblMessage);

        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        loginButton.setBounds(545, 392, 162, 73);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                buttonClicked();
            }
        });
        
        contentPane.add(loginButton);

        frame.setContentPane(contentPane);        
        frame.setVisible(true);
    }
    
    
    public void buttonClicked() {    
        synchronized (myLockObj) {
            pendingActions.add("action");
            myLockObj.notify();
        }      
    }
    
    
    @Override
    public void triggerLogin() {
        synchronized(myLockObj) {
            while (pendingActions.isEmpty()) {
                try{
                    myLockObj.wait();
                } catch (InterruptedException ex){
                }
            }
            pendingActions.removeFirst();
        }
    }

    @Override
    public boolean getResult() {
        return true;
    }

    @Override
    public void showMessage(String msg) {
        if (SwingUtilities.isEventDispatchThread()) {
            lblMessage.setText(msg);
            serviceNumberField.setText(""); 
            secretCodeField.setText("");  
        } else {
            SwingUtilities.invokeLater(
                    new Runnable() {
                        public @Override void run() {
                            lblMessage.setText(msg);
                            serviceNumberField.setText(""); 
                            secretCodeField.setText("");  
                        }
                    } 
            );
        }
    }

    @Override
    public String getServiceNumber() {
        return this.serviceNumberField.getText();
    }

    @Override
    public String getSecretCode() {
        return this.secretCodeField.getText();
    }

    @Override
    public String checkView() {
        return "frame";
    }

}
