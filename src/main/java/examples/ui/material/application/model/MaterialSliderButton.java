package examples.ui.material.application.model;

import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.gui.base.BaseButton;

import java.awt.*;

public class MaterialSliderButton extends BaseButton {

    public MaterialSliderButton(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    public void draw(Graphics g) {
        if (!visible)
            return;

        Color color = getColor();
        g.setColor(color);

        g.fillOval(left(), top(), width(), height());

        drawLabel(g);
    }

}
