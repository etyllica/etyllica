package br.com.etyllica.effects.light;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.RadialGradientPaint;

import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.layer.GeometricLayer;

public class LightSource extends GeometricLayer {
		
	private float[] fractions = {.2f,.8f};
	
	private Color[] colors = {new Color(0f, 0f, 0f, 0f), new Color(0f, 0f, 0f, 1f)};
	
	private RadialGradientPaint rgp;
	
	public LightSource(int x, int y, int intensity) {
		super(x, y);
		w = intensity;
		h = intensity;
	}
	
	public int getIntensity() {
		return w;
	}
	
	public void drawLight(Graphic g) {

		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_IN, 1.0f));
		
		java.awt.geom.Point2D center = new java.awt.geom.Point2D.Float(x+w/2, y+h/2);
		
		rgp = new RadialGradientPaint(center, w/2, fractions, colors);
                        
        g.setPaint(rgp);
        
        g.fillOval(x, y, w, h);
	}
	
}
