package examples.etyllica.procedural;
import br.com.etyllica.Etyllica;
import br.com.etyllica.commons.context.Application;
import examples.etyllica.procedural.application.DiceApplication;


public class DiceExample extends Etyllica {
	private static final long serialVersionUID = 1L;
	
	public DiceExample() {
		super(800, 600);
	}

	public static void main(String[] args) {
		DiceExample app = new DiceExample();
		app.init();
	}

	@Override
	public Application startApplication() {
		return new DiceApplication(w,h);
	}

}
