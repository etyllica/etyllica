package examples.etyllica.tutorial22;

import br.com.etyllica.Etyllica;
import br.com.etyllica.context.Application;

public class Tutorial22 extends Etyllica {

	private static final long serialVersionUID = 1L;

	public Tutorial22() {
		super(800, 200);
	}
	
	@Override
	public Application startApplication() {
		initialSetup("../../../../");
		return new ParallaxApplication(w, h);
	}
	
}
