package br.com.etyllica.gui.panel;

import java.awt.Color;

import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.gui.View;

public class ScrollBackground extends View{

	public ScrollBackground(int x, int y, int w, int h){
		super(x,y,w,h);
	}
	
	@Override
	public void updateEvent(GUIEvent event) {
		// TODO Auto-generated method stub
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(x,y,w,h);
	}

	@Override
	public GUIEvent updateMouse(PointerEvent event) {
		// TODO Auto-generated method stub
		return GUIEvent.NONE;
	}

	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {
		// TODO Auto-generated method stub
		return GUIEvent.NONE;
	}

}
