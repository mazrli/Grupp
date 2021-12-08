package se.sahlgrenska.main;

import java.awt.*;
import java.time.format.DateTimeFormatter;

public class Util {

    public static Font titleFont = new Font("JetBrains Mono", Font.BOLD, 28);
    public static Font biggerFont = new Font("JetBrains Mono", Font.PLAIN, 18);
    public static Font normalFont = new Font("JetBrains Mono", Font.PLAIN, 12);

    public static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
}
