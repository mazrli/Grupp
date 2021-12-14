package se.sahlgrenska.gui.util.misc;

import se.sahlgrenska.gui.util.UtilGUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SuggestionExampleMain {
    public static void main(String[] args) {
        JTextField textField = new JTextField(10);
        //SuggestionDropDownDecorator.decorate(textField, new TextComponentSuggestionClient(SuggestionExampleMain::getSuggestions));
    }

    private static List<String> words = new ArrayList<String>(List.of(new String[]{"123", "1234", "123456"}));//UtilGUI.getWords(2, 400).stream().map(String::toLowerCase).collect(Collectors.toList());

}