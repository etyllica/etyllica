package br.com.etyllica.ui.base;

import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.ui.View;

/**
 * TextView (non-editable) component
 * 
 * @author yuripourre
 *
 */

public class BaseTextView extends View {

	private String text = "";
	
	public BaseTextView(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	@Override
	public GUIEvent updateMouse(PointerEvent event) {
		return GUIEvent.NONE;
	}

	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {
		return GUIEvent.NONE;
	}

	@Override
	public void updateEvent(GUIEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void draw(Graphics g) {
		if (!getTheme().isShadow()) {
			g.drawString(text, left(), top(), width(), height());
		} else {
			g.drawStringShadow(text, left(), top(), width(), height(), getTheme().getShadowColor());
		}
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void copy(BaseTextView view) {
		super.copy(view);
		text = view.text;
	}

}
