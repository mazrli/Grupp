package se.sahlgrenska.gui.Order;

import se.sahlgrenska.gui.util.HelperGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class button extends HelperGUI implements ActionListener {

    JButton button;

    OrderGUI OrderGUI = new OrderGUI();




    button(){

        JButton button = new JButton();
        button.setBounds(200, 100, 100, 50 );
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.BOTTOM);


        button.addActionListener(e -> System.out.println("poo") );
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(500 ,500);
        this.setVisible(true);
        this.add(button);
        this.setFocusable(false);
        this.add(button);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button){
            System.out.println("poo");
        }
    }








}
