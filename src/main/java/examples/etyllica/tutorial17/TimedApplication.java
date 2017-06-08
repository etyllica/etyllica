package examples.etyllica.tutorial17;

import br.com.etyllica.commons.context.Application;
import br.com.etyllica.commons.event.KeyEvent;
import br.com.etyllica.commons.graphics.Graphics;
import br.com.etyllica.layer.ImageLayer;

public class TimedApplication extends Application {
	
	private ImageLayer layer;

	private boolean press = false;
	
	public TimedApplication(int w, int h) {
		super(w, h);
	}

	@Override
	public void load() {
		
		layer = new ImageLayer(50,450, "particle.png");
		
		loading = 100;
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		layer.draw(g);
	}
	
	@Override
	public void update(long now){
		
		if(press){
			
			/*if(now-pressTime>=200){
				layer.setOffsetX(10);
			}else{
				press = false;
			}*/
			
			layer.offsetX(10);
			
		}
	}
	
	@Override
	public void updateKeyboard(KeyEvent event) {
		
		if(event.isKeyDown(KeyEvent.VK_RIGHT)) {
			press = true;
		}
		
		if(event.isKeyUp(KeyEvent.VK_RIGHT)) {
			press = false;
		}
	}

}
