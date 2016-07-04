package br.com.etyllica.gui;

import java.util.HashMap;
import java.util.Map;


/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public abstract class ViewGroup extends View {
	
	private Map<Long, Float> weights = new HashMap<Long, Float>();
	protected Orientation orientation = Orientation.VERTICAL;
	
	public ViewGroup(int x, int y, int w, int h) {
		super(x,y,w,h);
	}
	
	@Override
	public void add(View component) {
		this.add(component, 1);
	}
	
	public void add(View component, float weight) {
		super.add(component);
		weights.put(component.getId(), weight);
		
		resize();
	}
	
	private void resize() {
		final int vx = x+style.padding.left;
		final int vw = w-style.padding.right-style.padding.left;
		final int vy = y+style.padding.top;
		final int vh = h-style.padding.bottom-style.padding.top;
				
		int lastCursor = 0;
		int totalSpace = 0;
		
		if (orientation == Orientation.VERTICAL) {
			totalSpace = vh;
		} else {
			totalSpace = vw;
		}
		
		for(View view: views) {

			int size = (int)viewSize(totalSpace, view);
			
			//Vertical Panel
			if (orientation == Orientation.VERTICAL) {
				view.setBounds(vx, vy+lastCursor, vw, size);
				
				//leftSpace -= size+verticalMargin(view);
				lastCursor += size+verticalMargin(view);
								
			} else {
				view.setBounds(vx+lastCursor, vy, size, vh);
				
				//leftSpace -= size+horizontalMargin(view);
				lastCursor += size+horizontalMargin(view);
			}
		}
	}
	
	public void setBounds(int x, int y, int w, int h) {
		super.setBounds(x, y, w, h);
		resize();
	}
	
	private float viewSize(int total, View view) {
		
		float sizeUnit = total/weightSum();
		float size = weights.get(view.getId())*sizeUnit;
		
		return size;
	}
	
	private int horizontalMargin(View view) {
		return view.style.margin.right+view.style.margin.left;
	}
	
	private int verticalMargin(View view) {
		return view.style.margin.top+view.style.margin.bottom;
	}
	
	private float weightSum() {
		float sum = 0;
		
		for (Float weight: this.weights.values()) {
			sum += weight;
		}
		return sum;
	}
	
	public Orientation getOrientation() {
		return orientation;
	}

	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}
}
