package examples.etyllica.tutorial08;

import examples.etyllica.tutorial08.application.AnimatedMenu;
import br.com.etyllica.EtyllicaApplet;
import br.com.etyllica.commons.context.Application;

public class Tutorial8 extends EtyllicaApplet {

	private static final long serialVersionUID = 1L;

	public Tutorial8() {
		super(800, 600);
	}
	
	@Override
	public Application startApplication() {
		initialSetup("../../");
		return new AnimatedMenu(w, h);
	}
	
}
