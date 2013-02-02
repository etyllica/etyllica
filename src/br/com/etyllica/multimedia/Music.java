package br.com.etyllica.multimedia;

import br.com.etyllica.core.loader.MultimediaLoader;

/**
 * 
 * @author mscythe
 * @license LGPLv3
 *
 */

public class Music extends Sound{

	private boolean loop = true;
	
	public Music(String path){
		super(path);
	}
		
	public boolean isLoop() {
		return loop;
	}

	public void setLoop(boolean loop) {
		this.loop = loop;
	}

	@Override
	public void play(){
		MultimediaLoader.getInstancia().tocaMusica(path,loop);
	}
	
	public void playStream(){
		MultimediaLoader.getInstancia().tocaMusicaStream(path,loop);
	}
	
	public boolean isPlaying(){
		return MultimediaLoader.getInstancia().isPlaying(path);
	}

}
