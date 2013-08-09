package examples.etyllica.tutorial05;

import br.com.etyllica.Etyllica;
import examples.etyllica.tutorial05.application.FollowingText;

public class Tutorial5 extends Etyllica {

	private static final long serialVersionUID = 1L;

	public Tutorial5() {
		super(640, 480);
	}
	
	@Override
	public void startGame() {
		
		setMainApplication(new FollowingText(w,h));
	}
	
}
