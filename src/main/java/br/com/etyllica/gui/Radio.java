package br.com.etyllica.gui;

import java.awt.Color;

import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.gui.base.BaseCheckBox;
import br.com.etyllica.theme.ThemeManager;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Radio extends BaseCheckBox {

	private RadioGroup group;
	
	private String value;

	public Radio(int x, int y){
		super(x,y);
	}
	
	public Radio(int x, int y, int w, int h){
		super(x,y,w,h);
	}

	@Override
	protected void leftClick() {
		mark();
	}
	
	@Override
	public void update(GUIEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Graphic g) {

		g.setColor(Color.WHITE);
		//g.fillOval(x, y, w, h);
		
		if(!mouseOver) {
			g.setColor(ThemeManager.getInstance().getTheme().getTextFieldWithoutFocusColor());
		}else{
			g.setColor(ThemeManager.getInstance().getTheme().getTextFieldOnMouseColor());
		}
		
		g.drawOval(x, y, w, h);
		
		g.setColor(ThemeManager.getInstance().getTheme().getTextFieldWithoutFocusColor());
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
	
	public void mark() {
		
		if(!checked) {
			
			if(group!=null) {
				group.mark(this);
			}

			checked = true;
		}
		
	}
	
}
