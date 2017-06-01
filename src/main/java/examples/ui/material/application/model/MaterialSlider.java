package examples.ui.material.application.model;


import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.gui.base.BaseSlider;
import br.com.etyllica.gui.theme.Theme;

public class MaterialSlider extends BaseSlider {

    int barSize = 4;

    public MaterialSlider(int x, int y, int w, int h) {
        super(x, y, w, h);
        barSize = h / 8;
    }

    public void buildButton() {
        button = new MaterialSliderButton(x, y, w, h);
    }

    @Override
    public void draw(Graphics g) {
        if (!visible)
            return;

        Theme theme = getTheme();

        if (disabled) {
            g.setColor(theme.getButtonDisabledColor());
            int radius = h / 6;
            int spacing = 2;

            int half = w / 2 - radius - spacing;

            g.fillRect(x, y + h / 2 - barSize / 2, half, barSize);
            g.fillCircle(x + w / 2, y + h / 2, radius);
            g.fillRect(x + w / 2 + radius + spacing, y + h / 2 - barSize / 2, half, barSize);
            return;
        }

        //Draw Slide
        int middle = sliderPosition;

        g.setColor(theme.getBaseColor());
        g.fillRect(x, y + h / 2 - barSize / 2, middle, barSize);

        g.setColor(theme.getBarColor());
        g.fillRect(x + middle, y + h / 2 - barSize / 2, w - middle, barSize);

        //Draw Button
        if (value == minValue) {
            float size = normalRadius();
            if (activated) {
                size = h;
            }
            g.setLineWidth(barSize);
            g.drawCircle(x, y + h / 2, size / 2);
        } else {
            button.draw(g);
        }
    }

    public void updateValue(PointerEvent event) {
        super.updateValue(event);
        button.setX(button.getX() + h / 6);
    }

    protected void activate() {
        button.setBounds(button.getX(), y, h, h);
    }

    protected void deactivate() {
        float size = normalRadius();
        button.setBounds((int) (button.getX() + size / 4), (int) (y + size / 4), (int) size, (int) size);
    }

    private float normalRadius() {
        float scale = 1.5f;
        return h / scale;
    }

}
