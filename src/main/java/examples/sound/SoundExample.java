package examples.sound;

import sound.MultimediaLoader;
import examples.physics.application.BoxesFalling;
import examples.sound.application.PlaySoundApplication;
import examples.sound.application.SyntheticAudioApplication;
import br.com.etyllica.EtyllicaFrame;
import br.com.etyllica.context.Application;

public class SoundExample extends EtyllicaFrame {
	
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
