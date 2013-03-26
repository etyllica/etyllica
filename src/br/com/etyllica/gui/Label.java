package br.com.etyllica.gui;


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
		super(x, y);
	}
	
	public Label(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	public void setContentBounds(int bx, int by, int bw, int bh){
		this.bx = bx;
		this.by = by;
		this.bw = bw;
		this.bh = bh;
	}
	
}
