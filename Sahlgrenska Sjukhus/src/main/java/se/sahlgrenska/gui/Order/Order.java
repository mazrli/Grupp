package se.sahlgrenska.gui.Order;


import se.sahlgrenska.gui.util.HelperGUI;
import se.sahlgrenska.sjukhus.person.employee.Accessibility;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Order extends HelperGUI {

    private JPanel panel1;
    private JScrollPane scrollPane1;
    private JLabel CreateOrder;
    private JButton Remove;
    private JButton Edit;
    private JFormattedTextField formattedTextField1;
    private JButton Add;
    private JButton Cancel;
    private JButton SendOrder;
    private JTextField textField1;
    private JFormattedTextField ItemName1;
    private JFormattedTextField Quantity2;
    private JFormattedTextField Price1;
    private JFormattedTextField OrderDate2;
    private JFormattedTextField OrderDate1;
    private JFormattedTextField Price2;
    private JFormattedTextField Quantity1;
    private JFormattedTextField ItemName2;
    private JLabel Totalsum;
    private JLabel ItemName;
    private JPanel table;
    private JTextArea textArea;


    public Order() {
        this.panel1 = panel1;
        this.scrollPane1 = scrollPane1;

        init(panel1, "Order", Accessibility.RECEPTIONIST );

        Cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });








    }

  /*  public  class SendOrder extends JFrame implements ActionListener {
        private JLabel usernamePanel;

        public SendOrder(){
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }






        public static void main(String[ ] args){
            JFrame f = new JFrame("SendOrder");
            JButton b = new JButton("Skicka");
            b.setBackground(Color.black);
            f.add(b);
            f.setVisible(true);
            f.setLayout(null);

        }

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }*/








}
