package br.com.etyllica.core.loader;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Loader {

	protected URL url;
	
	//protected String folder = "http://www.etyllica.com.br/imagens/";
	protected String folder;
	
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
