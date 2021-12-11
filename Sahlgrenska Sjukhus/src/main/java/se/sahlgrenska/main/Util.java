package se.sahlgrenska.main;

import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Util {

    public static Font titleFont = new Font("JetBrains Mono", Font.BOLD, 28);
    public static Font biggerFont = new Font("JetBrains Mono", Font.PLAIN, 18);
    public static Font normalFont = new Font("JetBrains Mono", Font.PLAIN, 12);

    public static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");



    public static String getOS() {
        return System.getProperty("os.name").toLowerCase();
    }

    public static List<String> getSearchResults(List<String> list, String str) {
        List<String> results = new ArrayList<>();

        for(String item : list) {
            if(item.startsWith(str))
                results.add(item);
        }

        return results;
    }
}
