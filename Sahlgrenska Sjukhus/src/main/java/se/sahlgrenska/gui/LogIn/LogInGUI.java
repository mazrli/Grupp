package se.sahlgrenska.gui.LogIn;

import javax.swing.*;

public class LogInGUI extends JFrame { //extenda JFrame
    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel contentPanel;
    private JPanel passwordPanel;
    private JPanel bottomPanel;
    private JTextField textField1;
    private JPanel usernamePanel;
    private JTextField textField2;
    private JButton loginButton;
    private JButton quitButton;

    public LogInGUI() { //constructor

        this.setContentPane(mainPanel); //lägg till mainpanel i vår super class (JFrame)

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //programmet avslutas när man trycker X

        this.setLocationRelativeTo(null); //lägg den i center.

        this.pack(); // ??
    }


}
