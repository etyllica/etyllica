package br.com.etyllica.effects.light;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.RadialGradientPaint;

import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.layer.GeometricLayer;

public class LightSource extends GeometricLayer implements LightSpot {
		
	private float[] fractions = {.2f,.8f};
	
	private Color centerColor = new Color(0f, 0f, 0f, 0f);
	
	private Color borderColor = new Color(0f, 0f, 0f, 1f);
	
	private Color[] colors;
	
	private RadialGradientPaint rgp;
	
	public LightSource(int x, int y, int intensity) {
		super(x, y);
		w = intensity;
		h = intensity;
		
		resetColors();
	}
	
	public int getIntensity() {
		return w;
	}
	
	public void setIntensity(int intensity) {
		w = intensity;
		h = intensity;
	}
	
	public void drawLight(Graphics g) {

		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_IN, 1.0f));
		
		java.awt.geom.Point2D center = new java.awt.geom.Point2D.Float(x+w/2, y+h/2);
		
		rgp = new RadialGradientPaint(center, w/2, fractions, colors);
                        
        g.setPaint(rgp);
        
        g.fillOval(x, y, w, h);
	}
	
	private void resetColors() {
		colors = new Color[2];
		colors[0] = centerColor;
		colors[1] = borderColor;
	}

	public Color getCenterColor() {
		return centerColor;
	}

	public void setCenterColor(Color centerColor) {
		this.centerColor = centerColor;
		resetColors();
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
		resetColors();
	}
	
}
