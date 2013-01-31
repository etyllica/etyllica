package examples.tutorial1.application;

import java.awt.Color;

import br.com.etyllica.core.application.Application;
import br.com.etyllica.core.event.Event;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyboardEvent;
import br.com.etyllica.core.event.Tecla;
import br.com.etyllica.core.video.Grafico;

public class HelloWorld extends Application{

	private int alturaTexto = 100;
	
	@Override
	public void load() {
		carregando = 100;
	}

	@Override
	public void draw(Grafico g) {

		g.setColor(new Color(0xDD,0xDD,0xDD));
		g.getGraphics().fillRect(0, 0, w, h);
		
		g.setColor(Color.BLACK);
		g.escreveX(alturaTexto, "Hello World!");
		
		g.setColor(Color.BLACK);
		g.escreveX(300, "Press <Enter> to change the application!");
	}

	@Override
	public GUIEvent updateKeyboard(KeyboardEvent event) {
				
		if(event.getPressed(Tecla.TSK_SETA_CIMA)){
			alturaTexto--;						
		}
		else if(event.getPressed(Tecla.TSK_SETA_BAIXO)){
			alturaTexto+=10;
		}
		
		if(event.getPressed(Tecla.TSK_ENTER)){
			returnApplication = new TchauMundo();
		}

		return GUIEvent.NONE;
	}
	
	@Override
	public GUIEvent updateMouse(Event event) {
		// TODO Auto-generated method stub
		return GUIEvent	.NONE;
	}
	
}
