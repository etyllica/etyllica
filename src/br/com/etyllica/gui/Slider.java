package br.com.etyllica.gui;

import br.com.etyllica.core.Configuration;
import br.com.etyllica.core.Theme;
import br.com.etyllica.core.control.mouse.MouseButton;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyboardEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.video.Grafico;

/**
 * 
 * @author mscythe
 * @license LGPLv3
 *
 */

public class Slider extends GUIComponent{

	private int minValue = 0;
	private int maxValue = 255;
	private int value = 0;
	
	protected Button button;

	public Slider(int x, int y, int w, int h){
		
		super(x,y,w,h);

		button = new Button(x, y, h/3, h);

	}

	@Override
	public GUIEvent updateMouse(PointerEvent event) {

		if(event.getPressed(MouseButton.MOUSE_BUTTON_LEFT)){
		//if(mouse.getPressionado(Mouse.BOTAO_ESQUERDO)){

			if(onMouse(event)){
				
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
	public void draw(Grafico g){

		//Draw Slide
		Theme theme = Configuration.getInstance().getTheme();

		g.setColor(theme.getBarColor());

		g.fillRect(x, y+h/3, w, h/5);

		//Draw Button
		button.draw(g);

	}

	public int getMinValue() {
		return minValue;
	}

	public void setMinValue(int minValue) {
		this.minValue = minValue;
	}

	public int getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		
		this.value = value;
		
		int interval = maxValue-minValue; 

		int bx = x+((value*w)/interval);
		
		button.setX(bx-button.getW()/2);
		
		
	}

	@Override
	public GUIEvent updateKeyboard(KeyboardEvent event) {
		// TODO Auto-generated method stub
		return GUIEvent	.NONE;
	}

}

