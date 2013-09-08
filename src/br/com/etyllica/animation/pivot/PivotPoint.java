package br.com.etyllica.animation.pivot;

import java.util.ArrayList;
import java.util.List;

import br.com.etyllica.core.video.Graphic;
import br.com.etyllica.linear.vector.Vector2D;

public class PivotPoint extends Vector2D{
	
	private List<Part> partes = new ArrayList<Part>();
	
	public PivotPoint(int x, int y){
		super(x,y);
	}

	public List<Part> getPartes() {
		return partes;
	}

	public void setPartes(List<Part> partes) {
		this.partes = partes;

	}
	
	public void addParte(Part parte){
		
		linkPart(parte);

		this.partes.add(parte);
	}
	
	private void linkPart(Part part){
		int px = part.getX()+part.getXPivot();
		int py = part.getY()+part.getYPivot();

		int ox = (int)x-px;
		int oy = (int)y-py;
		
		part.setOffset(ox,oy);
	}

	@Override
	public void setX(double x){
		
		this.x = x;
		
		for(Part parte: partes){
			
			int px = parte.getX()+parte.getXPivot();

			int ox = (int)x-px;
			
			parte.setOffsetX(ox);
		}
	}
	
	@Override
	public void setY(double y){
		
		this.y = y;
		
		for(Part parte: partes){
			
			int py = parte.getY()+parte.getYPivot();

			int oy = (int)y-py;
			
			parte.setOffsetY(oy);
		}
	}
	
	public void drawPart(Graphic g) {
		for(Part part: partes){
			part.draw(g);
		}
	}
	
	public void rotate(double cx, double cy, double angle){
				
		double px = x-cx;
		double py = y-cy;
		
		double c = Math.cos(Math.toRadians(angle));
		double s = Math.sin(Math.toRadians(angle));

		double rotatedX = px * c - py * s + cx;
		double rotatedY = px * s + py * c + cy;

		for(Part part: partes){
			//Avoid bad approximations 
			linkPart(part);
			part.setOffset((int)(rotatedX-x),(int)(rotatedY-y));
			
			part.rotateByParent(angle);
		}
		
		x = rotatedX;
		y = rotatedY;
	}
	
}
