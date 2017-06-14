package examples.etyllica.tutorial21;

import br.com.etyllica.EtyllicaApplet;
import br.com.etyllica.commons.context.Application;

public class Tutorial21 extends EtyllicaApplet {

	private static final long serialVersionUID = 1L;

	public Tutorial21() {
		super(800, 200);
	}
	
	@Override
	public Application startApplication() {
		initialSetup("../../");
		return new ParallaxApplication(w, h);
	}
	
}
