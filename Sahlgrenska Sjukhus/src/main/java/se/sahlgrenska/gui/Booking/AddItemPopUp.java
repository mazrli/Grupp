package se.sahlgrenska.gui.Booking;

import se.sahlgrenska.gui.util.HelperGUI;
import se.sahlgrenska.main.Driver;
import se.sahlgrenska.sjukhus.Hospital;
import se.sahlgrenska.sjukhus.item.Item;
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
    private String userSearchedItem = "";
    private TreeMap<String,Integer> orderedHospitalStoredItems;

    public AddItemPopUp() {
        init(mainPanel, "Add new item", new Dimension(350, 400), Accessibility.NONE);

        setDefaultValues();


        searchItemTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);

                userSearchedItem = searchItemTextField.getText().trim();

                //       SuggestionDropDownDecorator.decorate(searchItemTextField, new TextComponentSuggestionClient(this::getSuggestions));
            }
        });

        searchItemBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("searched word is: " + userSearchedItem);
                getSuggestions();
            }
        });
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
        System.out.println("TREEEEMAP");
        for (Map.Entry<String, Integer> ent : hospitalItemStorageOrg.entrySet()) {
            System.out.println(ent);
        }
    }

    private void  getSuggestions() { //Set<String>

        if (userSearchedItem == null || userSearchedItem.isEmpty()) {
            System.out.println(userSearchedItem + " returned null or was empty");
        }

        convertStoredItemMapToStringMap((HashMap) Driver.getHospital().getHospitalsStoredItems());

        if (orderedHospitalStoredItems != null && orderedHospitalStoredItems.containsKey(userSearchedItem)) {
            System.out.println(userSearchedItem + " existed in hospitalStorage och sjukhuset har " + orderedHospitalStoredItems.get(userSearchedItem).toString());
        } else {
            System.out.println(userSearchedItem + " DOES NOT exist in hospitalStorage");
        }
        Set<String> itemStorageNames = (TreeSet) orderedHospitalStoredItems.keySet();

      // return itemStorageNames.stream().limit(10);

    }

/*
    private void fillItemsStorageForHospital(){
        Item item1 = new Equipment("Defibrilator", "Starts hearts", 2500.5f, true);
        Item item2 = new Equipment("MRI", "Scans body", 5000.0f, true);
        Item item5 = new Equipment("Stethoscope", "Heartbeats", 5000.0f, true);
        Item item3 = new Medicine("Panodil", "Pain relief", 15.0f, LocalDate.now());
        Item item4 = new Medicine("Alvedon", "Pain relief", 12.5f, LocalDate.now());
        Item item6 = new Medicine("SARS-vaccine", "Covid", 105.2f, LocalDate.now());

        items.put(item1,25);
        items.put(item2,10);
        items.put(item3,15);
        items.put(item4,50);
        items.put(item5,30);
        items.put(item6,44);

    }*/

}
