package br.com.etyllica.core.i18n;

import br.com.etyllica.ui.theme.Theme;
import br.com.etyllica.ui.theme.ThemeManager;

public class LanguageHandler implements LanguageChangerListener {

	@Override
	public void changeLanguage(Language language) {
		LanguageModule.getInstance().setLanguage(language);

		if(language == Language.JAPANESE) {
			ThemeManager.getInstance().getTheme().setFontName(Theme.FONT_JAPANESE);
		}else{
			ThemeManager.getInstance().getTheme().setFontName(Theme.FONT_DEFAULT);
		}

		ThemeManager.getInstance().getTheme().reloadFonts();
	}
	
}
