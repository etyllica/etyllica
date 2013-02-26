package examples.etyllica.tutorial1;

import br.com.etyllica.Etyllica;
import examples.etyllica.tutorial1.application.HelloWorld;

public class Tutorial1 extends Etyllica {

	private static final long serialVersionUID = 1L;

	public Tutorial1() {
		super(640, 480);
	}
	
	@Override
	public void startGame() {
		
		setMainApplication(new HelloWorld());
	}
	
}
