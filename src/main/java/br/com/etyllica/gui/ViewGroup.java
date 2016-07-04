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
		final int vw = w-style.padding.right-style.padding.left;
		final int vy = y+style.padding.top;
		final int vh = h-style.padding.bottom-style.padding.top;
				
		int lastCursor = 0;
		int leftSpace = 0;
		
		if (orientation == Orientation.VERTICAL) {
			leftSpace = vh;
		} else {
			leftSpace = vw;
		}
		
		int i = 0;
		for(View view: views) {
			
			int size = 0;
			//Vertical Panel
			if (orientation == Orientation.VERTICAL) {
				if (view.getH() > 0 && view.getH() <= leftSpace) {
					size = view.getH();
				} else {
					size = leftSpace/(views.size()-i);
				}
				
				view.setBounds(vx, vy+lastCursor, vw, size);
								
			} else {
				if (view.getW() > 0 && view.getW() <= leftSpace) {
					size = view.getW();
				} else {
					size = leftSpace/(views.size()-i);
				}
				view.setBounds(vx+lastCursor, vy, size, vh);
			}
			
			leftSpace -= size+view.style.margin.top+view.style.margin.bottom;
			lastCursor += size+view.style.margin.top+view.style.margin.bottom;
			
			i++;
		}
	}

	public Orientation getOrientation() {
		return orientation;
	}

	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}
}
