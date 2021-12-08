package se.sahlgrenska.gui.util;

import se.sahlgrenska.gui.Menu.MenuGUI;
import se.sahlgrenska.sjukhus.person.employee.Accessibility;

import javax.accessibility.AccessibleAction;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public abstract class HelperGUI extends JFrame {

    //menyn accessibility level;
    private Accessibility accessibility;

    //statisk referens till main menyn
    private static MenuGUI menu = null;

    //500 * 500 är default size
    private static final Dimension defaultSize = new Dimension(500, 500);


    protected void init(JPanel mainPanel, String title, Accessibility accessibility) {
        init(mainPanel, title, defaultSize, accessibility);
    }

    //denna är deprecated (använd den ej)
    @Deprecated
    protected void init(JPanel mainPanel, String title) {
        init(mainPanel, title, defaultSize);
    }

    //denna är deprecated (använd den ej)
    @Deprecated
    protected void init(JPanel mainPanel, String title, Dimension dimension) {
        init(mainPanel, title, dimension, Accessibility.NONE);
    }

    protected void init(JPanel mainPanel, String title, Dimension dimension, Accessibility accessibility) {
        //sätt accessibility för menyn;
        this.accessibility = accessibility;

        //lägg till som knapp i main menyn
        if(menu != null)
            menu.addButton(this);

        //sätt mainPanel
        setContentPane(mainPanel);

        //sätt titeln
        setTitle(title);

        //sätt preferred size
        setPreferredSize(dimension);

        //asdf
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //packa ihop allt
        pack();

        //ej resizable till default (ni kan ändra annars)
        setResizable(false);

        //lägg den i center av skärmen.
        setLocationRelativeTo(null);
    }

    //toggla main menyn
    public static void toggleMainMenu() {
        menu.setVisible(!menu.isVisible());
    }

    //getter
    public static JFrame getMainMenu() {
        return menu;
    }

    //skapa main menyn med accessibility level
    public static void setMainMenu(Accessibility accessibility) {
        menu = new MenuGUI(accessibility);
    }


    public Accessibility getAccessibility() {
        return accessibility;
    }
}
