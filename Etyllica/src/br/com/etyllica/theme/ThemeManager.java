package br.com.etyllica.theme;

import br.com.etyllica.core.theme.Theme;
import br.com.etyllica.gui.mouse.theme.ArrowTheme;


public class ThemeManager {

	private static ThemeManager instance = null;
	
	public static ThemeManager getInstance() {
		if(instance==null){
			instance = new ThemeManager();
		}

		return instance;
	}
	
	private Theme theme = new EtyllicTheme();
	
	private ArrowTheme arrowTheme = new ArrowTheme();
	
	public Theme getTheme() {
		return theme;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}
	
	public ArrowTheme getArrowTheme() {
		return arrowTheme;
	}

	/**
	 * 
	 * @param arrowTheme
	 */
	public void setArrowTheme(ArrowTheme arrowTheme) {
		this.arrowTheme = arrowTheme;
	}
	
}
