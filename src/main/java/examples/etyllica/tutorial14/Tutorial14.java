package examples.etyllica.tutorial14;

import br.com.etyllica.EtyllicaApplet;
import br.com.etyllica.core.context.Application;

public class Tutorial14 extends EtyllicaApplet {

	private static final long serialVersionUID = 1L;

	public Tutorial14() {
		super(80, 60);
	}
	
	@Override
	public Application startApplication() {
		initialSetup("../../");
		return new SpriteAnimationTutorial(w,h);
	}
	
}
