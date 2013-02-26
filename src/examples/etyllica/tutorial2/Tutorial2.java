package examples.etyllica.tutorial2;

import br.com.etyllica.Etyllica;
import examples.etyllica.tutorial2.application.SimpleGuiExample;

public class Tutorial2 extends Etyllica {

	private static final long serialVersionUID = 1L;

	public Tutorial2() {
		super(640, 480);
	}
	
	@Override
	public void startGame() {
		
		setMainApplication(new SimpleGuiExample());
	}
	
}
