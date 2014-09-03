package examples.etyllica.tutorial14;

import br.com.etyllica.Etyllica;
import br.com.etyllica.context.Application;

public class Tutorial14 extends Etyllica {

	private static final long serialVersionUID = 1L;

	public Tutorial14() {
		super(80, 60);
	}
	
	@Override
	public Application startApplication() {
				
		return new SpriteAnimationTutorial(w,h);
	}
	
}
