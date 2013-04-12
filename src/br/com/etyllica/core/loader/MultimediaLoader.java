package br.com.etyllica.core.loader;


import sound.paulscode.Library;
import sound.paulscode.SoundSystem;
import sound.paulscode.SoundSystemConfig;
import sound.paulscode.SoundSystemException;
import sound.paulscode.codecs.CodecJLayerMP3;
import sound.paulscode.codecs.CodecJOrbis;
import sound.paulscode.codecs.CodecWav;
import sound.paulscode.libraries.LibraryJavaSound;



/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class MultimediaLoader extends Loader{

	private SoundSystem mySoundSystem;

	private static MultimediaLoader instancia = null;

	public static MultimediaLoader getInstance() {
		if(instancia==null){
			instancia = new MultimediaLoader();
		}

		return instancia;
	}

	public MultimediaLoader(){

		folder = "res/sounds/";
		
		try {
			
			SoundSystemConfig.setCodec( "wav", CodecWav.class );
			//SoundSystemConfig.setCodec( "ogg", CodecJOgg.class );
			SoundSystemConfig.setCodec( "ogg", CodecJOrbis.class );
			SoundSystemConfig.setCodec( "mp3", CodecJLayerMP3.class );
						
			SoundSystemConfig.addLibrary(LibraryJavaSound.class);
			//SoundSystemConfig.addLibrary(LibraryJOAL.class);
			
			SoundSystemConfig.setSoundFilesPackage(folder);
			
			mySoundSystem = new SoundSystem(LibraryJavaSound.class);
			//mySoundSystem = new SoundSystem(LibraryJOAL.class);
		}
		catch(SoundSystemException e) {
			System.err.println("Error on "+this.getClass().getSimpleName() );
		}

	}

	public void setSoundLibrary(Class<? extends Library> library){
		try {
			mySoundSystem = new SoundSystem(library);
		} catch (SoundSystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean isPlaying(String path){
		return mySoundSystem.playing(path);
	}
	
	//Loads ogg and mp3 too
	public void loadMusic(String path){

		mySoundSystem.loadSound(path);
		

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
