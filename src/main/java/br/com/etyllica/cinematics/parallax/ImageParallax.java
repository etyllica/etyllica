package br.com.etyllica.cinematics.parallax;

import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.layer.ImageLayer;

public class ImageParallax extends Parallax {

	private ImageLayer layer;
	
	public ImageParallax(String imagePath) {
		super();
		
		layer = new ImageLayer(imagePath);
	}

	@Override
	public void draw(Graphic g) {
		
		int mod = (offset/proximity)%layer.getW();
		
		if(mod == 0) {
			layer.simpleDraw(g, 0, 0);
		} else {
			layer.simpleDraw(g, -mod, 0);
			layer.simpleDraw(g, -mod+layer.getW(), 0);
		}		
	}
	
}
