package br.com.etyllica.gui.label;

import br.com.etyllica.core.Configuration;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyboardEvent;
import br.com.etyllica.core.event.Tecla;
import br.com.etyllica.core.video.Grafico;
import br.com.etyllica.gui.Label;
import br.com.etyllica.theme.Theme;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class TextLabel extends Label{

	protected String text;
	
	protected float fontSize = 18;
	
	public TextLabel(int x, int y) {
		super(x, y);
	}
	
	public TextLabel(int x, int y, int w) {
		super(x, y, w);
	}
	
	public TextLabel(int x, int y, String text) {
		super(x, y);
		
		this.text = text;
	}
	
	public TextLabel(String text) {
		super(0,0);
		
		this.text = text;
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
	public void draw(Grafico g) {

		Theme theme = Configuration.getInstance().getTheme();
	
		g.setFont(theme.getFont().deriveFont(fontSize));
		
		if(!onFocus){
			g.setColor(theme.getTextColor());
		}else{
			g.setColor(theme.getButtonOnFocus());
		}
		
		//Label is always in center
		if(!theme.isShadow()){
			g.escreveLabel(bx, by, bw, bh, text);
		}else{
			g.escreveLabelSombra(bx, by, bw, bh, text ,theme.getShadowColor());
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
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public float getFontSize() {
		return fontSize;
	}

	public void setFontSize(float fontSize) {
		this.fontSize = fontSize;
	}
	
}
