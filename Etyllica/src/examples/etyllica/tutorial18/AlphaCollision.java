package examples.etyllica.tutorial18;

import br.com.etyllica.context.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.video.Graphic;
import br.com.etyllica.layer.BufferedLayer;

public class AlphaCollision extends Application{
	
	private BufferedLayer layer;
	
	public AlphaCollision(int w, int h) {
		super(w, h);
	}

	private int mx = 0;
	
	private int my = 0;
	
	@Override
	public void load() {
		
		layer = new BufferedLayer(w/2,h/2, "machine.png");
		
		loading = 100;
	}

	@Override
	public void draw(Graphic g) {

		layer.draw(g);
	}
		
	@Override
	public void update(long now){
		
		if(layer.colideAlphaPoint(mx, my)){
			
			layer.offsetRGB(80, -40, -50);
			
		}else{
			
			layer.resetImage();
			
		}
				
	}
	
	@Override
	public GUIEvent updateMouse(PointerEvent event) {
		
		mx = event.getX();
		
		my = event.getY();
				
		return null;
	}
	
	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {

		return GUIEvent.NONE;
	}

}
