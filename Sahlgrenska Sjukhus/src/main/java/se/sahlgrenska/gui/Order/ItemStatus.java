package se.sahlgrenska.gui.Order;

import se.sahlgrenska.gui.util.HelperGUI;
import se.sahlgrenska.main.Driver;
import se.sahlgrenska.sjukhus.person.employee.Accessibility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;






public class ItemStatus<panel1> extends HelperGUI {

    private JPanel panel1;
    private JTextField textField1;
    private JFormattedTextField formattedTextField2;
    private JButton Cancel;
    private JButton Send;



    JPanel ItemStatus;



    public ItemStatus() {
        this.panel1 = panel1;



        init(panel1, "Item Status", new Dimension(700, 700),Accessibility.RECEPTIONIST);






        Cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Driver.getMainMenu().setVisible(true);

            }
        });

        Send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Driver.getMainMenu().setVisible(true);

            }
        });






    }
}
