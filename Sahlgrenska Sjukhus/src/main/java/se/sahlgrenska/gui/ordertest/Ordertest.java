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

public class Ordertest extends HelperGUI  implements ActionListener{
    private JPanel panel;
    private JScrollPane HuvudScrollPane;
    private JPanel MenuBar;
    private JPanel TablePanel;
    private JPanel NotesPanel;
    private JPanel MenuPanel;
    private JPanel HuvudPanel;
    private JScrollPane TabelScrololPanel;
    private JTable table;
    private JButton buttonAdd;
    private JButton buttonEdit;
    private JButton buttonRemove;
    private JFormattedTextField formattedTextField1;
    private JTextArea textArea;
    private JButton buttonCancel;
    private JButton buttonSendOrder;
    private JPanel datom;
    private JLabel testDatom;
    private JLabel testordernotes;
    private JPanel sumPanel;
    private JPanel AddPanel;
    private JLabel testUserName;
    private JLabel userName;
    private JPanel searchPanel;


    public Ordertest(){
        this.HuvudPanel = HuvudPanel;
        this.panel = panel;
        this.MenuPanel = MenuPanel;
        this.HuvudScrollPane =HuvudScrollPane;
        this.TabelScrololPanel = TabelScrololPanel;
        this.TablePanel = TablePanel;
        this.table = table;
        this.textArea = textArea;
        this.searchPanel = searchPanel;





        init(panel, "Order", new Dimension(700, 700), Accessibility.RECEPTIONIST);


        getContentPane().setBackground(Color.lightGray);


/*        ImageIcon image = new ImageIcon("https://avatars.dicebear.com/api/male/sddddf.svg");
        setIconImage(image.getImage());*/

        setResizable(true);



        MenuBar.addAncestorListener(new AncestorListener() {
            @Override
            public void ancestorAdded(AncestorEvent event) {
                setLayout(new FlowLayout());

                //använder för att kunna ändra storlek
                setResizable(true);

                JMenuBar menuBar = new JMenuBar(); // change color of backgrund


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

        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setResizable(true);
                setVisible(false);
                Driver.getMainMenu().setVisible(true);
            }
        });

        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


/*        searchPanel = new JPanel((LayoutManager) Serach.f);
        searchPanel.setVisible(true);*/












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
                {"Williamfps", "yes", "4031", "CSE"},
                {"Phepe", "yes", "4031", "CSE"},
                {"Phepe", "yes", "4031", "CSE"},
                {"Phepe", "yes", "4031", "CSE"},
                {"Phepe", "yes", "4031", "CSE"},
                {"Phepe", "yes", "4031", "CSE"},
                {"Phepe", "yes", "4031", "CSE"},

        };
        table = new JTable(data, columns);
        UtilGUI.changeJTableHeaderColour(table, tableHeaderColour);
        getContentPane().setBackground(Color.lightGray);



    }


    @Override
    public void actionPerformed(ActionEvent e) {


    }
}




