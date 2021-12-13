package se.sahlgrenska.gui.ordertest;

import se.sahlgrenska.gui.util.HelperGUI;
import se.sahlgrenska.gui.util.UtilGUI;
import se.sahlgrenska.main.Driver;
import se.sahlgrenska.sjukhus.person.employee.Accessibility;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ordertest extends HelperGUI {
    private JPanel panel;
    private JScrollPane HuvudScrollPane;
    private JPanel MenuBar;
    private JPanel TablePanel;
    private JPanel NotesPanel;
    private JPanel MenuPanel;
    private JPanel HuvudPanel;
    private JScrollPane TabelScrololPanel;
    private JTable table;





    public Ordertest(){
        this.HuvudPanel = HuvudPanel;
        this.panel = panel;
        this.MenuPanel = MenuPanel;
        this.HuvudScrollPane =HuvudScrollPane;
        this.TabelScrololPanel = TabelScrololPanel;
        this.TablePanel = TablePanel;
        this.table =table;


        init(panel, "Order", new Dimension(700, 1000), Accessibility.RECEPTIONIST);








        MenuBar.addAncestorListener(new AncestorListener() {
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

                setVisible(true);
            }

            @Override
            public void ancestorRemoved(AncestorEvent event) {

            }

            @Override
            public void ancestorMoved(AncestorEvent event) {

            }
        });








    }
    private void createUIComponents() {
        // TODO: place custom component creation code here

        Color tableHeaderColour = new Color(199, 199, 199);

        String[] columns = {"Item name", "Quantity", "Price", "OrderDate"};
        String[][] data = {
                {"Defibrilator", "yes", "4031", "CSE"},
                {"Mr", "yes", "4031", "CSE"},
                {"Tony", "yes", "4031", "CSE"},
                {"Phepe", "yes", "4031", "CSE"},};
        table = new JTable(data, columns);
        UtilGUI.changeJTableHeaderColour(table, tableHeaderColour);



    }




}



