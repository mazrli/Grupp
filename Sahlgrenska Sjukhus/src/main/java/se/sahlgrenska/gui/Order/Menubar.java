package se.sahlgrenska.gui.Order;


import se.sahlgrenska.gui.util.HelperGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menubar extends HelperGUI implements ActionListener {


    Menubar(){
        this.setDefaultCloseOperation(HelperGUI.EXIT_ON_CLOSE);
        this.setSize(600, 600);
        this.setLayout(new FlowLayout());

        JMenuBar menuBar = new JMenuBar();

        JMenu FileMenu = new JMenu("File");

        JMenu editMenu = new JMenu("F");


        this.setMenuBar(menuBar);
        this.setVisible(true);
    }

    private void setMenuBar(JMenuBar menuBar) {
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
