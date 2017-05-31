package examples.etyllica.gui.subwindow;

import java.awt.Color;

import br.com.etyllica.core.context.Application;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.gui.Window;
import examples.ui.simple.UIExample.BackgroundColorChangerApplication;

public class SubWindowExample extends Application {

	public SubWindowExample(int w, int h) {
		super(w, h);
	}

	@Override
	public void load() {
		loading = 100;
	}

	@Override
	public void updateKeyboard(KeyEvent event) {
		
		if(event.isKeyDown(KeyEvent.VK_1)) {
			
			Window subWindow = new Window(200, 20, 160, 400);
			subWindow.setApplication(new BackgroundColorChangerApplication(subWindow.getW(), subWindow.getH()));
			
		}
	}
	
	@Override
	public void draw(Graphics g) {
		//Draw background	
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(x,y,w,h);
	}
	
}
