package se.sahlgrenska.gui.util;

import se.sahlgrenska.gui.Menu.MenuGUI;

import javax.swing.*;
import java.awt.*;

public abstract class HelperGUI extends JFrame {

    private static final JFrame menu = new MenuGUI(); //statisk referens till main menyn

    protected void init(JPanel mainPanel, String title) {
        init(mainPanel, title, 500, 500); //500 * 500 är default size
    }

    protected void init(JPanel mainPanel, String title, int width, int height) {
        setContentPane(mainPanel); //sätt mainPanel
        setTitle(title); //sätt titeln
        setPreferredSize(new Dimension(width, height)); //sätt preferred size
        pack(); //packa ihop allt
        setResizable(false); //ej resizable till default (ni kan ändra annars)
        setLocationRelativeTo(null); //lägg den i center av skärmen.
    }

    public static void toggleMainMenu() { //toggla main menyn
        menu.setVisible(!menu.isVisible());
    }

}
