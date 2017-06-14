package examples.etyllica.tutorial20;

import br.com.etyllica.EtyllicaApplet;
import br.com.etyllica.commons.context.Application;

public class Tutorial20 extends EtyllicaApplet {

	private static final long serialVersionUID = 1L;

	public Tutorial20() {
		super(800, 600);
	}
	
	@Override
	public Application startApplication() {
		initialSetup("../../");
		return new AnimatedDialog(w, h);
	}
	
}
