package br.com.etyllica.gui.base;

import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.gui.Orientation;
import br.com.etyllica.gui.View;
import br.com.etyllica.gui.ViewGroup;

public abstract class UIViewGroup extends UIView {
	
	public UIViewGroup() {
		super();
	}
	
	@Override
	public void add(View component) {
		((ViewGroup) delegatedView).add(component);
	}
		
	@Override
	public void setBounds(int x, int y, int w, int h) {
		((ViewGroup) delegatedView).setBounds(x, y, w, h);
	}
	
	@Override
    public void drawWithChildren(Graphics g) {
		((ViewGroup) delegatedView).drawWithChildren(g);
	}

	public void setOrientation(Orientation orientation) {
		((ViewGroup) delegatedView).setOrientation(orientation);
	}
	
	public void add(View view, float weight) {
		((ViewGroup) delegatedView).add(view, weight);
	}

	public void setRowSize(int rowSize) {
		((ViewGroup) delegatedView).setRowSize(rowSize);
	}
	
	@Override
	public void resize() {
		((ViewGroup) delegatedView).resize();
	}
	
	public Orientation getOrientation() {
		return ((ViewGroup) delegatedView).getOrientation();
	}
}
