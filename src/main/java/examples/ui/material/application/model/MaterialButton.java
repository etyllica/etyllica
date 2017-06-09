package examples.ui.material.application.model;

import br.com.etyllica.awt.ColorHelper;
import br.com.etyllica.awt.SVGColor;
import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.ui.base.BaseButton;
import br.com.etyllica.ui.theme.Theme;

import java.awt.*;

public class MaterialButton extends BaseButton {

    int radius = 6;

    public MaterialButton(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    public void draw(Graphics g) {
        if (!visible) {
            return;
        }

        Theme theme = getTheme();

        Color baseColor;

        if (!disabled) {
            if (!mouseOver) {
                baseColor = theme.getBaseColor();
            } else {
                if (clicked) {
                    baseColor = theme.getActiveColor();
                } else {
                    baseColor = theme.getSelectionColor();
                }
            }
        } else {
            baseColor = theme.getButtonDisabledColor();
        }

        //Draw diffuse Button
        g.setColor(ColorHelper.darker(baseColor, 8));
        g.fillRoundRect(x, y + h - radius * 2, w, radius * 2 + 3, radius, radius);

        //Draw bottom effect
        g.setColor(baseColor);
        g.fillRoundRect(x, y, w, h, radius, radius);

        //Draw Label
        g.setColor(theme.getTextColor());
        drawLabel(g);
        //g.drawString(x, y, w, h, label);

        //Draw Shadow
        g.setAlpha(70);
        g.setColor(SVGColor.GRAY);
        g.drawLine(x + radius / 4 + 1, y + h + 3, x + w - radius / 2, y + h + 3);

        g.setAlpha(30);
        g.drawLine(x + radius / 4 + 1, y + h + 3 + 1, x + w - radius / 2 - 2, y + h + 3 + 1);
        g.drawLine(x - 1, y + radius / 4, x - 1, y + h - radius / 2);
        g.drawLine(x + w - 1, y + radius / 4, x + w - 1, y + h - radius / 2);

        g.resetAlpha();
    }

}
