package br.com.etyllica.core.control.mouse.arrow;

/**
 * 
 * @author mscythe
 * @license LGPLv3
 *
 */

public class WaitingArrow extends DefaultArrow{

	public WaitingArrow(int size) {
		super(size);
	}

	@Override
	public void defPoints(){
		addPoint(0, 0);
		addPoint(size, 0);
		addPoint((int)(size*0.65),(int)(size*0.5));

		addPoint(size,size);
		addPoint(0,size);

		addPoint((int)(size*0.45),(int)(size*0.5));
	}


}
