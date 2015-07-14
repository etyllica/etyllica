package br.com.etyllica.animation.pivot;

import java.util.ArrayList;
import java.util.List;

import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.core.linear.Point2D;

public class PivotPoint extends Point2D {
	
	private List<Part> parts = new ArrayList<Part>();
	
	public PivotPoint(int x, int y) {
		super(x,y);
	}
	
	public void addPart(Part part) {
		
		linkPart(part);

		this.parts.add(part);
	}
	
	private void linkPart(Part part) {
		float px = part.getX()+part.getXPivot();
		float py = part.getY()+part.getYPivot();

		int ox = (int)(x-px);
		int oy = (int)(y-py);
		
		part.setOffset(ox,oy);
	}

	@Override
	public void setX(double x) {
		
		this.x = x;
		
		for(Part part: parts) {
			
			float px = part.getX()+part.getXPivot();

			int ox = (int)(x-px);
			
			part.setOffsetX(ox);
		}
	}
	
	@Override
	public void setY(double y) {
		
		this.y = y;
		
		for(Part part: parts) {
			
			float py = part.getY()+part.getYPivot();

			int oy = (int)(y-py);
			
			part.setOffsetY(oy);
		}
	}
	
	public void drawPart(Graphic g) {
		for(Part part: parts) {
			part.draw(g);
		}
	}
	
	public void rotate(double cx, double cy, double angle) {
				
		double px = x-cx;
		double py = y-cy;
		
		double c = Math.cos(Math.toRadians(angle));
		double s = Math.sin(Math.toRadians(angle));

		double rotatedX = px * c - py * s + cx;
		double rotatedY = px * s + py * c + cy;

		for(Part part: parts) {
			//Avoid bad approximations 
			linkPart(part);
			part.setOffset((int)(rotatedX-x),(int)(rotatedY-y));
			
			part.rotateByParent(angle);
		}
		
		x = rotatedX;
		y = rotatedY;
	}

	public List<Part> getParts() {
		return parts;
	}
		
}
