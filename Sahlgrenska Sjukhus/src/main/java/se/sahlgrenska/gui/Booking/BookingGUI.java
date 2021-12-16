package se.sahlgrenska.gui.Booking;

import se.sahlgrenska.gui.util.HelperGUI;
import se.sahlgrenska.gui.util.UtilGUI;
import se.sahlgrenska.gui.util.components.JTextFieldPlaceholder;
import se.sahlgrenska.main.Driver;
import se.sahlgrenska.main.Util;
import se.sahlgrenska.sjukhus.Booking;
import se.sahlgrenska.sjukhus.Hospital;
import se.sahlgrenska.sjukhus.Room;
import se.sahlgrenska.sjukhus.Ward;
import se.sahlgrenska.sjukhus.item.Item;
import se.sahlgrenska.sjukhus.person.employee.Accessibility;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    private JComboBox wardComboBox; // = new DefaultComboBoxModel<>();
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

    private Hospital hospital;
    private Booking booking;
    private int minWindowSize = 600;
    private int maxWindowSize = 700;
    private String[] columnNames = {"Redskap namn", "Kvantitet"};
    private DefaultTableModel tableModel;
    private boolean isActiveWard;
    private Room selectedRoom;

    public BookingGUI() {
        init(mainPanel, "Skapa bokning", new Dimension(minWindowSize, maxWindowSize), Accessibility.RECEPTIONIST);

        defaultBookingSetUp();
        BookingGUI booking = this;
        userOutLbl.setText(Driver.getCurrentUser().toString());
        patPersNrTxtField = new JTextFieldPlaceholder("YYYYMMDD-YYYY");

        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Driver.getMainMenu().setVisible(true);
            }
        });

        wardComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (checkSelectedIndexIsFirstOption(wardComboBox)) {
                    isActiveWard = false;
                    System.out.println("You've not selected a ward!");
                    resetRoomMenu();
                    return;
                }

                Ward selectedWard = (Ward) wardComboBox.getSelectedItem();
                roomComboBox.setEnabled(true);
                isActiveWard = true;
                fillComboBoxRooms(selectedWard);

                if (!checkSelectedIndexIsFirstOption(roomComboBox)) {
                    Room selectedRoom = (Room) roomComboBox.getSelectedItem();
                    fillRoomItems(selectedRoom);
                }
            }
        });

        roomComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                emptyItemList();
                if (isActiveWard) {
                    selectedRoom = (Room) roomComboBox.getSelectedItem();
                    fillRoomItems(selectedRoom);
                }
            }
        });


        addItemsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddItemPopUp addItemPopUp = new AddItemPopUp(selectedRoom, booking);
                addItemPopUp.setVisible(true);

                removeItemsBtn.setEnabled(true);

            }


        });


        removeItemsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int outOfBoundsValue = -1;
                int row = itemsTable.getSelectedRow();
                int col = itemsTable.getSelectedColumn();

                if (selectedRoom.getItems().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Inget redskap finns kvar i listan", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (row <= outOfBoundsValue && col <= outOfBoundsValue) {
                    JOptionPane.showMessageDialog(null, "Inget redskap har valts", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Item item = (Item) itemsTable.getModel().getValueAt(row, 0);
                int quantity = (Integer) itemsTable.getModel().getValueAt(row, 1);

                if (item == null || quantity < 0) {
                    System.out.println("Item fanns inte!");
                    return;
                }


                hospital.addItem(item, quantity);
                selectedRoom.removeItem(item, quantity);
                //remove from Room and hospital add to hospital amount
                System.out.println(item + " was selected with the quantity " + hospital.getItemStorageQuantity(item));
                itemsTable.clearSelection();
                fillRoomItems(selectedRoom);

            }
        });

/*
        roomComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                emptyItemList();
                if (isActiveWard) {
                    selectedRoom = (Room) roomComboBox.getSelectedItem();
                    fillRoomItems(selectedRoom);
                }
            }
        });
*/


    }


    private boolean checkSelectedIndexIsFirstOption(JComboBox combo) {
        return combo.getSelectedIndex() == 0;
    }

    private void emptyItemList() {
        tableModel.setRowCount(0);
    }


    private void resetRoomMenu() {
        roomComboBox.removeAllItems();
        roomComboBox.insertItemAt("Välj rum", 0);
        roomComboBox.setSelectedIndex(0);
        roomComboBox.setEnabled(false);

        removeItemsBtn.setEnabled(false);
        addItemsBtn.setEnabled(false);

    }


    private void defaultBookingSetUp() {
        dateOutLbl.setText(LocalDateTime.now().format(Util.dateFormatter));
        itemsTable.setBackground(Color.WHITE);
        itemsTable.setShowGrid(true);


        removePartBtn.setEnabled(false);

        wardComboBox.insertItemAt("Välj avdelning", 0);
        fillComboBoxWards(hospital.getWards());
        wardComboBox.setSelectedIndex(0);
        resetRoomMenu();
        createDefaultTableValues();

    }


    private void fillComboBoxWards(List<Ward> wards) {
        for (int i = 0; i < wards.size(); i++) {
            wardComboBox.addItem(wards.get(i));
        }
    }

    private void fillComboBoxRooms(Ward ward) {
        roomComboBox.removeAllItems();
        Set<Room> wardRooms = ward.getRooms();
        if (wardRooms != null) {
            for (Room r : wardRooms) {
                roomComboBox.addItem(r);
            }
        }
    }


    private void createDefaultTableValues() {
        tableModel.addColumn(columnNames[0]);
        tableModel.addColumn(columnNames[1]);
    }
    public Room getSelectedRoom(){
        return (Room) roomComboBox.getSelectedItem();
    }


    public void fillRoomItems(Room room) {
        if (room != null) {

            Map<Item, Integer> roomItems = room.getItems();
            emptyItemList();
            for (Map.Entry<Item, Integer> itemsInRoom : roomItems.entrySet()) {
                Item item = itemsInRoom.getKey();
                Integer itemQuantity = itemsInRoom.getValue();

                System.out.println(item.getName() + " " + itemQuantity);
                tableModel.addRow(new Object[]{item, itemQuantity});
            }
            itemsTable.setModel(tableModel);
            addItemsBtn.setEnabled(true);
            removeItemsBtn.setEnabled(true);
        }
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
        hospital = Driver.getHospital();

        Color tableHeaderColour = new Color(199, 199, 199);

        tableModel = new DefaultTableModel();
        itemsTable = new JTable(tableModel);
        UtilGUI.changeJTableHeaderColour(itemsTable, tableHeaderColour);
    }
}


