package examples.ui.simple.UIExample;

import br.com.etyllica.commons.context.Application;
import br.com.etyllica.commons.event.Action;
import br.com.etyllica.commons.event.GUIEvent;
import br.com.etyllica.commons.graphics.Color;
import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.ui.UI;
import br.com.etyllica.ui.Button;
import br.com.etyllica.ui.label.TextLabel;

public class BackgroundColorChangerApplication extends Application {

    public BackgroundColorChangerApplication(int w, int h) {
        super(w, h);
    }

    /**
     * Background Color
     */
    private Color color = Color.WHITE;

    @Override
    public void load() {

        Button buttonWhite = new Button(20, 30, 120, 40);
        buttonWhite.setLabel(new TextLabel("WHITE!"));
        buttonWhite.addAction(GUIEvent.MOUSE_LEFT_BUTTON_UP, new Action(this, "changeColor", Color.GHOST_WHITE));
        UI.add(buttonWhite);

        Button buttonBlue = new Button(20, 80, 120, 40);
        buttonBlue.setLabel(new TextLabel("BLUE!"));
        buttonBlue.addAction(GUIEvent.MOUSE_LEFT_BUTTON_UP, new Action(this, "changeColor", Color.ROYAL_BLUE));
        UI.add(buttonBlue);

        Button buttonSeaGreen = new Button(20, 130, 120, 40);
        buttonSeaGreen.setLabel(new TextLabel("SEA GREEN!"));
        buttonSeaGreen.addAction(GUIEvent.MOUSE_LEFT_BUTTON_UP, new Action(this, "changeColor", Color.SEA_GREEN));
        UI.add(buttonSeaGreen);

        Button buttonOrchid = new Button(20, 180, 120, 40);
        buttonOrchid.setLabel(new TextLabel("ORCHID!"));
        buttonOrchid.addAction(GUIEvent.MOUSE_LEFT_BUTTON_UP, new Action(this, "changeColor", Color.ORCHID));
        UI.add(buttonOrchid);

        Button buttonOrange = new Button(20, 230, 120, 40);
        buttonOrange.setLabel(new TextLabel("ORANGE!"));
        buttonOrange.addAction(GUIEvent.MOUSE_LEFT_BUTTON_UP, new Action(this, "changeColor", Color.ORANGE));
        UI.add(buttonOrange);

        Button buttonCrimson = new Button(20, 280, 120, 40);
        buttonCrimson.setLabel(new TextLabel("CRIMSON!"));
        buttonCrimson.addAction(GUIEvent.MOUSE_LEFT_BUTTON_UP, new Action(this, "changeColor", Color.CRIMSON));
        UI.add(buttonCrimson);

        loading = 100;
    }

    public void changeColor(Color color) {
        this.color = color;
    }

    @Override
    public void draw(Graphics g) {

        g.setColor(color);
        g.fillRect(x, y, w, h);

    }

}
