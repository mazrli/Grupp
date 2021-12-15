package se.sahlgrenska.gui.Booking;

import se.sahlgrenska.gui.util.HelperGUI;
import se.sahlgrenska.gui.util.UtilGUI;
import se.sahlgrenska.gui.util.misc.SuggestionDropDownDecorator;
import se.sahlgrenska.gui.util.misc.TextComponentSuggestionClient;
import se.sahlgrenska.main.Driver;
import se.sahlgrenska.main.Util;
import se.sahlgrenska.sjukhus.Booking;
import se.sahlgrenska.sjukhus.Hospital;
import se.sahlgrenska.sjukhus.Room;
import se.sahlgrenska.sjukhus.Ward;
import se.sahlgrenska.sjukhus.item.Item;
import se.sahlgrenska.sjukhus.person.Person;
import se.sahlgrenska.sjukhus.person.employee.Accessibility;
import se.sahlgrenska.sjukhus.person.employee.Employee;
import se.sahlgrenska.sjukhus.person.patient.Patient;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
    private JTextField employeeTextField;
    private JComboBox dateBox;
    private LocalDateTime date;


    private Hospital hospital;
    private Booking booking;
    private int minWindowSize = 600;
    private int maxWindowSize = 700;
    private String[] columnNames = {"Redskap namn", "Kvantitet"};
    private DefaultTableModel tableModel;
    private boolean isActiveWard;
    private Room selectedRoom;


    private Employee currentUser;
    private Set<Employee> employees;


    DefaultListModel employeeListModel = new DefaultListModel();
    DefaultComboBoxModel timeBoxModel = new DefaultComboBoxModel();
    DefaultComboBoxModel dateBoxModel = new DefaultComboBoxModel();

    public BookingGUI() {
        init(mainPanel, "Skapa bokning", new Dimension(minWindowSize, maxWindowSize), Accessibility.RECEPTIONIST);


        currentUser = Driver.getCurrentUser();
        userOutLbl.setText("Användare: " + currentUser.getFullName());
        employees = Driver.getIOManager().getAllEmployees(currentUser.getLoginDetails());

        SuggestionDropDownDecorator.decorate(patPersNrTxtField, new TextComponentSuggestionClient(this::getSuggestions));
        SuggestionDropDownDecorator.decorate(employeeTextField, new TextComponentSuggestionClient(this::getEmployeeSuggestions));

        participationList.setModel(employeeListModel);
        dateBox.setModel(dateBoxModel);

        defaultBookingSetUp();


        for(int hours  = 12; hours > 0; hours--)
            timeBoxModel.addElement(hours + ":00" );


        for(int i = 30; i > 16; i--)
            dateBoxModel.addElement("2021-12-" + i);

        bookingDurationComboBox.setModel(timeBoxModel);

        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetRoomMenu();
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
                AddItemPopUp addItemPopUp = new AddItemPopUp(selectedRoom);
                addItemPopUp.setVisible(true);

                //refresha sidan!

                removeItemsBtn.setEnabled(true);
            }
        });


        removeItemsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                for (Map.Entry<Item, Integer> hosStorage : hospital.getHospitalsStoredItems().entrySet()) {
                    System.out.println(hosStorage);
                }

            }
        });
        createBtn.addActionListener(e -> {
            List<Employee> employees = new ArrayList<>();
            List<Patient> patients = new ArrayList<>();

            Patient patient = Driver.getIOManager().getPatient(patPersNrTxtField.getText());
            LocalDateTime time = Util.parseDate(bookingDateTxtField.getText());

            String note = noteTxtArea.getText();

            if(patient == null) {
                UtilGUI.error("Det finns ingen med detta personnumret.");
            }

            patients.add(patient);

            Ward ward = (Ward) wardComboBox.getSelectedItem();
            Room room = (Room) roomComboBox.getSelectedItem();

            Booking booking = new Booking(time, patients, employees, ward, room, note);

            System.out.println("Created booking!");
            System.out.println(booking.toString());

            Driver.getIOManager().saveBooking(booking);
        });

        addPartBtn.addActionListener(e -> {
            employeeListModel.addElement(employeeTextField.getText());
            employeeTextField.setText("");
        });

        removePartBtn.addActionListener(e -> {
            employeeListModel.removeElement(participationList.getSelectedValue());
        });
    }

    private List<String> getEmployeeSuggestions(String key) {
        if(key.isEmpty())
            return null;

        List<String> output = new ArrayList<>();
        final String input = key.toUpperCase();

        for(Person person : employees)
            if(person.getFullName().toUpperCase().startsWith(input)
                    || person.getFirstName().toUpperCase().startsWith(input)
                    || person.getLastName().toUpperCase().startsWith(input)
                    || person.getPersonNumber().startsWith(input)
            ) {
                output.add(person.toString());
            }


        return output.stream().limit(20).collect(Collectors.toList());
    }

    private List<String> getSuggestions(String key) {

        if(key.isEmpty())
            return null;

        List<String> output = new ArrayList<>();
        final String input = key.toUpperCase();

        Set<Person> persons = Driver.getHospital().getPersons();

        for(Person person : persons)
            if(person.getFullName().toUpperCase().startsWith(input)
                    || person.getFirstName().toUpperCase().startsWith(input)
                    || person.getLastName().toUpperCase().startsWith(input)
                    || person.getPersonNumber().startsWith(input)
            ) {
                output.add(person.toString());
            }


        return output.stream().limit(20).collect(Collectors.toList());
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
        participationList.clearSelection();

        patPersNrTxtField.setText("");
        employeeListModel.clear();



        // removeItemsBtn.setEnabled(false);                                                         GÖR DENNA OKOMMENTERAD SENARE PHOEBS!
        addItemsBtn.setEnabled(false);

    }


    private void defaultBookingSetUp() {
        dateOutLbl.setText(LocalDateTime.now().format(Util.dateFormatter));
        itemsTable.setBackground(Color.WHITE);
        itemsTable.setShowGrid(true);


        removePartBtn.setEnabled(true); //ändrade till true.

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


    private void fillRoomItems(Room room) {
        if (room != null) {
            Map<Item, Integer> roomItems = room.getItems();

            for (Map.Entry<Item, Integer> itemsInRoom : roomItems.entrySet()) {
                Item item = itemsInRoom.getKey();
                Integer itemQuantity = itemsInRoom.getValue();

                System.out.println(item.getName() + " " + itemQuantity);
                tableModel.addRow(new Object[]{item, itemQuantity});
            }
            itemsTable.setModel(tableModel);
            addItemsBtn.setEnabled(true);
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


