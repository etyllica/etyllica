package examples.ui.kit.application;

import br.com.etyllica.core.context.Application;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.gui.Button;
import br.com.etyllica.gui.Slider;
import br.com.etyllica.gui.label.TextLabel;

public class UIKitApplication extends Application {

    public UIKitApplication(int w, int h) {
        super(w, h);
    }

    public void load() {
        Button button = new Button(20, 60, 90, 40);
        button.setLabel(new TextLabel("BUTTON"));

        Button disabledButton = new Button(120, 60, 90, 40);
        disabledButton.setLabel(new TextLabel("BUTTON"));
        disabledButton.setDisabled(true);

        Slider slider = new Slider(20, 110, 190, 30);

        addView(button);
        addView(disabledButton);
        addView(slider);
    }

    @Override
    public void draw(Graphics g) {

    }

}

