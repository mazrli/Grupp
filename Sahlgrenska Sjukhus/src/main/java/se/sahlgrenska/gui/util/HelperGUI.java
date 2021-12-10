package se.sahlgrenska.gui.util;

import se.sahlgrenska.gui.Menu.MenuGUI;
import se.sahlgrenska.main.Driver;
import se.sahlgrenska.sjukhus.person.employee.Accessibility;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;

public abstract class HelperGUI extends JFrame {

    //menyn accessibility level;
    private Accessibility accessibility;

    private String windowsLook = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
    private String appleLook = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";

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

        //icon
        try {
            setIconImage(ImageIO.read(Objects.requireNonNull(getClass().getResource("/icon.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //packa ihop allt
        pack();

        //lägg den i center av skärmen.
        setLocationRelativeTo(null);

        //look and feel
        if(false) { //tog bort detta då programmet crashar på mac datorer.
            try {
                UIManager.setLookAndFeel(windowsLook);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    //toggla main menyn
    @Deprecated
    public static void toggleMainMenu() {
        Driver.getMainMenu().setVisible(!Driver.getMainMenu().isVisible());
    }


    //getter
    @Deprecated
    public static MenuGUI getMainMenu() {
        return Driver.getMainMenu();
    }

    public Accessibility getAccessibility() {
        return accessibility;
    }
}
