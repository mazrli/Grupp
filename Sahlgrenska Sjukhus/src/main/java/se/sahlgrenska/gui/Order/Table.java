package se.sahlgrenska.gui.Order;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;



public class Table {
    OrderGUI OrderGUI = new OrderGUI();

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
        f.setTitle("JTable Example");

        // Data to be displayed in the JTable
        String[][] data = {
                { "Defibrilator", "yes","4031", "CSE" },
                { "Mr", "yes","4031", "CSE" },
                { "Tony", "yes","4031", "CSE" },
                { "Phepe", "yes","4031", "CSE" },
        };

        // Column Names
        String[] columnNames = { "Item name", "Quantity", "Price", "OrderDate" };

        // Initializing the JTable
        j = new JTable(data, columnNames);
        j.setBounds(30, 40, 200, 300);

        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(j);
        f.add(sp);
        // Frame Size
        f.setSize(500, 200);
        // Frame Visible = true
        f.setVisible(true);
    }

    // Driver  method
    public static void main(String[] args)
    {
        new Table();
    }
}
