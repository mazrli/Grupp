package se.sahlgrenska.gui.util;

import se.sahlgrenska.gui.Menu.MenuGUI;
import se.sahlgrenska.main.Driver;
import se.sahlgrenska.sjukhus.person.employee.Accessibility;

import javax.swing.*;
import java.awt.*;

public abstract class HelperGUI extends JFrame {

    //menyn accessibility level;
    private Accessibility accessibility;

    //referens till main panelen
    private JPanel mainPanel;

    //kollar om init har använts.
    private boolean init = false;

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

        this.mainPanel = mainPanel;

        //sätt mainPanel
        setContentPane(mainPanel);

        //sätt titeln
        setTitle(title);

        //sätt preferred size
        setPreferredSize(dimension);

        //stäng fönstret när man trycket X
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //ej resizable till default (ni kan ändra annars)
        setResizable(false);

        //Tilldelar ikon åt fönstret, sitter uppe i vänstra hörnet.
        setIconImage(UtilGUI.iconImage);

        //packa ihop allt
        pack();

        //lägg den i center av skärmen.
        setLocationRelativeTo(null);

        init = true;
    }

    //toggle (deprecated, använd Driver.getMainMenu() istället)
    @Deprecated
    public static void toggleMainMenu() {
        Driver.getMainMenu().setVisible(!Driver.getMainMenu().isVisible());
    }


    //getter (deprecated, använd Driver.getMainMenu() istället)
    @Deprecated
    public static MenuGUI getMainMenu() {
        return Driver.getMainMenu();
    }

    public Accessibility getAccessibility() {
        return accessibility;
    }

    public boolean isInit() {
        return init;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
