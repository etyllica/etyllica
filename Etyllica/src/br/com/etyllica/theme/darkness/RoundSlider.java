package br.com.etyllica.theme.darkness;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Arc2D;

import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.gui.Slider;
import br.com.etyllica.image.paint.ConicalGradientPaint;

public class RoundSlider extends Slider {
	
	private Arc2D arc;
			
	private ConicalGradientPaint rgp;
	
	private Color[] colors;
	
	private Color startColor;
	
	public RoundSlider(int x, int y, int radius) {
		super(x, y, radius*2, radius*2);
		
		startColor = new Color(0, 128, 0, 128);
		Color end = new Color(0, 128, 0, 0);
		
		colors = new Color[2];
		colors[0] = startColor;
		colors[1] = end;
		
		float angle = 90;
		
		float extent = 360;
				
		rgp = new ConicalGradientPaint(
				true,
				new Point(x + w/2, y + h/2),
				0.5f, new float[]{angle, extent}, colors);
		
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

		//Draw Slide
		g.setPaint(rgp);
		
		g.setBasicStroke(10f);
						
		g.draw(arc);
		
		g.setFontSize(30);
		g.setColor(startColor);
		g.drawString(x, y, w, h, Integer.toString(value));

	}
	
}
