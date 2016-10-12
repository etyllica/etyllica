package br.com.etyllica.core;

import br.com.etyllica.core.i18n.Language;

/**
 * 
 * @author yuripourre
 *
 */

public class Configuration {

	private static Configuration instance = null;
	
	private Language language = Language.PORTUGUESE_BRAZIL;
	
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

	public Language getLanguage() {
		return language;
	}

	/**
	 * 
	 * @param language
	 */
	public void setLanguage(Language language) {
		this.language = language;		
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

}
