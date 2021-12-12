package se.sahlgrenska.gui.Order;


import se.sahlgrenska.gui.util.HelperGUI;
import se.sahlgrenska.main.Driver;
import se.sahlgrenska.sjukhus.person.employee.Accessibility;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/*
    Du bör byta namn till "OrderGUI" istället (shift + f6)
    annars får vi problem med den andra order data klassen.
 */

public class OrderGUI<upstream> extends HelperGUI {

    private JPanel panel1;
    private JScrollPane scrollPane1;
    private JLabel CreateOrder;
    private JButton Remove;
    private JButton Edit;
    private JButton Add;
    private JButton Cancel;
    private JButton SendOrder;
    private JTextField textField1;
    private JLabel Totalsum;
    private JFormattedTextField formattedTextField1;
    private JTextArea textArea1;
    private JTable table1;
    private JPanel Menu;


    ItemStatus ItemStatus = new ItemStatus();


    public OrderGUI() {


        this.panel1 = panel1;
        this.scrollPane1 = scrollPane1;


        init(panel1, "Order", new Dimension(700, 700),Accessibility.RECEPTIONIST);


        Cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Driver.getMainMenu().setVisible(true);


            }
        });



        SendOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Driver.getMainMenu().setVisible(true);
                new button();
            }
        });

        Add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setVisible(false);
                Driver.getMainMenu().setVisible(true);
                new ItemStatus();
            }
        });
        Edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Driver.getMainMenu().setVisible(true);
                new ItemStatus();
            }
        });

        Remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Driver.getMainMenu().setVisible(true);
                new ItemStatus();
            }
        });


        Menu.addAncestorListener(new AncestorListener() {
            @Override
            public void ancestorAdded(AncestorEvent event) {
                setDefaultCloseOperation(HelperGUI.EXIT_ON_CLOSE);
                setSize(600, 600);
                setLayout(new FlowLayout());

                JMenuBar menuBar = new JMenuBar();

                JMenu fileMenu = new JMenu("File");

                JMenu editMenu = new JMenu("Edit");
                JMenu helpMenu = new JMenu("Help");
                menuBar.add(fileMenu);
                menuBar.add(editMenu);
                menuBar.add(helpMenu);
                setJMenuBar(menuBar);
                setVisible(true);
            }

            @Override
            public void ancestorRemoved(AncestorEvent event) {

            }

            @Override
            public void ancestorMoved(AncestorEvent event) {

            }
        });









        //den ska ej vara visible i början
        //setVisible(true);




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
