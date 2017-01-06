package br.com.etyllica.core.graphics;

import java.awt.Color;

import br.com.etyllica.layer.GeometricLayer;

public interface TextGraphics {

	/**
	 * 
	 * @param text
	 * @param x
	 * @param y
	 */
	public void drawString(String text, int x, int y);
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param text
	 */
	public void drawString(int x, int y, int w, int h, String text);
	
	/**
	 * 
	 * @param layer
	 * @param text
	 */
	public void drawString(GeometricLayer layer, String text);
	
	/**
	 * 
	 * @param layer
	 * @param text
	 */
	public void drawString(GeometricLayer layer, int offsetX, int offsetY, String text);

	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param text
	 */
	public void drawString(float x, float y, float w, float h, String text);

	/**
	 * @param x
	 * @param y
	 * @param text
	 * @param exponent
	 */
	public void drawStringExponent(int x, int y, String text, String exponent);

	public void drawStringExponentShadow(int x, int y, String text, String exponent);

	public void drawStringShadow(int x, int y, int w, int h, String text);

	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param text
	 * @param shadowColor
	 */
	public void drawStringShadow(int x, int y, int w, int h, String text, Color shadowColor);
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param text
	 * @param shadowColor
	 */
	public void drawStringShadow(float x, float y, float w, float h, String text, Color shadowColor);
	
	/**
	 * 
	 * @param offsetX
	 * @param y
	 * @param text
	 * @param border
	 */
	public void drawStringX(float offsetX, float y, String text, boolean border);
	
	/**
	 * 
	 * @param offsetX
	 * @param y
	 * @param text
	 * @param border
	 */
	public void drawStringBorder(float x, float y, String text);
	
	public void drawStringBorder(int x, int y, int w, int h, String text);
	
	/**
	 * 
	 * @param offsetX
	 * @param y
	 * @param text
	 * @param border
	 */
	public void drawStringBorderX(float y, String text);

	/**
	 * 
	 * @param x
	 * @param y
	 * @param text
	 */
	public void drawStringShadow(int x, int y, String text);

	/**
	 * 
	 * @param x
	 * @param y
	 * @param text
	 */
	public void drawStringShadow(float x, float y, String text);
	
	public void drawStringShadow(GeometricLayer layer, String text);

	/**
	 * 
	 * @param x
	 * @param y
	 * @param text
	 * @param shadowColor
	 */
	public void drawStringShadow(int x, int y, String text, Color shadowColor);

	/**
	 * 
	 * @param x
	 * @param y
	 * @param text
	 * @param shadowColor
	 */
	public void drawStringShadow(float x, float y, String text, Color shadowColor);

	/**
	 * 
	 * @param y
	 * @param text
	 */
	public void drawStringShadowX(int y, String text);

	/**
	 * 
	 * @param y
	 * @param text
	 */
	public void drawStringShadowX(float y, String text);
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param text
	 */
	public void drawString(float x, float y, String text);

	public void drawStringX(float y, String text);
	
}
