package examples.etyllica.tutorial01;

import br.com.etyllica.Etyllica;
import br.com.etyllica.core.context.Application;

public class Tutorial1 extends Etyllica {

	private static final long serialVersionUID = 1L;

	public Tutorial1() {
		super(800, 600);
	}

	public static void main(String[] args){
		Tutorial1 app = new Tutorial1();
		app.init();
	}	
	
	@Override
	public Application startApplication() {
		initialSetup("../");
		return new MoveImage(w,h);
	}

}
