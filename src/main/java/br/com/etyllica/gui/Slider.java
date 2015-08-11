package br.com.etyllica.gui;

import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.MouseButton;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.gui.factory.DefaultButton;
import br.com.etyllica.theme.Theme;
import br.com.etyllica.theme.ThemeManager;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Slider extends View{

	protected int minValue = 0;
	
	protected int maxValue = 255;
	
	protected int value = 0;
	
	protected DefaultButton button;

	public Slider(int x, int y, int w, int h){
		
		super(x,y,w,h);

		button = new DefaultButton(x, y, h/3, h);

	}

	@Override
	public GUIEvent updateMouse(PointerEvent event) {

		if(mouseOver) {
			
			if(event.isButtonDown(MouseButton.MOUSE_BUTTON_LEFT)) {
				
				int interval = maxValue-minValue;
				
				int mx = event.getX()-x;
				
				value = (mx*interval)/w; 
				
				button.setX(event.getX()-button.getW()/2);
				
				return GUIEvent.COMPONENT_CHANGED;
			}
			
		}

		return GUIEvent.NONE;
	}

	@Override
	public void update(GUIEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Graphic g){

		//Draw Slide
		Theme theme = ThemeManager.getInstance().getTheme();

		g.setColor(theme.getBarColor());

		g.fillRect(x, y+h/3, w, h/5);

		//Draw Button
		button.draw(g);

	}

	public float getMinValue() {
		return minValue;
	}

	public void setMinValue(int minValue) {
		this.minValue = minValue;
	}

	public float getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}

	public float getValue() {
		return value;
	}

	public void setValue(int value) {
		
		this.value = value;
		
		int interval = maxValue-minValue; 

		int bx = x+((value*w)/interval);
		
		button.setX(bx-button.getW()/2);
		
	}

	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {
		// TODO Auto-generated method stub
		return GUIEvent	.NONE;
	}

}

