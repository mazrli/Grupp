package se.sahlgrenska.gui.util;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class UtilGUI {

    public static Font titleFont = new Font("JetBrains Mono", Font.BOLD, 28);
    public static Font biggerFont = new Font("JetBrains Mono", Font.PLAIN, 18);
    public static Font normalFont = new Font("JetBrains Mono", Font.PLAIN, 12);




    public static void changeJTableHeaderColour(JTable table, Color color){
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setReorderingAllowed(false);
        tableHeader.setOpaque(false);
        tableHeader.setBackground(color);
    }










}
