package examples.etyllica.tutorial14;

import br.com.etyllica.Etyllica;
import br.com.etyllica.context.Application;

public class Tutorial14 extends Etyllica {

	private static final long serialVersionUID = 1L;

	public Tutorial14() {
		super(800, 600);
	}
	
	@Override
	public Application startApplication() {
		
		String s = getClass().getResource("").toString();
		setPath(s+"../../../");
		
		return new AnimationTutorial(w,h);
	}
	
}
