package examples.etyllica.animation.pivot;
import br.com.etyllica.Etyllica;
import br.com.etyllica.commons.context.Application;

public class PivotExample extends Etyllica {

	private static final long serialVersionUID = 1L;

	public PivotExample() {
		super(800, 600);
	}

	public static void main(String[] args){
		PivotExample viewer = new PivotExample();
		viewer.init();
	}	
	
	@Override
	public Application startApplication() {
		initialSetup("../");

		return new PivotApplication(w,h);
	}

}

