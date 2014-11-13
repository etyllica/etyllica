package examples.etyllica.tutorial17;

import br.com.etyllica.Etyllica;
import br.com.etyllica.context.Application;

public class Tutorial17 extends Etyllica {

	private static final long serialVersionUID = 1L;

	public Tutorial17() {
		super(800, 600);
	}
	
	@Override
	public Application startApplication() {
		initialSetup("../../../../../");
		return new TimedApplication(w,h);
	}
	
}
