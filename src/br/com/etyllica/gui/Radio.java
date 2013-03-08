package br.com.etyllica.gui;

import java.awt.Color;

import br.com.etyllica.core.Configuration;
import br.com.etyllica.core.control.mouse.MouseButton;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyState;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.video.Grafico;

/**
 * 
 * @author mscythe
 * @license LGPLv3
 *
 */

public class Radio extends CheckBox{

	private RadioGroup group;
	
	private String value;

	public Radio(int x, int y){
		super(x,y);
	}

	@Override
	public GUIEvent updateMouse(PointerEvent event) {

		if(onMouse(event)){

			mouseOver = true;

			if(event.getState()==KeyState.PRESSED){

				if(event.isKey(MouseButton.MOUSE_BUTTON_LEFT)){

					if(!checked){
						if(group!=null){
							group.mark(this);
						}

						checked = true;
					}
				}

			}

			return GUIEvent.MOUSE_OVER;

		}else{

			mouseOver = false;
			return GUIEvent.NONE;

		}


	}

	@Override
	public void update(GUIEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Grafico g) {

		g.setColor(Color.WHITE);
		//g.fillOval(x, y, w, h);
		
		if(!mouseOver){
			g.setColor(Configuration.getInstance().getTheme().getTextFieldWithoutFocusColor());
		}else{
			g.setColor(Configuration.getInstance().getTheme().getTextFieldOnMouseColor());
		}
		g.drawOval(x, y, w, h);
		
		g.setColor(Configuration.getInstance().getTheme().getTextFieldWithoutFocusColor());
		if(checked){
			g.fillCircle(x+w/2, y+h/2, w/5);
		}
	}

	public RadioGroup getGroup() {
		return group;
	}

	public void setGroup(RadioGroup group) {
		this.group = group;
	}

	@Override
	public boolean onMouse(int mx, int my) {
		return colideCirclePoint(mx, my);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
