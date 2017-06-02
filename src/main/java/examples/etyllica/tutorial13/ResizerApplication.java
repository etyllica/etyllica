package examples.etyllica.tutorial13;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import br.com.etyllica.core.context.Application;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.ui.selection.Resizer;
import br.com.etyllica.layer.Layer;

public class ResizerApplication extends Application {

	private Resizer<Layer> resizer;

	private Layer blueComponent;
	private Layer redComponent;
	private Layer yellowComponent;
	
	private List<Layer> components;

	public ResizerApplication(int w, int h) {
		super(w, h);
	}

	@Override
	public void load() {

		resizer = new Resizer<Layer>(this);
		components = new ArrayList<Layer>();
				
		blueComponent = new Layer(40, 100, 200, 80);
		components.add(blueComponent);

		redComponent = new Layer(40, 200, 200, 80);
		components.add(redComponent);

		yellowComponent = new Layer(300, 100, 200, 80);
		components.add(yellowComponent);
		
		resizer.setLayers(components);
	}

	@Override
	public void draw(Graphics g) {

		g.setColor(Color.BLUE);
		g.drawRect(blueComponent);

		g.setColor(Color.RED);
		g.drawRect(redComponent);

		g.setColor(Color.YELLOW);
		g.fillRect(yellowComponent);
		g.setColor(Color.BLACK);
		g.drawRect(yellowComponent);

		resizer.draw(g);
	}

	@Override
	public void updateMouse(PointerEvent event) {
		resizer.handleEvent(event);
	}

	@Override
	public void updateKeyboard(KeyEvent event) {
		resizer.handleKeyEvent(event);
	}

}
