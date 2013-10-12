package examples.etyllica.tutorial14;

import br.com.etyllica.Etyllica;

public class Tutorial14 extends Etyllica {

	private static final long serialVersionUID = 1L;

	public Tutorial14() {
		super(800, 600);
	}
	
	@Override
	public void startGame() {
		
		String s = getClass().getResource("").toString();
		setPath(s+"../../../");
		
		setMainApplication(new AnimationTutorial(w,h));
	}
	
}
