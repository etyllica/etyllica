package br.com.etyllica.core;

import br.com.etyllica.core.control.mouse.theme.ArrowTheme;
import br.com.etyllica.i18n.Language;
import br.com.etyllica.theme.Theme;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Configuration {

	private static Configuration instance = null;
	
	//private Core core;
		
	//Default Theme
	//TODO Button Factory
	private Theme theme = new Theme();
	private ArrowTheme arrowTheme = new ArrowTheme();
	
	//private Language lang = Language.JAPANESE;
	//private Language lang = Language.ENGLISH_USA;
	private Language language = Language.PORTUGUESE_BRAZIL;
		
	
	private boolean timerClick = false;
	private boolean numpadMouse = false;
	private boolean languageChanged = false;
	
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

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
		
		if(language==Language.JAPANESE){
			theme.setFontName(Theme.FONT_JAPANESE);
		}else{
			theme.setFontName(Theme.FONT_DEFAULT);
		}
		
		theme.reloadFonts();
		
		setLanguageChanged(true);
		
		//TODO Each component is responsible for it
		//addGUIEvent(GUIEvent.LANGUAGE_CHANGED);
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

	public boolean isLanguageChanged() {
		return languageChanged;
	}

	public void setLanguageChanged(boolean languageChanged) {
		this.languageChanged = languageChanged;
	}

}
