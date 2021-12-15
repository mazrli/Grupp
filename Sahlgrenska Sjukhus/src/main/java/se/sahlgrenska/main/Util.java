package se.sahlgrenska.main;

import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

public class Util {

    public static Font titleFont = new Font("JetBrains Mono", Font.BOLD, 28);
    public static Font biggerFont = new Font("JetBrains Mono", Font.PLAIN, 18);
    public static Font normalFont = new Font("JetBrains Mono", Font.PLAIN, 12);

    public static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("HH:mm");



    /*
        använd denna när ni ska printa saker.
        lättare att hitta och ta bort alla prints sen :)
     */
    public static void print(String str) {
        System.out.println("=====> " + str);
    }

    public static String getOS() {
        return System.getProperty("os.name").toLowerCase();
    }


    /*
        liten sök motor som tar en Set<Object> och ett sök ord.

        returnar allt i listan som börjar på sökordet
     */
    public static Set<Object> getSearchResults(Set<? extends  Object> list, String keyWord) {
        Set<Object> results = new HashSet<>();

        keyWord = keyWord.toUpperCase();

        for(Object item : list) {
            //lägg till den i results om den börjar på samma ord som sökordet
            if(item.toString().toUpperCase(Locale.ROOT).startsWith(keyWord))
                results.add(item);
        }

        return results;
    }
}
