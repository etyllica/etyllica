package examples.etyllica.triangulation;
import br.com.etyllica.Etyllica;
import br.com.etyllica.core.context.Application;
import examples.etyllica.procedural.application.DiceApplication;


public class TriangulationExample extends Etyllica {
	private static final long serialVersionUID = 1L;
	
	public TriangulationExample() {
		super(800, 600);
	}

	public static void main(String[] args) {
		TriangulationExample app = new TriangulationExample();
		app.init();
	}

	@Override
	public Application startApplication() {
		return new TriangulationApplication(w,h);
	}

}
