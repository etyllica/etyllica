package examples.etyllica.tutorial08;

import br.com.etyllica.Etyllica;
import examples.etyllica.tutorial08.application.AnimatedMenu;
import br.com.etyllica.EtyllicaApplet;
import br.com.etyllica.commons.context.Application;

import java.awt.*;

public class Tutorial8 extends Etyllica {

	private static final long serialVersionUID = 1L;

	public Tutorial8() {
		super(800, 600);
	}

	public static void main(String[] args) {
		Tutorial8 app = new Tutorial8();
		app.init();
	}

	@Override
	public Application startApplication() {
		initialSetup("");
		return new AnimatedMenu(w, h);
	}
	
}
