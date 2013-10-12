package br.com.etyllica.core.application;

import java.util.HashMap;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class SessionMap extends HashMap<String,Object>{

	private static final long serialVersionUID = 7770904534534479007L;

	public int getInt(String param){
		
		return Integer.parseInt(this.get(param).toString());
		
	}
	
	public String getString(String param){
		
		return this.get(param).toString();
		
	}
	
}
