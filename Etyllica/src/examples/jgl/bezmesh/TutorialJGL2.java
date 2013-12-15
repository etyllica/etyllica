package examples.jgl.bezmesh;

import br.com.etyllica.Etyllica;

public class TutorialJGL2 extends Etyllica {

	private static final long serialVersionUID = 1L;

	public TutorialJGL2() {
		super(640, 480);
	}
	
	@Override
	public void startGame() {
		
		setMainApplication(new BezMesh(w,h));
		
	}
	
}
