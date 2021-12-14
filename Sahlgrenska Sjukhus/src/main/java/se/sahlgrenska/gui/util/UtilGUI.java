package se.sahlgrenska.gui.util;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class UtilGUI {

    public static final Font titleFont = new Font("JetBrains Mono", Font.BOLD, 28);
    public static final Font biggerFont = new Font("JetBrains Mono", Font.PLAIN, 18);
    public static final Font normalFont = new Font("JetBrains Mono", Font.PLAIN, 12);

    public static final String windowsLook = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
    public static final String appleLook = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
    public static final Image iconImage = Toolkit.getDefaultToolkit().getImage(UtilGUI.class.getClassLoader().getResource("icon.png"));



    public static void changeJTableHeaderColour(JTable table, Color color){
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setReorderingAllowed(false);
        tableHeader.setOpaque(false);
        tableHeader.setBackground(color);
    }


    public static void error(String message) {
        error(message, "Warning");
    }

    public static void error(String message, String title) {
        error(message, title, JOptionPane.ERROR_MESSAGE);
    }

    public static void error(String message, String title, int type) {
        java.awt.Toolkit.getDefaultToolkit().beep();
        JOptionPane.showMessageDialog(null, message, title, type);
    }

    /*
        Bestämde mig för att göra en metod för detta då det blev så populärt
        (den visar/gömmer lösenordet)
     */
    public static void toggleVisibility(JPasswordField pwField) {
        pwField.setEchoChar( pwField.getEchoChar() == '●' ? '\0' : '●');
    }

    public static String getPasswordString(JPasswordField pwField) {
        String out = "";
        for(char c : pwField.getPassword())
            out += c;
        return out;
    }



}
