package br.com.etyllica.theme.darkness;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Arc2D;

import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.gui.Slider;
import br.com.etyllica.image.paint.ConicalGradientPaint;
import br.com.etyllica.theme.ThemeManager;

public class RoundSlider extends Slider {
	
	private Arc2D arc;
			
	private ConicalGradientPaint rgp;
	
	private Color[] colors;
	
	private Color startColor;
	
	private Color endColor;
	
	public RoundSlider(int x, int y, int radius) {
		super(x, y, radius*2, radius*2);
		
		startColor = new Color(0, 128, 0, 20);
		
		endColor = new Color(0, 128, 0, 128);
		
		colors = new Color[3];
		colors[0] = startColor;
		colors[1] = endColor;
		colors[2] = endColor;
		
		float angle = 80;
		
		float extent = 190;
				
		rgp = new ConicalGradientPaint(
				true,
				new Point(x + w/2, y + h/2),
				-0.5f, new float[]{angle, extent, 360}, colors);
		
		arc = new Arc2D.Double(x, y, w, h, angle, 0, Arc2D.OPEN);
		
	}
	
	@Override
	public void setValue(int value) {
		
		this.value = value;
		
		float arcValue = value-minValue*360/maxValue;

		arc.setAngleExtent(-arcValue);
						
	}

	@Override
	public void draw(Graphic g){

		g.setBasicStroke(12f);
		
		g.setAlpha(30);
		g.setColor(startColor);
		g.drawArc(x, y, w, h, 0, 360);
		
		g.setBasicStroke(10f);
		
		g.setAlpha(100);
		
		//Draw Slide
		g.setPaint(rgp);
				
		g.draw(arc);
				
		g.setFontSize(30);
		g.setAlpha(80);
		
		g.setColor(endColor);
		g.drawString(x, y, w, h, Integer.toString(value));
		g.setAlpha(100);
		
	}
	
}


