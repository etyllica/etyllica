package br.com.etyllica.ui;

import br.com.etyllica.commons.event.GUIEvent;
import br.com.etyllica.commons.event.KeyEvent;
import br.com.etyllica.commons.event.PointerEvent;


/**
 * 
 * @author yuripourre
 *
 */

public abstract class Label extends View {
	
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
	public GUIEvent updateKeyboard(KeyEvent event) {
		// TODO Auto-generated method stub
		return GUIEvent.NONE;
	}

	@Override
	public void updateEvent(GUIEvent event) {
		// TODO Auto-generated method stub
	}
	
}
