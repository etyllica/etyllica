package examples.etyllica.tutorial19;

import br.com.etyllica.EtyllicaApplet;
import br.com.etyllica.commons.context.Application;

public class Tutorial23 extends EtyllicaApplet {

	private static final long serialVersionUID = 1L;

	public Tutorial23() {
		super(800, 300);
	}
	
	@Override
	public Application startApplication() {
		initialSetup("../../");
		return new HexagonalApplication(w, h);
	}
	
}
