package examples.etyllica.tutorial13;

import br.com.etyllica.Etyllica;
import br.com.etyllica.context.Application;

public class Tutorial13 extends Etyllica {

	private static final long serialVersionUID = 1L;

	public Tutorial13() {
		super(800, 600);
	}
	
	@Override
	public Application startApplication() {
		
		return new ComponentsList(w,h);
	}
	
}
