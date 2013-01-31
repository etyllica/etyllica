package br.com.etyllica.layer;

import java.util.ArrayList;
import java.util.List;

import br.com.etyllica.linear.Rectangle;

/**
 * 
 * @author mscythe
 * @license LGPLv3
 *
 */

public class ColisionArea {

	List<Rectangle> area;
	
	public ColisionArea (){
		area = new ArrayList<Rectangle>();
	}

	public List<Rectangle> getArea() {
		return area;
	}

	public void setArea(List<Rectangle> area) {
		this.area = area;
	}
		
}
