package examples.sound;

import sound.MultimediaLoader;
import br.com.etyllica.Etyllica;
import br.com.etyllica.commons.context.Application;
import examples.sound.application.SyntheticAudioApplication;

public class SoundExample extends Etyllica {
	
	/** The serial version id */
	private static final long serialVersionUID = 5663760293144882635L;
	
	/**
	 * Default constructor for the window
	 */
	public SoundExample() {
		super(800,600);
	}

	public static void main(String[] args){
		SoundExample example = new SoundExample();
		example.init();
	}

	@Override
	public Application startApplication() {
		addLoader(MultimediaLoader.getInstance());
		
		initialSetup("../../");
		
		//return new PlaySoundApplication(w,h);
		return new SyntheticAudioApplication(w,h);
	}

}
