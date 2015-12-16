package examples.etyllica.animation;
import br.com.etyllica.EtyllicaFrame;
import br.com.etyllica.core.context.Application;
import examples.etyllica.animation.pivot.PivotExample;

public class PivotApplication extends EtyllicaFrame {

	private static final long serialVersionUID = 1L;

	public PivotApplication() {
		super(800, 600);
	}

	public static void main(String[] args){
		PivotApplication viewer = new PivotApplication();
		viewer.init();
	}	
	
	@Override
	public Application startApplication() {
		initialSetup("../");

		return new PivotExample(w,h);
	}

}

