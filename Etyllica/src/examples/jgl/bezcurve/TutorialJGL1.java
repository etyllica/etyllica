package examples.jgl.bezcurve;

import br.com.etyllica.Etyllica;

public class TutorialJGL1 extends Etyllica {

	private static final long serialVersionUID = 1L;

	public TutorialJGL1() {
		super(640, 480);
	}
	
	@Override
	public void startGame() {
		
		setMainApplication(new BezCurve(w,h));
		
	}
	
}
