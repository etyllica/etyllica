package examples.etyllica.tutorial21;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import br.com.etyllica.context.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.core.input.mouse.MouseButton;
import br.com.etyllica.gui.selection.Resizer;
import br.com.etyllica.layer.GeometricLayer;

public class ResizerApplication extends Application {

	private Resizer resizer;

	private GeometricLayer overlay = null;

	private GeometricLayer blueComponent;

	private GeometricLayer redComponent;

	private GeometricLayer yellowComponent;
	
	private List<GeometricLayer> components;

	public ResizerApplication(int w, int h) {
		super(w, h);
	}

	@Override
	public void load() {

		resizer = new Resizer(this);

		components = new ArrayList<GeometricLayer>();
				
		blueComponent = new GeometricLayer(40, 100, 200, 80);
		components.add(blueComponent);

		redComponent = new GeometricLayer(40, 200, 200, 80);
		components.add(redComponent);

		yellowComponent = new GeometricLayer(300, 100, 200, 80);
		components.add(yellowComponent);
	}

	@Override
	public void draw(Graphic g) {

		g.setColor(Color.BLUE);
		g.drawRect(blueComponent);

		g.setColor(Color.RED);
		g.drawRect(redComponent);

		g.setColor(Color.YELLOW);
		g.fillRect(yellowComponent);
		g.setColor(Color.BLACK);
		g.drawRect(yellowComponent);

		drawOverlay(g);

		resizer.draw(g);
	}

	private void drawOverlay(Graphic g) {

		if(overlay == null)
			return;

		g.setColor(Color.BLACK);
		g.setAlpha(60);
		g.fillRect(overlay);
		g.resetOpacity();
	}

	@Override
	public GUIEvent updateMouse(PointerEvent event) {

		int mx = event.getX();
		int my = event.getY();

		if(!resizer.isSelected()) {
			
			for(GeometricLayer component: components) {
				if(component.colideRectPoint(mx, my)) {
					overlay = component;
					break;
				}	
			}			
		}
		
		if(event.isButtonDown(MouseButton.MOUSE_BUTTON_LEFT)) {

			if(!resizer.isSelected()) {

				for(GeometricLayer component: components) {
					if(component.colideRectPoint(mx, my)) {
						resizer.select(component);
						overlay = null;	
					}
				}
				
			} else if(!resizer.isDragged()) {

				resizer.deselect();
			}

		}

		resizer.handleEvent(event);

		return GUIEvent.NONE;
	}

	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {
		
		resizer.handleKeyEvent(event);
		
		return GUIEvent.NONE;
	}

}
