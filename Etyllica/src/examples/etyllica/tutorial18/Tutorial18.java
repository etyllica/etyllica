package examples.etyllica.tutorial18;

import br.com.etyllica.Etyllica;
import br.com.etyllica.context.Application;

public class Tutorial18 extends Etyllica {

	private static final long serialVersionUID = 1L;

	public Tutorial18() {
		super(320, 100);
	}
	
	@Override
	public Application startApplication() {
		initialSetup("../../../../");
		return new AlphaCollision(w, h);
	}
	
}
