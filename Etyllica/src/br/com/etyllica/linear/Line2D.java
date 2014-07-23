package br.com.etyllica.linear;

public class Line2D {

	private Point2D p1;
	
	private Point2D p2;
	
	public Line2D(Point2D p1, Point2D p2) {
		super();
		
		this.p1 = p1;
		this.p2 = p2;
	}

	public Point2D getP1() {
		return p1;
	}

	public Point2D getP2() {
		return p2;
	}
	
	public static Point2D[] interpolate(Point2D p1, Point2D p2, int points) {
		
		if(points<2) {
			Point2D[] array = new Point2D[2];
		
			array[0] = Point2D.clone(p1);
			array[1] = Point2D.clone(p2);
			
			return array;
		}
		
		Point2D[] array = new Point2D[points];
		
		array[0] = Point2D.clone(p1);
		
		int sections = points-1;
		
		array[sections] = Point2D.clone(p2);
				
		for(int i=1; i<sections; i++) {
			
			double px = p1.getX()+((p2.getX()-p1.getX())/sections)*i;
			double py = p1.getY()+((p2.getY()-p1.getY())/sections)*i;
						
			array[i] = new Point2D(px, py);
		}
		
		return array;
	}
	
}
