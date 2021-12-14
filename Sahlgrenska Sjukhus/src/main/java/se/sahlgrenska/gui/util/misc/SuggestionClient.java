package se.sahlgrenska.gui.util.misc;

import javax.swing.*;
import java.awt.*;

public interface SuggestionClient<C extends JComponent> {

    Point getPopupLocation(C invoker);

    void setSelectedText(C invoker, String selectedValue);

    java.util.List<String> getSuggestions(C invoker);

}