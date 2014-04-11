package examples.etyllica.gui;

import br.com.etyllica.context.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.video.Graphic;
import br.com.etyllica.gui.Button;

public class ThemeApplication extends Application {

	private Button button;
	
	public ThemeApplication(int w, int h) {
		super(w, h);
	}
		
	@Override
	public void load() {
		
		button = new Button(80,80, 200, 20);
		
		this.add(button);
		
		loading = 100;
	}

	@Override
	public void draw(Graphic g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public GUIEvent updateMouse(PointerEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

}
