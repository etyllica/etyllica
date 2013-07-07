package examples.etyllica.tutorial4;

import br.com.etyllica.Etyllica;
import examples.etyllica.tutorial4.application.ProceduralColorChange;

public class Tutorial4 extends Etyllica {

	private static final long serialVersionUID = 1L;

	public Tutorial4() {
		super(640, 480);
	}
	
	@Override
	public void startGame() {

		//Upping 3 file levels
		String s = getClass().getResource("").toString();
		setPath(s+"../../../");
		
		
		setMainApplication(new ProceduralColorChange(w,h));
	}
	
}
