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
	
	@Override
	protected void resize() {
		final int vx = left();
		final int vw = width();
		final int vy = top();
		final int vh = height();
				
		int lastPosition = 0;
		int totalSpace = 0;
		
		if (orientation == Orientation.VERTICAL) {
			totalSpace = vh-style.padding.top-style.padding.bottom;
		} else {
			totalSpace = vw-style.padding.left-style.padding.right;
		}
		
		int count = 0;
		for(View view: views) {
			count ++;
			int size = (int)viewSize(totalSpace, view);
			if (count == views.size()) {
				size--;
			}

			//Vertical Panel
			if (orientation == Orientation.VERTICAL) {
				view.setBounds(vx+style.padding.left, vy+lastPosition, vw-style.padding.left-style.padding.right-1, size);
			} else {
				view.setBounds(vx+lastPosition, vy+style.padding.top, size, vh-style.padding.top-style.padding.bottom-1);
			}
			
			lastPosition += size;
			
			view.resize();
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
