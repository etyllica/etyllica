package br.com.etyllica.cinematics.parallax;

import br.com.etyllica.commons.Drawable;

public abstract class Parallax implements Drawable {

	protected int offset = 0;
	
	protected int proximity = 1;

	public Parallax() {
		super();
	}
	
	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getProximity() {
		return proximity;
	}

	public void setProximity(int proximity) {
		this.proximity = proximity;
	}
		
}
