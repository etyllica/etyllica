package examples.jgl.bezcurve;

import br.com.etyllica.EtyllicaApplet;
import br.com.etyllica.core.context.Application;

public class TutorialJGL1 extends EtyllicaApplet {

	private static final long serialVersionUID = 1L;

	public TutorialJGL1() {
		super(640, 480);
	}
	
	@Override
	public Application startApplication() {
		
		return new BezCurve(w,h);
		
	}
	
}
