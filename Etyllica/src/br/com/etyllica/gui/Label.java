package br.com.etyllica.gui;

import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;


/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public abstract class Label extends View{
	
	protected float bx;
	protected float by;
	protected float bw;
	protected float bh;
	
	public Label(float x, float y) {
		this(x, y, 0, 0);
	}
	
	public Label(float x, float y, float w) {
		this(x, y, w, 0);
	}
	
	public Label(float x, float y, float w, float h) {
		super(x, y, w, h);
		setContentBounds(x, y, w, h);
	}

	public void setContentBounds(float bx, float by, float bw, float bh){
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
	public GUIEvent updateKeyboard(KeyEvent event) {
		// TODO Auto-generated method stub
		return GUIEvent.NONE;
	}

	@Override
	public void update(GUIEvent event) {
		// TODO Auto-generated method stub
	}
	
}
