package br.com.etyllica.ui.selection;

import br.com.etyllica.awt.stroke.DashedStroke;
import br.com.etyllica.core.input.mouse.MouseStateChanger;
import br.com.etyllica.commons.layer.Layer;

import java.awt.*;

public class BaseResizer<T extends Layer> extends Resizer<T> {

    private final DashedStroke dash = new DashedStroke();
    private final BasicStroke resetStroke = new BasicStroke(1);

    public BaseResizer(MouseStateChanger mouseStateChanger) {
        super(mouseStateChanger);
    }

    public void draw(br.com.etyllica.core.graphics.Graphics g) {
        drawOverlay(g);

        if (!isSelected())
            return;

        g.setColor(java.awt.Color.BLACK);
        g.setStroke(dash);
        drawScaledRect(g, selected);

        if (!moveOnly) {
            for (int b = 0; b < 8; b++) {
                points[b].draw(g, offsetX, offsetY);
            }
        }

        g.setStroke(resetStroke);
    }

    public void drawOverlay(br.com.etyllica.core.graphics.Graphics g) {
        if (!overlay.isVisible()) {
            return;
        }

        g.setColor(br.com.etyllica.commons.graphics.Color.BLACK);
        g.setAlpha(60);
        fillScaledRect(g, overlay);
        g.resetOpacity();
    }

    private void drawScaledRect(br.com.etyllica.core.graphics.Graphics g, Layer layer) {
        int sw = (int) (layer.getW() * layer.getScaleX());
        int sh = (int) (layer.getH() * layer.getScaleY());

        int oX = (int) (layer.getW() * (1 - layer.getScaleX())) / 2;
        int oY = (int) (layer.getH() * (1 - layer.getScaleY())) / 2;

        g.drawRect(layer.getX() + oX + offsetX, layer.getY() + oY + offsetY, sw, sh);
    }

    private void fillScaledRect(br.com.etyllica.core.graphics.Graphics g, Layer layer) {
        int sw = (int) (layer.getW() * layer.getScaleX());
        int sh = (int) (layer.getH() * layer.getScaleY());

        int oX = (int) (layer.getW() * (1 - layer.getScaleX())) / 2;
        int oY = (int) (layer.getH() * (1 - layer.getScaleY())) / 2;

        g.fillRect(layer.getX() + oX + offsetX, layer.getY() + oY + offsetY, sw, sh);
    }

}
