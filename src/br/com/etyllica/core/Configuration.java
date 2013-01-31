package br.com.etyllica.core;

import br.com.etyllica.core.control.mouse.theme.ArrowTheme;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.gui.Gui;
import br.com.etyllica.lang.Language;

/**
 * 
 * @author mscythe
 * @license LGPLv3
 *
 */

public class Configuration {

	private static Configuration instance = null;
		
	//Default Theme
	//TODO Button Factory
	private Theme theme = new Theme();
	private ArrowTheme arrowTheme = new ArrowTheme();
	
	//private Language lang = Language.JAPANESE;
	//private Language lang = Language.ENGLISH_USA;
	private Language lang = Language.PORTUGUESE_BRAZIL;
		
	
	private boolean timerClick = false;
	private boolean numpadMouse = false;
	
	private Configuration(){
		super();
	}

	public static Configuration getInstance() {
		if(instance==null){
			instance = new Configuration();
		}

		return instance;
	}
	
	public Theme getTheme() {
		return theme;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}
	
	public ArrowTheme getArrowTheme() {
		return arrowTheme;
	}

	public void setArrowTheme(ArrowTheme arrowTheme) {
		this.arrowTheme = arrowTheme;
	}

	public Language getLang() {
		return lang;
	}

	public void setLang(Language lang) {
		this.lang = lang;
		
		if(lang==Language.JAPANESE){
			theme.setFontName(Theme.FONT_JAPANESE);
		}else{
			theme.setFontName(Theme.FONT_DEFAULT);
		}
		
		theme.reloadFonts();
		
		//Force GUIEvent update
		Gui.getInstance().addGUIEvent(GUIEvent.LANGUAGE_CHANGED);
	}

	public boolean isTimerClick() {
		return timerClick;
	}

	public void setTimerClick(boolean timerClick) {
		this.timerClick = timerClick;
	}

	public boolean isNumpadMouse() {
		return numpadMouse;
	}

	public void setNumpadMouse(boolean numpadMouse) {
		this.numpadMouse = numpadMouse;
	}

}
