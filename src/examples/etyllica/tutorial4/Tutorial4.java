package examples.etyllica.tutorial4;

import br.com.etyllica.Etyllica;
import examples.etyllica.tutorial4.application.SubWindowExample;

public class Tutorial4 extends Etyllica {

	private static final long serialVersionUID = 1L;

	public Tutorial4() {
		super(640, 480);
	}
	
	@Override
	public void startGame() {
		
		setMainApplication(new SubWindowExample(w,h));
	}
	
}
