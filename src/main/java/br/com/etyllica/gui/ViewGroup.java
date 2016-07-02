package br.com.etyllica.gui;


/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public abstract class ViewGroup extends View {
	
	protected Orientation orientation = Orientation.VERTICAL;
	
	public ViewGroup(int x, int y, int w, int h) {
		super(x,y,w,h);
	}
	
	@Override
	public void add(View component) {
		super.add(component);
		
		final int vx = x+style.padding.left;
		final int vw = w-style.padding.right;
		final int vy = y+style.padding.top;
		final int vh = h-style.padding.bottom;
		
		final int rowSize = h/views.size();
		final int colSize = w/views.size();
		
		int i = 0;
		for(View view: views) {
			//Vertical Panel
			if (orientation == Orientation.VERTICAL) {
				view.setBounds(vx, vy+rowSize*i, vw, rowSize-style.padding.bottom);	
			} else {
				view.setBounds(vx+colSize*i, vy, colSize-style.padding.right, vh);
			}
			
			i++;
		}
	}
}
