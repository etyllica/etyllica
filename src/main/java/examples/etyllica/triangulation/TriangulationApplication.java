package examples.etyllica.triangulation;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;

import br.com.etyllica.core.context.Application;
import br.com.etyllica.core.event.MouseEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.linear.Triangle2;
import br.com.etyllica.util.triangulation.KongTriangulation;

public class TriangulationApplication extends Application {

	List<Vector2> points = new ArrayList<Vector2>();
	List<Triangle2> triangles = new ArrayList<Triangle2>();
	
	public TriangulationApplication(int w, int h) {
		super(w,h);
	}

	@Override
	public void load() {
		points.add(new Vector2(100, 80));
		points.add(new Vector2(120, 80));
		points.add(new Vector2(140, 100));
		points.add(new Vector2(160, 80));
		
		triangles = new KongTriangulation(points).triangulate();
				
		loading = 100;
	}
	
	@Override
	public void updateMouse(PointerEvent event) {
		if(event.isButtonUp(MouseEvent.MOUSE_BUTTON_LEFT)) {
			points.add(new Vector2(event.getX(), event.getY()));
		} else if(event.isButtonUp(MouseEvent.MOUSE_BUTTON_RIGHT)) {
			triangles = new KongTriangulation(points).triangulate();	
		}
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(this);
		
		g.setColor(Color.BLUE);		
		for (Triangle2 triangle:triangles) {
			g.drawLine(triangle.getA().x, triangle.getA().y, triangle.getB().x, triangle.getB().y);
			g.drawLine(triangle.getA().x, triangle.getA().y, triangle.getC().x, triangle.getC().y);
			g.drawLine(triangle.getB().x, triangle.getB().y, triangle.getC().x, triangle.getC().y);
		}
		
		g.setColor(Color.BLACK);
		for (Vector2 point:points) {
			g.fillCircle(point.x, point.y, 8);
		}
		
	}
	
}
