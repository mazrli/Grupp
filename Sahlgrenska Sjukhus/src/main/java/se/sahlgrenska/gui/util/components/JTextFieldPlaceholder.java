package se.sahlgrenska.gui.util.components;

import javax.swing.*;
import javax.swing.text.Document;
import java.awt.*;

public class JTextFieldPlaceholder extends JTextField {

    private final String placeholder;

    public JTextFieldPlaceholder(String placeholderText) {
        this.placeholder = placeholderText;
    }

    @Override
    protected void paintComponent(final Graphics pG) {
        super.paintComponent(pG);

        if (placeholder == null || placeholder.length() == 0 || getText().length() > 0)// placeholder.length())
            return;

        int length = getText().length();
        String holder = placeholder;

        if(false)
            holder = getText().substring(0, length) + placeholder.substring(length);

        final Graphics2D g = (Graphics2D) pG;

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(getDisabledTextColor());
        g.drawString(holder, getInsets().left, pG.getFontMetrics().getMaxAscent() + getInsets().top);

    }

}