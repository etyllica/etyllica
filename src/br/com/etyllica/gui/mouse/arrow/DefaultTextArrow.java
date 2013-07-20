package br.com.etyllica.gui.mouse.arrow;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class DefaultTextArrow extends DefaultArrow{

	private static final long serialVersionUID = 1L;

	public DefaultTextArrow(int size) {
		super(size);
	}
	
	@Override
	protected void defPoints(){
		
		addPoint((int)(size*0.4),0);
		addPoint(0, 0);
		//addPoint((int)(size*0.4),0);
		//addPoint((int)(size*0.4)+2,0);
		addPoint((int)(size*0.8),0);
		
		addPoint((int)(size*0.4),0);
		
		//addPoint((int)(size*0.4)+1,1);
		addPoint((int)(size*0.4),(int)(size*0.9));
		
		addPoint(0,(int)(size*0.9));
		addPoint((int)(size*0.8),(int)(size*0.9));
		addPoint((int)(size*0.4),(int)(size*0.9));
		
		//addPoint((int)(size*0.26),(int)(size*0.55));
		//addPoint((int)(size*0.49),(int)(size*0.57));
		
	}
	
}
