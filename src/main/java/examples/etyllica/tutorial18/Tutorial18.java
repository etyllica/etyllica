package examples.etyllica.tutorial18;

import br.com.etyllica.EtyllicaApplet;
import br.com.etyllica.core.context.Application;

public class Tutorial18 extends EtyllicaApplet {

	private static final long serialVersionUID = 1L;

	public Tutorial18() {
		super(320, 100);
	}
	
	@Override
	public Application startApplication() {
		initialSetup("../../");
		return new AlphaCollision(w, h);
	}
	
}
