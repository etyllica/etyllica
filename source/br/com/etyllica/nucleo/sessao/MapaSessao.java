package br.com.etyllica.nucleo.sessao;

import java.util.HashMap;

public class MapaSessao extends HashMap<String,Object>{

	private static final long serialVersionUID = 7770904534534479007L;

	public int getInt(String parametro){
		
		return Integer.parseInt(this.get(parametro).toString());
		
	}
	
	public String getString(String parametro){
		
		return this.get(parametro).toString();
		
	}
	
}
