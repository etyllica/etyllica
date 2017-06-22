package br.com.etyllica.core.graphics;

import java.awt.Color;

import br.com.etyllica.layer.GeometricLayer;

public interface TextGraphics {

	public void drawString(String text, int x, int y);

	public void drawString(String text, float x, float y);
	
	public void drawString(String text, int x, int y, int w, int h);
	
	public void drawString(String text, GeometricLayer layer);
	
	public void drawString(String text, GeometricLayer layer, int offsetX, int offsetY);

	public void drawString(String text, float x, float y, float w, float h);

	public void drawStringExponent(String text, String exponent, int x, int y);

	public void drawStringExponentShadow(String text, String exponent, int x, int y);

	public void drawStringShadow(String text, int x, int y, int w, int h);

	public void drawStringShadow(String text, int x, int y, int w, int h, Color shadowColor);

	public void drawStringShadow(String text, int x, int y, int w, int h, br.com.etyllica.commons.graphics.Color shadowColor);
	
	public void drawStringShadow(String text, float x, float y, float w, float h, Color shadowColor);

	public void drawStringShadow(String text, float x, float y, float w, float h, br.com.etyllica.commons.graphics.Color shadowColor);

	public void drawStringBorder(String text, float x, float y);
	
	public void drawStringBorder(String text, int x, int y, int w, int h);
	
	public void drawStringBorderX(String text, float y);

	public void drawStringShadow(String text, int x, int y);

	public void drawStringShadow(String text, float x, float y);
	
	public void drawStringShadow(String text, GeometricLayer layer);

	public void drawStringShadow(String text, int x, int y, Color shadowColor);

	public void drawStringShadow(String text, int x, int y, br.com.etyllica.commons.graphics.Color shadowColor);

	public void drawStringShadow(String text, float x, float y, Color shadowColor);

	public void drawStringShadow(String text, float x, float y, br.com.etyllica.commons.graphics.Color shadowColor);

	public void drawStringShadowX(String text, int y);

	public void drawStringShadowX(String text, float y);
	
	public void drawStringX(String text, float offsetX, float y);
	
	public void drawStringX(String text, int y);
	
	public void drawStringX(String text, float y);
	
}
