package br.com.etyllica.gui;

import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.MouseButton;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.gui.base.BaseButton;
import br.com.etyllica.gui.listener.ValueListener;
import br.com.etyllica.theme.Theme;
import br.com.etyllica.theme.ThemeManager;

/**
 * @author yuripourre
 * @license LGPLv3
 */

public class Slider extends View {

    protected float minValue = 0;

    protected float maxValue = 255;

    protected float value = 0;

    protected BaseButton button;

    private boolean activated = false;

    public Slider(int x, int y, int w, int h) {

        super(x, y, w, h);

        button = new BaseButton(x, y, h / 4, h);
    }

    @Override
    public GUIEvent updateMouse(PointerEvent event) {

        if (mouseOver) {
            if (event.isButtonDown(MouseButton.MOUSE_BUTTON_LEFT)) {
                activated = true;
            }
        }

        if (activated) {
            if (event.isButtonDown(MouseButton.MOUSE_BUTTON_LEFT)) {
                updateValue(event);
                return GUIEvent.COMPONENT_CHANGED;
            } else if (event.isButtonUp(MouseButton.MOUSE_BUTTON_LEFT)) {
                activated = false;
            }
        }

        return GUIEvent.NONE;
    }

    public void updateValue(PointerEvent event) {
        float interval = maxValue - minValue;
        int mx = event.getX() - x;
        value = (mx * interval) / w;

        if (value < minValue) {
            value = minValue;
            button.setX(getX() - button.getW() / 2);
        } else if (value > maxValue) {
            value = maxValue;
            button.setX(getX() + getW() - button.getW() / 2);
        } else {
            button.setX(event.getX() - button.getW() / 2);
        }
    }

    @Override
    public void update(GUIEvent event) {
        // TODO Auto-generated method stub

    }

    @Override
    public void draw(Graphics g) {

        //Draw Slide
        Theme theme = getTheme();

        g.setColor(theme.getBarColor());

        int sh = h / 5;
        g.fillRect(x, y + h / 2 - sh / 2, w, sh);

        //Draw Button
        button.draw(g);

    }

    public float getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public float getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {

        this.value = value;

        float interval = maxValue - minValue;

        float bx = x + ((value * w) / interval);

        button.setX((int)bx - button.getW() / 2);
    }

    @Override
    public GUIEvent updateKeyboard(KeyEvent event) {
        if (onFocus) {
            if (event.isKeyDown(KeyEvent.VK_RIGHT)) {
                setValue(value + 1);
            }
            if (event.isKeyDown(KeyEvent.VK_LEFT)) {
                setValue(value + 1);
            }
        }

        return GUIEvent.NONE;
    }

}

