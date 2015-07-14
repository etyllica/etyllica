package br.com.etyllica.core.i18n;

import br.com.etyllica.loader.LoaderImpl;

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
