package examples.etyllica.tutorial13;

import br.com.etyllica.Etyllica;

public class Tutorial13 extends Etyllica {

	private static final long serialVersionUID = 1L;

	public Tutorial13() {
		super(800, 600);
	}
	
	@Override
	public void startGame() {
		
		setMainApplication(new ComponentsList(w,h));
	}
	
}
