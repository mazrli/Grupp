package se.sahlgrenska.gui.util.misc;

import javax.swing.text.JTextComponent;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Matches entire text instead of separate words
 */
public class TextComponentSuggestionClient implements SuggestionClient<JTextComponent> {

    private Function<String, List<String>> suggestionProvider;

    public TextComponentSuggestionClient(Function<String, List<String>> suggestionProvider) {
        this.suggestionProvider = suggestionProvider;
    }

    @Override
    public Point getPopupLocation(JTextComponent invoker) {
        return new Point(0, invoker.getPreferredSize().height);
    }

    @Override
    public void setSelectedText(JTextComponent invoker, String selectedValue) {
        invoker.setText(selectedValue);
    }

    @Override
    public List<String> getSuggestions(JTextComponent invoker) {
        return suggestionProvider.apply(invoker.getText().trim());
    }
}