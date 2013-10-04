package examples.etyllica.tutorial04;

import br.com.etyllica.Etyllica;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Tutorial4 extends Etyllica {

	private static final long serialVersionUID = 1L;

	public Tutorial4() {
		super(640, 480);
	}
	
	@Override
	public void startGame() {

		//Three file levels up
		String s = getClass().getResource("").toString();
		setPath(s+"../../../");
		
		hideCursor();
		
		setMainApplication(new ProceduralColorChange(w,h));
	}
	
}
