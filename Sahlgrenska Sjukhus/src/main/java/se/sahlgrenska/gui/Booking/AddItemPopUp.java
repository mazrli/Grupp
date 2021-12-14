package se.sahlgrenska.gui.Booking;

import se.sahlgrenska.gui.util.HelperGUI;
import se.sahlgrenska.main.Driver;
import se.sahlgrenska.sjukhus.Hospital;
import se.sahlgrenska.sjukhus.person.employee.Accessibility;
import se.sahlgrenska.sjukhus.item.Item;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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


    public AddItemPopUp() {
        init(mainPanel, "Add new item", new Dimension(350, 400), Accessibility.NONE);
        hospital = Driver.getHospital();


        searchItemTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
            }
        });
    }


}
