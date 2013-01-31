package examples.tutorial1;

import br.com.etyllica.Etyllica;
import examples.tutorial1.application.HelloWorld;

public class Tutorial1 extends Etyllica {

	private static final long serialVersionUID = 1L;

	public Tutorial1() {
		super(640, 480);
	}

	@Override
	public void startGame() {
		
		setMainApplicacao(new HelloWorld());
	}
	
}
