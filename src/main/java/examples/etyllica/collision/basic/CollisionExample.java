package examples.etyllica.collision.basic;

import br.com.etyllica.Etyllica;
import br.com.etyllica.commons.context.Application;

public class CollisionExample extends Etyllica {

	private static final long serialVersionUID = 1L;

	public CollisionExample(int width, int height) {
		super(width, height);
	}
	
	public static void main(String[] args) {
		CollisionExample colision = new CollisionExample(800, 600);
		colision.init();
	}

	@Override
	public Application startApplication() {
		initialSetup("../../../../../");
		
		return new CollisionElements(w, h);
	}

}
