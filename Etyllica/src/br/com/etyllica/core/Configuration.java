package br.com.etyllica.core;

import br.com.etyllica.gui.theme.Theme;
import br.com.etyllica.i18n.Language;
import br.com.etyllica.theme.ThemeManager;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Configuration {

	private static Configuration instance = null;
	
	//private Language lang = Language.JAPANESE;
	//private Language lang = Language.ENGLISH_USA;
	private Language language = Language.PORTUGUESE_BRAZIL;
		
	
	private boolean timerClick = true;
	private boolean numpadMouse = false;
	
	private boolean languageChanged = false;
	private boolean themeChanged = false;
	
	private Configuration(){
		super();
	}

	public static Configuration getInstance() {
		if(instance==null){
			instance = new Configuration();
		}

		return instance;
	}

	public Language getLanguage() {
		return language;
	}

	/**
	 * 
	 * @param language
	 */
	public void setLanguage(Language language) {
		this.language = language;
		
		if(language==Language.JAPANESE){
			ThemeManager.getInstance().getTheme().setFontName(Theme.FONT_JAPANESE);
		}else{
			ThemeManager.getInstance().getTheme().setFontName(Theme.FONT_DEFAULT);
		}
		
		ThemeManager.getInstance().getTheme().reloadFonts();
		
		setLanguageChanged(true);
		
		//TODO Each component is responsible for it
		//addGUIEvent(GUIEvent.LANGUAGE_CHANGED);
	}

	public boolean isTimerClick() {
		return timerClick;
	}

	/**
	 * 
	 * @param timerClick
	 */
	public void setTimerClick(boolean timerClick) {
		this.timerClick = timerClick;
	}

	public boolean isNumpadMouse() {
		return numpadMouse;
	}

	/**
	 * 
	 * @param numpadMouse
	 */
	public void setNumpadMouse(boolean numpadMouse) {
		this.numpadMouse = numpadMouse;
	}

	public boolean isLanguageChanged() {
		return languageChanged;
	}

	/**
	 * 
	 * @param languageChanged
	 */
	public void setLanguageChanged(boolean languageChanged) {
		this.languageChanged = languageChanged;
	}
	
	public boolean isThemeChanged() {
		return themeChanged;
	}
	
	/**
	 * 
	 * @param themeChanged
	 */
	public void setThemeChanged(boolean themeChanged) {
		this.themeChanged = themeChanged;
	}
	

}
