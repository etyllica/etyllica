package br.com.etyllica.i18n;

import br.com.etyllica.core.loader.LoaderImpl;

/**
 * 
 * @author yuripourre
 * @license GPLv3
 *
 */

public class LanguageLoader extends LoaderImpl{

	private LanguageLoader(){
		super();
		
		folder = "lang/";
	}
	
}
