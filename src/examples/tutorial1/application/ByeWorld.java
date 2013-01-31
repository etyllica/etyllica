package examples.tutorial1.application;

import br.com.etyllica.core.application.Application;
import br.com.etyllica.core.event.Event;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyboardEvent;
import br.com.etyllica.core.event.Tecla;
import br.com.etyllica.core.video.Grafico;
import br.com.etyllica.util.SVGColor;

public class ByeWorld extends Application{
	
	@Override
	public void load() {
		
		carregando = 100;
	}

	@Override
	public void draw(Grafico g) {
		g.setColor(SVGColor.ORANGE_RED);
		g.getGraphics().fillRect(0, 0, w, h);
		
		g.setColor(SVGColor.FOREST_GREEN);
		g.escreveX(100, "Bye World!");
		
		g.escreveX(300, "Press <ESC> to go back.");
	}

	@Override
	public GUIEvent updateKeyboard( KeyboardEvent event) {
		
		if(event.getPressed(Tecla.TSK_ESC)){
			returnApplication = new HelloWorld();
		}
		
		return GUIEvent	.NONE;
	}

	@Override
	public GUIEvent updateMouse(Event event) {
		// TODO Auto-generated method stub
		return GUIEvent	.NONE;
	}

}
