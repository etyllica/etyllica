package br.com.etyllica.gui.label;

import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyboardEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.video.Grafico;
import br.com.etyllica.gui.Label;

public abstract class Icon extends Label{

	public Icon(int x, int y){
		super(x,y);
	}

	@Override
	public void update(GUIEvent event) {
		// TODO Auto-generated method stub	
	}
	
	@Override
	public GUIEvent updateMouse(PointerEvent event){
		// TODO Auto-generated method stub
		return GUIEvent.NONE;
	}

	@Override
	public GUIEvent updateKeyboard(KeyboardEvent event) {
		// TODO Auto-generated method stub
		return GUIEvent.NONE;
	}
	
}
