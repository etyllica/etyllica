package br.com.etyllica.multimedia;

import br.com.etyllica.core.loader.MultimediaLoader;


/**
 * 
 * @author mscythe
 * @license LGPLv3
 *
 */

public class Sound{

	protected String path = "";
	
	public Sound(String path){
		super();
		this.path = path;
	}
	
	public String getPath(){
		return path;
	}
		
	public void play(){
		MultimediaLoader.getInstancia().tocaSom(path);
	}
	
} 
