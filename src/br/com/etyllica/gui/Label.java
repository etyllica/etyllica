package br.com.etyllica.gui;


/**
 * 
 * @author mscythe
 * @license LGPLv3
 *
 */

public abstract class Label extends GUIComponent{

	protected boolean center = false;
	protected String alt = "";
	protected String text = "";
	
	public Label(int x, int y) {
		super(x, y);
	}
	
	public Label(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	public Label(int x, int y, String text) {
		super(x, y);
		this.text = text;
	}
	
	public boolean isCenter() {
		return center;
	}

	public void setCenter(boolean center) {
		this.center = center;
	}

	public String getAlt() {
		return alt;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
}
