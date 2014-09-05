package examples.etyllica.tutorial24;

import br.com.etyllica.Etyllica;
import br.com.etyllica.context.Application;

public class Tutorial24 extends Etyllica {

	private static final long serialVersionUID = 1L;

	public Tutorial24() {
		super(800, 600);
	}
	
	@Override
	public Application startApplication() {
				
		return new SplitCameraApplication(w,h);
	}
	
}
