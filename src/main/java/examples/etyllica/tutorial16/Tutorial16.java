package examples.etyllica.tutorial16;

import br.com.etyllica.Etyllica;
import br.com.etyllica.context.Application;

public class Tutorial16 extends Etyllica {

	private static final long serialVersionUID = 1L;

	public Tutorial16() {
		super(800, 600);
	}
	
	@Override
	public Application startApplication() {
		initialSetup("../../../../../");
		return new ParticleApplication(w,h);
	}
	
}