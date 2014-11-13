package examples.jgl.bezmesh;

import br.com.etyllica.Etyllica;
import br.com.etyllica.context.Application;

public class TutorialJGL2 extends Etyllica {

	private static final long serialVersionUID = 1L;

	public TutorialJGL2() {
		super(640, 480);
	}
	
	@Override
	public Application startApplication() {
		
		return new BezMesh(w,h);
		
	}
	
}
