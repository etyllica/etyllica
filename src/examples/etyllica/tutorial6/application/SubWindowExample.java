package examples.etyllica.tutorial6.application;

import java.awt.Color;

import br.com.etyllica.core.application.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyboardEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.event.Tecla;
import br.com.etyllica.core.video.Grafico;
import br.com.etyllica.gui.Window;
import examples.etyllica.tutorial2.application.SimpleGuiExample;

public class SubWindowExample extends Application{

	public SubWindowExample(int w, int h) {
		super(w, h);
	}

	@Override
	public void load() {
		loading = 100;
	}

	@Override
	public GUIEvent updateKeyboard(KeyboardEvent event){
		
		if(event.getPressed(Tecla.TSK_1)){
			
			Window subWindow = new Window(200, 20, 160, 400);
			subWindow.setApplication(new SimpleGuiExample(subWindow.getW(), subWindow.getH()));
			
			addWindow(subWindow);
		}
		
		return GUIEvent.NONE;
		
	}

	@Override
	public GUIEvent updateMouse(PointerEvent event) {
				
		// TODO Auto-generated method stub
		return GUIEvent	.NONE;
	}
	
	@Override
	public void draw(Grafico g) {
		
		//Drawing background
		
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(x,y,w,h);
	}
	
}
