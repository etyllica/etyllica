package br.com.etyllica.gui.label;

import java.awt.Color;

import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.gui.Label;
import br.com.etyllica.layer.GeometricLayer;
import br.com.etyllica.layer.TextLayer;
import br.com.etyllica.theme.Theme;
import br.com.etyllica.theme.ThemeManager;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class TextLabel extends Label {

	protected TextLayer layer;
		
	public TextLabel(int x, int y) {
		super(x, y);
		
		this.layer = new TextLayer(x,y,"");
	}
	
	public TextLabel(int x, int y, int w) {
		super(x, y, w);
		
		this.layer = new TextLayer(x,y,"");
	}
	
	public TextLabel(int x, int y, String text) {
		super(x, y);
		
		this.layer = new TextLayer(x,y,text);
	}
	
	public TextLabel(String text) {
		this(0,0,text);
	}
	
	public TextLabel(String text, float size) {
		this(0,0,text);
		this.layer.setSize(size);
	}
	
	public void setX(int x) {
		this.x = x;
		this.layer.setX(x);
	}

	public void setY(int y) {
		this.y = y;
		this.layer.setY(y);
	}
	
	@Override
	public void update(GUIEvent event) {
		switch (event) {
		
		case LOST_FOCUS:
			onFocus = false;
			break;
		
		case GAIN_FOCUS:
			onFocus = true;
			break;

		default:
			break;
			
		}
	}

	@Override
	public void draw(Graphics g) {

		Theme theme = ThemeManager.getInstance().getTheme();
	
		g.setFont(theme.getFont().deriveFont(layer.getStyle()));
		g.setFont(theme.getFont().deriveFont(layer.getSize()));
		
		if(!onFocus){
			g.setColor(theme.getTextColor());
		}else{
			g.setColor(theme.getButtonOnFocus());
		}
		
		//Label is always in center
		if(!theme.isShadow()){
			g.drawString(bx, by, bw, bh, layer.getText());
		}else{
			g.drawStringShadow(bx, by, bw, bh, layer.getText() ,theme.getShadowColor());
		}
		
	}

	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {
		
		if(event.isKeyDown(KeyEvent.VK_TAB)) {
			return GUIEvent.NEXT_COMPONENT;
		}
		
		return GUIEvent.NONE;
	}

	public String getText() {
		return this.layer.getText();
	}

	/**
	 * 
	 * @param text
	 */
	public void setText(String text) {
		this.layer.setText(text);
	}
	
	public float getFontSize() {
		return this.layer.getSize();
	}

	/**
	 * 
	 * @param size
	 */
	public void setFontSize(int size) {
		this.layer.setSize(size);
	}
	
	/**
	 * 
	 * @param border
	 */
	public void setBorder(boolean border) {
		this.layer.setBorder(border);
	}
	
	/**
	 * 
	 * @param borderColor
	 */
	public void setBorderColor(Color borderColor) {
		this.layer.setBorderColor(borderColor);
	}
	
	/**
	 * 
	 * @param borderWidh
	 */
	public void setBorderWidth(int borderWidh) {
		this.layer.setBorderWidth(borderWidh);
	}

	@Override
	public void centralize(int x, int y, int w, int h) {
		layer.centralize(x, y, w, h);
	}

	@Override
	public void centralize(GeometricLayer layer) {
		layer.centralize(layer);
	}
	
	@Override
	public void centralizeX(GeometricLayer layer) {
		layer.centralizeX(layer);
	}

	@Override
	public int centralizeX(int startX, int endX) {
		return layer.centralizeX(startX, endX);
	}

	@Override
	public void centralizeY(GeometricLayer layer) {
		layer.centralizeY(layer);
	}

	@Override
	public int centralizeY(int startY, int endY) {
		return layer.centralizeY(startY, endY);
	}
	
	public TextLayer getLayer() {
		return layer;
	}

	public int getFontStyle() {
		return layer.getStyle();
	}

	public void setFontStyle(int fontStyle) {
		layer.setStyle(fontStyle);
	}
		
}