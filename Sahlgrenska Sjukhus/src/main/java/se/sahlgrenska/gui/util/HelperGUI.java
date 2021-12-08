package se.sahlgrenska.gui.util;

import se.sahlgrenska.gui.Menu.MenuGUI;

import javax.swing.*;
import java.awt.*;

public abstract class HelperGUI extends JFrame {

    //statisk referens till main menyn
    private static final JFrame menu = new MenuGUI();

    protected void init(JPanel mainPanel, String title) {
        //500 * 500 är default size
        init(mainPanel, title, 500, 500);
    }

    protected void init(JPanel mainPanel, String title, int width, int height) {
        //sätt mainPanel
        setContentPane(mainPanel);

        //sätt titeln
        setTitle(title);

        //sätt preferred size
        setPreferredSize(new Dimension(width, height));
        
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

}
