package br.com.etyllica.gui;

import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.video.Grafico;

/**
 * 
 * @author mscythe
 * @license LGPLv3
 *
 */

public class Wallpaper extends GUIComponent{

	public Wallpaper(){
		super(90,90);
	}
	
	@Override
	public GUIEvent updateMouse(PointerEvent event) {
		
		return GUIEvent.NONE;
	}

	@Override
	public void update(GUIEvent event) {
		
	}

	@Override
	public void draw(Grafico g) {
		
		//new Camada(g.getTheme().getWallpaper()).desenha(g);
	}

}
