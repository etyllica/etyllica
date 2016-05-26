package examples.etyllica.tutorial01;

import br.com.etyllica.EtyllicaApplet;
import br.com.etyllica.core.context.Application;

public class Tutorial1 extends EtyllicaApplet {

	private static final long serialVersionUID = 1L;

	public Tutorial1() {
		super(800, 600);
	}

	@Override
	public Application startApplication() {
		initialSetup("../../");
		return new HelloWorld(w,h);
	}

}
