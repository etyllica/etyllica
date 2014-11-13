package br.com.etyllica.collision;

import java.util.ArrayList;
import java.util.List;

import br.com.etyllica.linear.Rectangle;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class HitBox {

	private List<Rectangle> areas = new ArrayList<Rectangle>();
	
	public HitBox() {
		super();
	}

	public List<Rectangle> getAreas() {
		return areas;
	}

	public void setAreas(List<Rectangle> area) {
		this.areas = area;
	}

}
