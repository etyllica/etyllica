package br.com.etyllica.gui.label;

import java.awt.Color;

import br.com.etyllica.core.Configuration;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyboardEvent;
import br.com.etyllica.core.event.Tecla;
import br.com.etyllica.core.theme.Theme;
import br.com.etyllica.core.video.Graphic;
import br.com.etyllica.gui.Label;
import br.com.etyllica.layer.Layer;
import br.com.etyllica.layer.TextLayer;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class TextLabel extends Label{

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
	public void draw(Graphic g) {

		Theme theme = Configuration.getInstance().getTheme();
	
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
	public GUIEvent updateKeyboard(KeyboardEvent event) {
		
		if(event.getPressed(Tecla.TSK_TAB)){

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
	public void setFontSize(float size) {
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
	public void setBorderWidth(float borderWidh) {
		this.layer.setBorderWidth(borderWidh);
	}

	@Override
	public void centralize(int x, int y, int w, int h) {
		layer.centralize(x, y, w, h);
	}

	@Override
	public void centralize(Layer layer) {
		layer.centralize(layer);
	}
	
	@Override
	public void centralizeX(Layer layer) {
		layer.centralizeX(layer);
	}

	@Override
	public int centralizeX(int startX, int endX) {
		return layer.centralizeX(startX, endX);
	}

	@Override
	public void centralizeY(Layer layer) {
		layer.centralizeY(layer);
	}

	@Override
	public int centralizeY(int startY, int endY) {
		return layer.centralizeY(startY, endY);
	}
		
}