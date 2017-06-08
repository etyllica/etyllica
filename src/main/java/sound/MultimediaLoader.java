package sound;


import br.com.etyllica.core.loader.LoaderImpl;
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

public class MultimediaLoader extends LoaderImpl {

	private SoundSystem mySoundSystem;

	private static MultimediaLoader instance = null;

	public static MultimediaLoader getInstance() {
		if(instance==null){
			instance = new MultimediaLoader();
		}

		return instance;
	}

	public MultimediaLoader() {

		folder = "assets/sounds/";
		
		try {
			
			SoundSystemConfig.setCodec( "wav", CodecWav.class );
			//SoundSystemConfig.setCodec( "ogg", CodecJOgg.class );
			SoundSystemConfig.setCodec( "ogg", CodecJOrbis.class );
			SoundSystemConfig.setCodec( "mp3", CodecJLayerMP3.class );
						
			SoundSystemConfig.addLibrary(LibraryJavaSound.class);
			//SoundSystemConfig.addLibrary(LibraryJOAL.class);
			
			//SoundSystemConfig.setSoundFilesPackage(folder);
			
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
	
	public boolean isPlaying(String path) {
		return mySoundSystem.playing(path);
	}

	public void loadSound(String path) {
		mySoundSystem.loadSound(fullPath(path));
	}

	public void playSound(String path) {
	
		mySoundSystem.quickPlay( false, fullPath(path), false,
                0, 0, 0,
                SoundSystemConfig.ATTENUATION_NONE,
                SoundSystemConfig.getDefaultRolloff());
	}
	
	//Loads ogg and mp3 too
	public void loadMusic(String path) {
		mySoundSystem.loadSound(fullPath(path));
	}

	public void playMusic(String path, boolean loop) {
		mySoundSystem.backgroundMusic( fullPath(path), fullPath(path), loop );
	}

	public void playMusicStream(String path) {
		playMusicStream(path, false);
	}
	
	public void playMusicStream(String path, boolean loop) {
		//mySoundSystem.newStreamingSource(true, fullPath()+path, fullPath()+path, loop, 0, 0, 0, SoundSystemConfig.ATTENUATION_NONE, 0);
		playMusic(path, loop);
	}

	public void stop(String path) {
		mySoundSystem.stop(fullPath(path));
	}
	

}
