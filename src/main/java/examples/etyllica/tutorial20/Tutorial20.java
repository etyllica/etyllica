package examples.etyllica.tutorial20;

import br.com.etyllica.Etyllica;
import br.com.etyllica.context.Application;

public class Tutorial20 extends Etyllica {

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
