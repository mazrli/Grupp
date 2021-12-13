package se.sahlgrenska.gui.Order;

import se.sahlgrenska.gui.util.HelperGUI;
import se.sahlgrenska.gui.util.UtilGUI;
import se.sahlgrenska.main.Driver;
import se.sahlgrenska.sjukhus.person.employee.Accessibility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class OrderGUI extends HelperGUI {

    private JPanel panel;
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
<<<<<<< Updated upstream
    private JPanel huvud;
    private JPanel huvudpenal;
    private JPanel menu;
    private JPanel notesbar;
    private JPanel testpanel;
    private JPanel sokpanel;
    private JPanel tabelpanel;
    private JScrollPane tabelScrolpanel;
    private JTable ordertabel;
    private JPanel sokpanel1;
    private JPanel extrapanel;
    private JPanel notespanel;

=======
    private JTable OrderTable;
    private JScrollPane tableScrollPane;
    private JPanel OrderPane;


    ItemStatus ItemStatus = new ItemStatus();

>>>>>>> Stashed changes


    public OrderGUI() {
        this.Notes = Notes;
        this.panel = panel;

        this.scrollPane1 = scrollPane1;


<<<<<<< Updated upstream
        init(panel, "Order", new Dimension(700, 1000), Accessibility.RECEPTIONIST);

=======
        init(panel1, "Order", new Dimension(700, 1000), Accessibility.RECEPTIONIST);
>>>>>>> Stashed changes


        Cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Driver.getMainMenu().setVisible(true);


            }
        });


<<<<<<< Updated upstream
       /* SendOrder.addActionListener(new ActionListener() {
=======
        SendOrder.addActionListener(new ActionListener() {
>>>>>>> Stashed changes
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Driver.getMainMenu().setVisible(true);

            }
        });

        Add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setVisible(false);
                Driver.getMainMenu().setVisible(true);

            }
        });
        Edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Driver.getMainMenu().setVisible(true);

            }
        });

        Remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Driver.getMainMenu().setVisible(true);

            }
        });
*/

        //MenuBar called it, and I got for Exit and for should look like a real program, user can user it now.
        //  aziz
/*        Menu.addAncestorListener(new AncestorListener() {
            @Override
            public void ancestorAdded(AncestorEvent event) {


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


<<<<<<< Updated upstream
            }*/
=======
            }
>>>>>>> Stashed changes

/*            @Override
            public void ancestorRemoved(AncestorEvent event) {

            }

            @Override
            public void ancestorMoved(AncestorEvent event) {

            }
        });*/


<<<<<<< Updated upstream





=======
        //den ska ej vara visible i början
        //setVisible(true);
>>>>>>> Stashed changes


    }


<<<<<<< Updated upstream
=======

    private void createUIComponents () {
        // TODO: place custom component creation code here
        Color tableHeaderColour = new Color(199, 199, 199);


        String[][] data = {
                {"Defibrilator", "yes", "4031", "CSE"},
                {"Mr", "yes", "4031", "CSE"},
                {"Tony", "yes", "4031", "CSE"},
                {"Phepe", "yes", "4031", "CSE"},
        };

        // Column Names
        String[] columnNames = {"Item name", "Quantity", "Price", "OrderDate"};
        OrderTable = new JTable(data, columnNames);
        UtilGUI.changeJTableHeaderColour(OrderTable, tableHeaderColour);

>>>>>>> Stashed changes

    }


  /*  }*/


    }
    private void createUIComponents () {
        // TODO: place custom component creation code here
        Color tableHeaderColour = new Color(199, 199, 199);


        String[][] data = {
                {"Defibrilator", "yes", "4031", "CSE"},
                {"Mr", "yes", "4031", "CSE"},
                {"Tony", "yes", "4031", "CSE"},
                {"Phepe", "yes", "4031", "CSE"},
        };

        // Column Names
        String[] columnNames = {"Item name", "Quantity", "Price", "OrderDate"};
        ordertabel = new JTable(data, columnNames);
        UtilGUI.changeJTableHeaderColour(ordertabel, tableHeaderColour);



    }



}
<<<<<<< Updated upstream






=======
>>>>>>> Stashed changes
