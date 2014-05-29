package examples.gui.hud;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Arc2D;

import br.com.etyllica.context.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.image.paint.ConicalGradientPaint;

/**
 * Concept based on Star Trek: Into Darkness - Med Bay UI
 *  
 * http://www.rudyvessup.com/work/star-trek-into-darkness-med-bay-ui/
 * 
 */

public class DarknessMedUI extends Application {

	private int mx = 0;
	
	private int my = 0;
	
	private float angle = 0;
	
	private float extent = 270;
	
	private ConicalGradientPaint rgp;
			
	private Color[] colors;
	
	private Color startColor;
	
	public DarknessMedUI(int w, int h) {
		super(w, h);
	}	
	
	@Override
	public void load() {
				
		float startAngle = 0;
		
		startColor = new Color(0, 128, 0, 128);
		Color end = new Color(0, 128, 0, 0);
		
		colors = new Color[2];
		colors[0] = startColor;
		colors[1] = end;
		
		rgp = new ConicalGradientPaint(
				true,
				new Point(w / 2, h / 2),
				0.5f, new float[]{startAngle, extent}, colors);
		
		updateAtFixedRate(50);
		
		loading = 100;
	}
	
	public void timeUpdate(long now) {

		angle += 5;
		
		if (angle > 360) {
			angle = 0;
		}
		
		rgp = new ConicalGradientPaint(true, new Point(mx, my), 0.5f, new float[]{angle, angle+extent}, colors);
		
	}
	
	@Override
	public void draw(Graphic g) {
				
		g.setPaint(rgp);
		
		g.setBasicStroke(10f);
				
		float radius = 80;
		
		double aw = radius;
		double ah = radius;
		
		Arc2D rightArc = new Arc2D.Double(mx-radius, my-radius, aw*2, ah*2, angle, -extent, Arc2D.OPEN);
				
		g.draw(rightArc);
		
		g.setFontSize(30);
		g.setColor(startColor);
		g.drawString(mx-radius, my-radius, (float)aw*2, (float)ah*2, Float.toString(extent));
						
	}

	@Override
	public GUIEvent updateMouse(PointerEvent event) {
		
		mx = event.getX();
		
		my = event.getY();
		
		return null;
	}

	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

}
