package examples.etyllica.tutorial23;

import br.com.etyllica.Etyllica;
import br.com.etyllica.context.Application;

public class Tutorial23 extends Etyllica {

	private static final long serialVersionUID = 1L;

	public Tutorial23() {
		super(800, 300);
	}
	
	@Override
	public Application startApplication() {
		initialSetup("../../../../");
		return new HexagonalApplication(w, h);
	}
	
}
