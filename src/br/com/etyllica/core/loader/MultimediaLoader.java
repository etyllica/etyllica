package br.com.etyllica.core.loader;


import com.paulscode.sound.SoundSystem;
import com.paulscode.sound.SoundSystemConfig;
import com.paulscode.sound.SoundSystemException;
import com.paulscode.sound.codecs.CodecJOrbis;
import com.paulscode.sound.codecs.CodecWav;
import com.paulscode.sound.libraries.LibraryJavaSound;

import de.cuina.fireandfuel.CodecJLayerMP3;

/**
 * 
 * @author mscythe
 * @license LGPLv3
 *
 */

public class MultimediaLoader extends GenericLoader{

	private SoundSystem mySoundSystem;

	private static MultimediaLoader instancia = null;

	public static MultimediaLoader getInstancia() {
		if(instancia==null){
			instancia = new MultimediaLoader();
		}

		return instancia;
	}

	public MultimediaLoader(){

		pasta = "res/sounds/";
		
		try {
			
			SoundSystemConfig.setCodec( "wav", CodecWav.class );
			//SoundSystemConfig.setCodec( "ogg", CodecJOgg.class );
			SoundSystemConfig.setCodec( "ogg", CodecJOrbis.class );
			SoundSystemConfig.setCodec( "mp3", CodecJLayerMP3.class );
						
			SoundSystemConfig.addLibrary(LibraryJavaSound.class);
			
			SoundSystemConfig.setSoundFilesPackage(pasta);
			
			mySoundSystem = new SoundSystem(LibraryJavaSound.class);
		}
		catch(SoundSystemException e) {
			System.err.println("WE GOT AN ERROR HERE HOUSTON" );
		}

	}

	public boolean isPlaying(String path){
		return mySoundSystem.playing(path);
	}
	
	//Tamb�m serve para mp3
	public void carregaMusica(String caminho){

		mySoundSystem.loadSound(caminho);
		

	}

	public void tocaMusica(String caminho, boolean loop){
		
		mySoundSystem.backgroundMusic( caminho, caminho, loop );
		
        mySoundSystem.play(caminho);
	}
	

	public void carregaSom(String caminho){

		mySoundSystem.loadSound(caminho);
		
	}

	public void tocaSom(String caminho){
	
		mySoundSystem.quickPlay( false, caminho, false,
                0, 0, 0,
                SoundSystemConfig.ATTENUATION_NONE,
                SoundSystemConfig.getDefaultRolloff());

	}

	public void tocaMusicaStream(String caminho) {
		tocaMusicaStream(caminho, false);
	}
	
	public void tocaMusicaStream(String caminho, boolean loop) {

		//mySoundSystem.newStreamingSource(true, caminho, caminho, loop, 0, 0, 0, SoundSystemConfig.ATTENUATION_NONE, 0);
		mySoundSystem.backgroundMusic(caminho, caminho, loop);
		mySoundSystem.play(caminho);

	}
	
	

}
