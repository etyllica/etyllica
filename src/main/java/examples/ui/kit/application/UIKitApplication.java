package examples.ui.kit.application;

import br.com.etyllica.commons.context.Application;
import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.ui.UI;
import br.com.etyllica.ui.Button;
import br.com.etyllica.ui.Slider;
import br.com.etyllica.ui.label.TextLabel;

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

        UI.add(button);
        UI.add(disabledButton);
        UI.add(slider);
    }

    @Override
    public void draw(Graphics g) {

    }

}

