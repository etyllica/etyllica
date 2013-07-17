package examples.etyllica.tutorial7;

import br.com.etyllica.Etyllica;
import examples.etyllica.tutorial7.application.AnimationExample;

public class Tutorial7 extends Etyllica {

	private static final long serialVersionUID = 1L;

	public Tutorial7() {
		super(640, 480);
	}
	
	@Override
	public void startGame() {
		
		setMainApplication(new AnimationExample(w,h));
	}
	
}
