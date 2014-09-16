package examples.etyllica.tutorial19;

import br.com.etyllica.Etyllica;
import br.com.etyllica.context.Application;

public class Tutorial19 extends Etyllica {

	private static final long serialVersionUID = 1L;

	public Tutorial19() {
		super(800, 600);
	}
	
	@Override
	public Application startApplication() {
		initialSetup("../../../../");		
		return new AnimatedMenu(w, h);
	}
	
}
