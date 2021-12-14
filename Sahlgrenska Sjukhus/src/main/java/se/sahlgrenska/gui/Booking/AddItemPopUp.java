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

    public AddItemPopUp() {
        init(mainPanel, "Add new item", new Dimension(350, 400), Accessibility.NONE);

        setDefaultValues();
        SuggestionDropDownDecorator.decorate(searchItemTextField, new TextComponentSuggestionClient(this::getSuggestions));

        searchItemTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);

                searchItemTextField.getText();

            }
        });

        searchItemBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("searched word is: " + searchItemTextField.getText());
                //getSuggestions();
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


    private List<String> getSuggestions(String key) { //Set<String>

        if (key == null || key.isEmpty()) {
            System.out.println(key + " returned null or was empty");
            // return null;
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
