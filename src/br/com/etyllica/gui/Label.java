package br.com.etyllica.gui;


/**
 * 
 * @author mscythe
 * @license LGPLv3
 *
 */

public abstract class Label extends GUIComponent{

	protected String alt = "";
	
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

	public String getAlt() {
		return alt;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}

	public void setContentBounds(int bx, int by, int bw, int bh){
		this.bx = bx;
		this.by = by;
		this.bw = bw;
		this.bh = bh;
	}
	
	/*public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}*/
	
}
