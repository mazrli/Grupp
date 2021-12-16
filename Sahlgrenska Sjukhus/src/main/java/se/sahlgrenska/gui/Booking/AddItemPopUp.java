package se.sahlgrenska.gui.Booking;

import se.sahlgrenska.gui.util.HelperGUI;
import se.sahlgrenska.gui.util.misc.SuggestionDropDownDecorator;
import se.sahlgrenska.gui.util.misc.TextComponentSuggestionClient;
import se.sahlgrenska.main.Driver;
import se.sahlgrenska.sjukhus.Hospital;
import se.sahlgrenska.sjukhus.item.Item;


import java.awt.print.Book;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.TreeMap;

import java.util.*;

import se.sahlgrenska.sjukhus.item.Medicine;
import se.sahlgrenska.sjukhus.Room;
import se.sahlgrenska.sjukhus.person.employee.Accessibility;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;

public class AddItemPopUp extends HelperGUI {
    private JPanel mainPanel;
    private JPanel topPanel;
    private JLabel titleLbl;
    private JPanel centerPanel;
    private JPanel bottomPanel;
    private JPanel searchItemPanel;
    private JPanel quantityPanel;
    private JTextField searchItemTextField;
    private JPanel mainPanelBtnPanel;
    private JLabel searchItemLbl;
    private JButton searchItemBtn;
    private JButton cancelBtn;
    private JButton addItemBtn;
    private JTextField quantityTxtField;
    private JButton increaseQuantBtn;
    private JButton decreaseQuantBtn;
    private JPanel quantityBtnPanel;
    private JPanel quantitySearchPanel;
    private JLabel enterQuantityLbl;
    private JPanel inputPanel;
    private JLabel quantityOutLbl;
    private Hospital hospital;

    private Item selectedItem;
    private int quantity = 1;
    private TreeMap<String, Integer> orderedHospitalStoredItems;
    private int maxQuantity = 10;


    public AddItemPopUp(Room room, BookingGUI booking) {
        init(mainPanel, "Nytt redskap", new Dimension(350, 400), Accessibility.NONE);
        hospital = Driver.getHospital();

        SuggestionDropDownDecorator.decorate(searchItemTextField, new TextComponentSuggestionClient(this::getSuggestions));

        decreaseQuantBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                keepButtonsInRange(--quantity);
            }
        });

        increaseQuantBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                keepButtonsInRange(quantity++);
            }
        });


        addItemBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userInput = searchItemTextField.getText();

                if (orderedHospitalStoredItems == null) {
                    System.out.println("NULL ORDERHOSPITALLIST");
                    return;
                }

                if (userInput.isEmpty() || quantityTxtField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Fyll i båda sökfälten", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (orderedHospitalStoredItems.containsKey(userInput)) {

                    selectedItem = findSearchedItem(userInput);
                    if (selectedItem == null) {
                        return;
                    }
                    quantity = orderedHospitalStoredItems.get(userInput);
                    maxQuantity = quantity;

                    if (validateQuantityInput()) {

                        int hospitalCurrentQuantity = hospital.getItemStorageQuantity(selectedItem);

                        boolean isEnoughItemsToWithdraw = (hospitalCurrentQuantity - quantity) >= 0;
                        if (isEnoughItemsToWithdraw) {

                            hospital.removeItem(selectedItem, quantity);
                            room.addItems(selectedItem, quantity);

                            booking.fillRoomItems(booking.getSelectedRoom());
                            //   comboBox.setSelectedItem(selectedItem);
                            //roomComboBox.getSelectedItem();
                            //  roomComboBox.actionPerformed();
                            // JOptionPane.showMessageDialog(null, "Items name: " + selectedItem.getName() + " Max amount: " + maxQuantity + " Du valde: " + quantity, "Summary", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }

                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Angivna redskapet finns inte hos sjukhuset", "Error", JOptionPane.ERROR_MESSAGE);
                }


            }
        });

        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });


    }


    private boolean validateQuantityInput() {
        boolean isValid = false;
        try {
            int userInp = Integer.parseInt(quantityTxtField.getText());

            if (isValidAmount(userInp)) {
                quantity = userInp;
                isValid = true;
            } else {
                JOptionPane.showMessageDialog(null, "Ogiltig kvantitet för det valda redskapet! Det finns totalt " + maxQuantity + "st tillgängliga", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException exception) {
            System.out.println(exception.toString() + " was not an integer number! Please enter integer numerical input!");

        }
        return isValid;
    }

    private boolean isValidAmount(int inp) {
        return inp > 0 && inp <= maxQuantity;
    }

    private void keepButtonsInRange(int quantity) {
        increaseQuantBtn.setEnabled(true);
        decreaseQuantBtn.setEnabled(true);
        if (quantity >= maxQuantity) {
            increaseQuantBtn.setEnabled(false);
        } else if (quantity <= 1) {
            decreaseQuantBtn.setEnabled(false);
        }
        quantityTxtField.setText(quantity + "");
    }


    private Item findSearchedItem(String itemName) {

        HashMap<Item, Integer> storedItems = (HashMap) hospital.getHospitalsStoredItems();
        for (Map.Entry<Item, Integer> hospitalsItems : storedItems.entrySet()) {

            if (hospitalsItems.getKey().getName().equalsIgnoreCase(itemName)) {
                return hospitalsItems.getKey();
            }
        }
        return null;
    }


    private void convertStoredItemMapToStringMap(Map<Item, Integer> itemsMap) {

        if (itemsMap == null || itemsMap.isEmpty()) {
            System.out.println("ItemsMap to be converted is null/empty");
            return;
        }

        orderedHospitalStoredItems = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        HashMap<Item, Integer> hospitalItem = (HashMap) itemsMap;

        for (Map.Entry<Item, Integer> ent : hospitalItem.entrySet()) {
            System.out.println(ent);
            orderedHospitalStoredItems.put(ent.getKey().toString(), ent.getValue());
        }

    }

    private void printTreeMap(TreeMap<String, Integer> hospitalItemStorageOrg) {
        for (Map.Entry<String, Integer> ent : hospitalItemStorageOrg.entrySet()) {
            System.out.println(ent);
        }
    }

    public Item getSelectedItem() {
        return selectedItem;
    }

    public int getSelectedItemQuantity() {
        return quantity;
    }


    private List<String> getSuggestions(String key) {

        if (key == null || key.isEmpty()) {
            System.out.println(key + " returned null or was empty");
        }

        convertStoredItemMapToStringMap(hospital.getHospitalsStoredItems());

        if (orderedHospitalStoredItems != null && orderedHospitalStoredItems.containsKey(key)) {
            System.out.println(key + " existed in hospitalStorage och sjukhuset har " + orderedHospitalStoredItems.get(key).toString());
        }

        List<String> itemStorageNames = new ArrayList<String>(orderedHospitalStoredItems.keySet());

        return itemStorageNames.stream().limit(10).collect(Collectors.toList());

    }

}
