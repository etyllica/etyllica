package br.com.etyllica.i18n;

import br.com.etyllica.core.Configuration;
import br.com.etyllica.gui.theme.Theme;
import br.com.etyllica.theme.ThemeManager;

public class LanguageHandler implements LanguageChangerListener {

	@Override
	public void changeLanguage(Language language) {
		
		Configuration.getInstance().setLanguage(language);

		if(language == Language.JAPANESE) {
			ThemeManager.getInstance().getTheme().setFontName(Theme.FONT_JAPANESE);
		}else{
			ThemeManager.getInstance().getTheme().setFontName(Theme.FONT_DEFAULT);
		}
		
		ThemeManager.getInstance().getTheme().reloadFonts();
		
	}	
	
}
