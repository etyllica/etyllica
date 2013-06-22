package br.com.etyllica.gui;

import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyboardEvent;
import br.com.etyllica.core.event.PointerEvent;


/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public abstract class Label extends GUIComponent{
	
	protected int bx;
	protected int by;
	protected int bw;
	protected int bh;
	
	public Label(int x, int y) {
		this(x, y, 0, 0);
	}
	
	public Label(int x, int y, int w) {
		this(x, y, w, 0);
	}
	
	public Label(int x, int y, int w, int h) {
		super(x, y, w, h);
		setContentBounds(x, y, w, h);
	}

	public void setContentBounds(int bx, int by, int bw, int bh){
		this.bx = bx;
		this.by = by;
		this.bw = bw;
		this.bh = bh;
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

	@Override
	public void update(GUIEvent event) {
		// TODO Auto-generated method stub
	}
	
}
