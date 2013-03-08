package br.com.etyllica.gui.panel;

import java.awt.Color;

import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyboardEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.video.Grafico;
import br.com.etyllica.gui.GUIComponent;

public class ScrollBackground extends GUIComponent{

	public ScrollBackground(int x, int y, int w, int h){
		super(x,y,w,h);
	}
	
	@Override
	public void update(GUIEvent event) {
		// TODO Auto-generated method stub
	}

	@Override
	public void draw(Grafico g) {
		g.setColor(Color.WHITE);
		g.fillRect(x,y,w,h);
	}

	@Override
	public GUIEvent updateMouse(PointerEvent event) {
		// TODO Auto-generated method stub
		return GUIEvent.NONE;
	}

	@Override
	public GUIEvent updateKeyboard(KeyboardEvent event) {
		// TODO Auto-generated method stub
		return GUIEvent.NONE;
	}

}
