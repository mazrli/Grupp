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

public class ItemsStatus extends HelperGUI {
    private JPanel HuvudPanel;
    private JPanel Menubar;
    private JScrollPane HuvudScrollPane;
    private JPanel delarPanel;
    private JPanel TablePanel;
    private JScrollPane TableScrollPanel;
    private JTable table;
    private JButton ButtonCancel;
    private JButton ButtonOk;


    Ordertest Ordertest = new Ordertest();

    public ItemsStatus() {
        this.HuvudPanel = HuvudPanel;
        this.HuvudScrollPane = HuvudScrollPane;
        this.TablePanel = TablePanel;
        this.delarPanel = delarPanel;
        this.TableScrollPanel = TableScrollPanel;
        this.table = table;
        this.ButtonCancel = ButtonCancel;
        this.ButtonOk = ButtonOk;


        init(HuvudPanel, "ItesmsStatus", new Dimension(700, 700), Accessibility.RECEPTIONIST);


        Menubar.addAncestorListener(new AncestorListener() {
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

        ButtonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Driver.getMainMenu().setVisible(true);
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
                {"Phepe", "yes", "4031", "CSE"},
                {"Williamxpxs", "no", "4325", "cdsaZcfe"},
                {"Phepe", "yes", "4031", "CSE"},
                {"Phepe", "yes", "4031", "CSE"},
                {"Phepe", "yes", "4031", "CSE"},
                {"Phepe", "yes", "4031", "CSE"},
                {"Phepe", "yes", "4031", "CSE"},
                {"Phepe", "yes", "4031", "CSE"},
                {"Phepe", "yes", "4031", "CSE"},

        };
        table = new JTable(data, columns);
        UtilGUI.changeJTableHeaderColour(table, tableHeaderColour);



    }
}







