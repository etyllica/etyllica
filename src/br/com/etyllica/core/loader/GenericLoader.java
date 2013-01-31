package br.com.etyllica.core.loader;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * 
 * @author mscythe
 * @license LGPLv3
 *
 */

public class GenericLoader {

	protected URL url;
	
	//protected String pasta = "http://www.etyllica.com.br/mystic/imagens/";
	protected String pasta;
	
	public void setUrl(String s){
		try {
			url = new URL(s);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	} 

	public URL getUrl(){
		return url;
	}
}
