package br.com.etyllica.gui.label;

import br.com.etyllica.core.Configuration;
import br.com.etyllica.core.Theme;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyboardEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.event.Tecla;
import br.com.etyllica.core.loader.FontLoader;
import br.com.etyllica.core.video.Grafico;
import br.com.etyllica.gui.Label;

/**
 * 
 * @author mscythe
 * @license LGPLv3
 *
 */

public class TextLabel extends Label{

	//protected String text;
	
	protected int fontSize = 18;
	
	public TextLabel(int x, int y) {
		super(x, y);
	}
	public TextLabel(int x, int y, String text) {
		super(x, y,text);
	}
	
	public TextLabel(String text) {
		super(0, 0,text);
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
	
		g.setFont(FontLoader.getInstancia().getFonte(theme.getFontName(),fontSize));		
		
		if(!onFocus){
			g.setColor(theme.getTextColor());
		}else{
			g.setColor(theme.getButtonOnFocus());
		}
		
		if(!center){
			if(!theme.isShadow()){
				g.escreve(x, y, text);
			}else{
				g.escreveSombra(x, y, text);
			}
		}
		else{
			g.escreveX(y, text);
			
			if(!theme.isShadow()){
				g.escreveX(y, text);
			}else{
				g.escreveSombraX(y, text);
			}
		}
	}

	@Override
	public GUIEvent updateMouse(PointerEvent event) {
		return GUIEvent.NONE;
	}
	
	@Override
	public GUIEvent updateKeyboard(KeyboardEvent event) {
		
		if(event.getPressed(Tecla.TSK_TAB)){

			return GUIEvent.NEXT_COMPONENT;
		}
		
		return GUIEvent.NONE;
	}

	public int getFontSize() {
		return fontSize;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}
	
}
