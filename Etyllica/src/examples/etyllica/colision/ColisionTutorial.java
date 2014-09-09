package examples.etyllica.colision;

import br.com.etyllica.EtyllicaFrame;
import br.com.etyllica.context.Application;

public class ColisionTutorial extends EtyllicaFrame {

	private static final long serialVersionUID = 1L;

	public ColisionTutorial(int width, int height) {
		super(width, height);
	}
	
	public static void main(String[] args) {
		ColisionTutorial colision = new ColisionTutorial(800, 600);
		colision.init();
	}

	@Override
	public Application startApplication() {
		initialSetup("../../../../");
		
		return new ColisionElements(w, h);
	}

}
