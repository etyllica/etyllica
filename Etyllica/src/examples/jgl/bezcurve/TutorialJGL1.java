package examples.jgl.bezcurve;

import br.com.etyllica.Etyllica;
import br.com.etyllica.context.Application;

public class TutorialJGL1 extends Etyllica {

	private static final long serialVersionUID = 1L;

	public TutorialJGL1() {
		super(640, 480);
	}
	
	@Override
	public Application startApplication() {
		
		return new BezCurve(w,h);
		
	}
	
}
