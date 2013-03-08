package examples.etyllica.tutorial1;

import br.com.etyllica.Etyllica;
import examples.etyllica.tutorial1.application.HelloWorld;

public class Tutorial1 extends Etyllica {

	private static final long serialVersionUID = 1L;

	public Tutorial1() {
		super(800, 600);
	}
	
	@Override
	public void startGame() {
		
		setMainApplication(new HelloWorld(w,h));
	}
	
}
