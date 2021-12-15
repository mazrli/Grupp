package se.sahlgrenska.gui.Booking;

import se.sahlgrenska.gui.util.HelperGUI;
import se.sahlgrenska.gui.util.misc.SuggestionDropDownDecorator;
import se.sahlgrenska.gui.util.misc.TextComponentSuggestionClient;
import se.sahlgrenska.main.Driver;
import se.sahlgrenska.sjukhus.Hospital;
import se.sahlgrenska.sjukhus.item.Item;


import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.TreeMap;

import java.util.*;

import se.sahlgrenska.sjukhus.person.employee.Accessibility;


import javax.swing.*;
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
    private Hospital hospital;


    private Item selectedItem;
    private int quantity = 0;
    private TreeMap<String, Integer> orderedHospitalStoredItems;
    private int maxQuantity;

    public AddItemPopUp() {
        init(mainPanel, "Add new item", new Dimension(350, 400), Accessibility.NONE);

        setDefaultValues();
        SuggestionDropDownDecorator.decorate(searchItemTextField, new TextComponentSuggestionClient(this::getSuggestions));

        searchItemTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                searchItemTextField.getText();
                //Q: Behövs denna ens? Den gör ju typ inget...
            }


        });

        searchItemBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userInput = searchItemTextField.getText();

                if (orderedHospitalStoredItems == null) {
                    System.out.println("Nothing was entered in search bar");
                    return;
                }

                System.out.println("searched word is: " + searchItemTextField.getText());
                if (orderedHospitalStoredItems.containsKey(userInput)) {

                    selectedItem = findSearchedItem(userInput);
                    if (selectedItem == null) {
                        return;
                    }
                    quantity = orderedHospitalStoredItems.get(userInput);
                    maxQuantity = quantity;

                    quantityTxtField.setText(maxQuantity + "");
                    keepButtonsInRange(quantity);

                    System.out.println("Items name: " + selectedItem.getName() + " max amount: " + maxQuantity);
                }
                return;
            }
        });


        quantityTxtField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                try {
                    int userInp = Integer.parseInt(quantityTxtField.getText());

                    keepButtonsInRange(userInp);
                    if (isValidAmount(userInp)) {
                        quantityTxtField.setText(userInp + "");
                        System.out.println("Yay, within range " + userInp);
                    }

                } catch (NumberFormatException exception) {
                    System.out.println(exception.toString() + " was not an integer number! Please enter integer numerical input!");

                }

            }


        });


        decreaseQuantBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                keepButtonsInRange(--quantity);
            }
        });
        increaseQuantBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                keepButtonsInRange(++quantity);
            }
        });
        addItemBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (selectedItem != null) {

                }
            }
        });
    }


    private boolean isValidAmount(int inp) {
        return inp <= 0 && inp >= maxQuantity;
    }

    private void keepButtonsInRange(int quantity) {
        increaseQuantBtn.setEnabled(true);
        decreaseQuantBtn.setEnabled(true);
        if (quantity >= maxQuantity) {
            increaseQuantBtn.setEnabled(false);
        } else if (quantity <= 0) {
            decreaseQuantBtn.setEnabled(false);
        }
        quantityTxtField.setText(quantity + "");
    }


    private Item findSearchedItem(String itemName) {

        HashMap<Item, Integer> storedItems = (HashMap) Driver.getHospital().getHospitalsStoredItems();
        for (Map.Entry<Item, Integer> hospitalsItems : storedItems.entrySet()) {

            if (hospitalsItems.getKey().getName().equalsIgnoreCase(itemName)) {
                return hospitalsItems.getKey();
            }
        }
        return null;
    }


    private void setDefaultValues() {
        hospital = Driver.getHospital();

    }

    private void convertStoredItemMapToStringMap(HashMap<Item, Integer> itemsMap) {

        if (itemsMap == null || itemsMap.isEmpty()) {
            return;
        }

        orderedHospitalStoredItems = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        HashMap<Item, Integer> hospitalItem = itemsMap;

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


    private List<String> getSuggestions(String key) {

        if (key == null || key.isEmpty()) {
            System.out.println(key + " returned null or was empty");
        }

        convertStoredItemMapToStringMap((HashMap) Driver.getHospital().getHospitalsStoredItems());

        if (orderedHospitalStoredItems != null && orderedHospitalStoredItems.containsKey(key)) {
            System.out.println(key + " existed in hospitalStorage och sjukhuset har " + orderedHospitalStoredItems.get(key).toString());
        } else {
            System.out.println(key + " DOES NOT exist in hospitalStorage");
        }
        List<String> itemStorageNames = new ArrayList<String>(orderedHospitalStoredItems.keySet());

        return itemStorageNames.stream().limit(10).collect(Collectors.toList());

    }

}
