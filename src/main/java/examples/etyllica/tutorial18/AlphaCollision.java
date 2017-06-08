package examples.etyllica.tutorial18;

import br.com.etyllica.commons.context.Application;
import br.com.etyllica.commons.event.PointerEvent;
import br.com.etyllica.commons.graphics.Graphics;
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
		
		layer = new BufferedLayer("machine.png");
		layer.setCoordinates(w/2-layer.getW()/2, h/2-layer.getH()/2);
		
		loading = 100;
	}

	@Override
	public void draw(Graphics g) {

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
	public void updateMouse(PointerEvent event) {
		mx = event.getX();
		my = event.getY();
	}
	
	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		load();
	}

}
