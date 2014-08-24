package examples.etyllica.tutorial21;

import br.com.etyllica.Etyllica;
import br.com.etyllica.context.Application;

public class Tutorial21 extends Etyllica {

	private static final long serialVersionUID = 1L;

	public Tutorial21() {
		super(800, 600);
	}
	
	@Override
	public Application startApplication() {
				
		return new ResizerApplication(w,h);
	}
	
}
