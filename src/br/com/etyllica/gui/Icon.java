package br.com.etyllica.gui;

import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.video.Grafico;
import br.com.etyllica.layer.ImageLayer;

/**
 * 
 * @author mscythe
 * @license LGPLv3
 *
 */

public class Icon extends GUIComponent{

	ImageLayer camada; 
	
	public Icon(int x, int y, String path){
		super(x, y);
		camada = new ImageLayer(0,0,path);
	}
	
	@Override
	public GUIEvent updateMouse(PointerEvent event) {
		return GUIEvent.NONE;
	}

	@Override
	public void update(GUIEvent event) {
		// TODO Auto-generated method stub
	}

	@Override
	public void draw(Grafico g) {
		camada.setOffset(x, y);
		camada.draw(g);
		camada.setOffset(-x, -y);
	}

	
	
	
}
