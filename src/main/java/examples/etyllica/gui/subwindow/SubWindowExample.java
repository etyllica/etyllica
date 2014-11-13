package examples.etyllica.gui.subwindow;

import java.awt.Color;

import br.com.etyllica.context.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.gui.Window;
import examples.etyllica.tutorial09.application.SimpleGuiExample;

public class SubWindowExample extends Application {

	public SubWindowExample(int w, int h) {
		super(w, h);
	}

	@Override
	public void load() {
		loading = 100;
	}

	@Override
	public GUIEvent updateKeyboard(KeyEvent event){
		
		if(event.isKeyDown(KeyEvent.TSK_1)){
			
			Window subWindow = new Window(200, 20, 160, 400);
			subWindow.setApplication(new SimpleGuiExample(subWindow.getW(), subWindow.getH()));
			
		}
		
		return GUIEvent.NONE;
		
	}

	@Override
	public GUIEvent updateMouse(PointerEvent event) {
				
		// TODO Auto-generated method stub
		return GUIEvent	.NONE;
	}
	
	@Override
	public void draw(Graphic g) {
		
		//Drawing background
		
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(x,y,w,h);
	}
	
}
