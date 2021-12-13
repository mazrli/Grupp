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

public class OrderGUI extends HelperGUI {

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
    private JTextArea Notes;
    private JPanel Menu;
    private JTable table1;
    private JScrollPane table;
    private JPanel Orderfortable;



    ItemStatus ItemStatus = new ItemStatus();
    Table orderTable = new Table();


    public OrderGUI() {
        this.Notes = Notes;
        this.panel1 = panel1;

        this.scrollPane1 = scrollPane1;



        init(panel1, "Order", new Dimension(700, 1000),Accessibility.RECEPTIONIST);







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


        //MenuBar called it, and I got for Exit and for should look like a real program, user can user it now.
        //  aziz
        Menu.addAncestorListener(new AncestorListener() {
            @Override
            public void ancestorAdded(AncestorEvent event) {

                //setDefaultCloseOperation(HelperGUI.EXIT_ON_CLOSE); <--- Använd inte setDefaultOperation!

                setLayout(new FlowLayout());

                JMenuBar menuBar = new JMenuBar();


                JMenu fileMenu = new JMenu("File");

                JMenu editMenu = new JMenu("Edit");
                JMenu helpMenu = new JMenu("Help");

                JMenuItem loadItem = new JMenuItem("Load");
                JMenuItem SaveItem = new JMenuItem("Save");
                JMenuItem ExitItem = new JMenuItem("Exit");





                ExitItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        setVisible(false);
                        Driver.getMainMenu().setVisible(true);
                    }
                });

                fileMenu.add(loadItem);
                fileMenu.add(ExitItem);

                menuBar.add(fileMenu);
                menuBar.add(editMenu);
                menuBar.add(helpMenu);
                setJMenuBar(menuBar);


                //setVisible(true); <-- Använd inte setVisible i början!
            }

            @Override
            public void ancestorRemoved(AncestorEvent event) {

            }

            @Override
            public void ancestorMoved(AncestorEvent event) {

            }
        });







        Orderfortable.addAncestorListener(new AncestorListener() {
            @Override
            public void ancestorAdded(AncestorEvent event) {
                // frame
                JFrame f;
                // Table
                JTable j;
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
