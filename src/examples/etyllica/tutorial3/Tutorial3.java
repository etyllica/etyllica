package examples.etyllica.tutorial3;

import br.com.etyllica.Etyllica;
import examples.etyllica.tutorial3.application.ChatWindowExample;

public class Tutorial3 extends Etyllica {

	private static final long serialVersionUID = 1L;

	public Tutorial3() {
		super(640, 480);
	}
	
	@Override
	public void startGame() {
		
		setMainApplication(new ChatWindowExample(w,h));
	}
	
}
