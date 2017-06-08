package examples.etyllica.gui.subwindow;

import java.awt.Color;

import br.com.etyllica.awt.AWTWindow;
import br.com.etyllica.commons.context.Application;
import br.com.etyllica.commons.event.KeyEvent;
import br.com.etyllica.commons.graphics.Graphics;
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
			
			AWTWindow subWindow = new AWTWindow(200, 20, 160, 400);
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
