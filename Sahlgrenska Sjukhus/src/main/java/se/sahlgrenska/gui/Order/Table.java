package se.sahlgrenska.gui.Order;

import se.sahlgrenska.gui.util.HelperGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Table extends HelperGUI implements ActionListener {


    // frame
    JFrame f;
    // Table
    JTable j;

    // Constructor
    Table()
    {
        // Frame initialization
        f = new JFrame();

        // Frame Title
        f.setTitle("Tabel for Order");

        String[][] data = {
                { "Defibrilator", "yes","4031", "CSE" },
                { "Mr", "yes","4031", "CSE" },
                { "Tony", "yes","4031", "CSE" },
                { "Phepe", "yes","4031", "CSE" },
        };

        // Column Names
        String[] columnNames = { "Item name", "Quantity", "Price", "OrderDate" };


        j = new JTable(data, columnNames);
        j.setBounds(30, 40, 200, 300);

        JScrollPane sp = new JScrollPane(j);
        f.add(sp);

        f.setSize(500, 200);

        //f.setVisible(true); <-- Använd inte setVisible i början!
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }


    // Driver  method

}
