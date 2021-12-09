package se.sahlgrenska.gui.Booking;

import se.sahlgrenska.gui.util.HelperGUI;
import se.sahlgrenska.main.Driver;
import se.sahlgrenska.main.Util;
import se.sahlgrenska.sjukhus.Booking;
import se.sahlgrenska.sjukhus.person.employee.Accessibility;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public class BookingGUI extends HelperGUI {

    private JPanel mainPanel;
    private JPanel bannerPanel;
    private JPanel bookingPanel;
    private JLabel userLbl;
    private JLabel titleLbl;
    private JLabel userOutLbl;
    private JLabel dateOutLbl;
    private JPanel menuPanel;
    private JPanel rightPanel;
    private JPanel leftPanel;
    private JPanel bottomPanel;
    private JTextArea noteTxtArea;
    private JLabel noteLbl;
    private JPanel noteTxtAreaPanel;
    private JPanel bookingBtnPanel;
    private JButton cancelBtn;
    private JButton createBtn;
    private JTextField patPersNrTxtField;
    private JLabel persLbl;
    private JPanel persNrFieldPanel;
    private JTextField bookingDateTxtField;
    private JLabel bookingDateLbl;
    private JPanel bookingFieldPanel;
    private JComboBox bookingDurationComboBox;
    private JPanel bookingDurationFieldPanel;
    private JLabel bookingDurationLbl;
    private JList participationList;
    private JButton addPartBtn;
    private JButton removePartBtn;
    private JLabel participationListLbl;
    private JPanel participationPanel;
    private JPanel bookingLocationPanel;
    private JPanel wardPanel;
    private JPanel roomPanel;
    private JComboBox wardComboBox;
    private JComboBox roomComboBox;
    private JLabel wardLbl;
    private JLabel roomLbl;
    private JPanel neededItemsPanel;
    private JTable itemsTable;
    private JButton addItemsBtn;
    private JButton removeItemsBtn;
    private JPanel titlePanel;
    private JScrollPane itemScrollPanel;
    private LocalDateTime date;


    private Booking booking;
    private int minWindowSize = 600;
    private int maxWindowSize = 700;




    public BookingGUI() {
        init(mainPanel, "Skapa bokning", new Dimension(minWindowSize, maxWindowSize), Accessibility.RECEPTIONIST);

        setUpBookingData();

        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Driver.getMainMenu().setVisible(true);
            }
        });

    }




    private void setUpBookingData(){
      dateOutLbl.setText(LocalDateTime.now().format(Util.dateFormatter));
    itemsTable.setBackground(Color.WHITE);
    }


    private void changeTableHeaderText(JTable table, Color color){
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setReorderingAllowed(false);
        tableHeader.setOpaque(false);
        tableHeader.setBackground(color);
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here

        String [] columns = {"Item name","Quantity"};
        String [][] data = {{"Defibrilator","5"},{"MRI","2"},{"Panodil", "10"},{"Defibrilator","5"},{"MRI","2"},{"Panodil", "10"},{"Defibrilator","5"},{"MRI","2"},{"Panodil", "10"},{"Defibrilator","5"},{"MRI","2"},{"Panodil", "10"}};
        itemsTable = new JTable(data,columns);
        changeTableHeaderText(itemsTable, new Color(199,199,199));


    }
}
