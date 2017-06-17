package examples.etyllica.collision.hexagon;

import br.com.etyllica.Etyllica;
import br.com.etyllica.commons.context.Application;

public class HexagonExample extends Etyllica {

	private static final long serialVersionUID = 1L;

	public HexagonExample() {
		super(800, 300);
	}

	public static void main(String[] args) {
		HexagonExample app = new HexagonExample();
		app.init();
	}

	@Override
	public Application startApplication() {
		initialSetup("");
		return new HexagonalApplication(w, h);
	}
	
}
