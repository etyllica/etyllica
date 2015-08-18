package examples.etyllica.animation;
import br.com.etyllica.EtyllicaFrame;
import br.com.etyllica.core.context.Application;

public class AnimationApp extends EtyllicaFrame {

	private static final long serialVersionUID = 1L;

	public AnimationApp() {
		super(800, 600);
	}

	public static void main(String[] args){
		
		AnimationApp viewer = new AnimationApp();
		
		viewer.init();
	}	
	
	@Override
	public Application startApplication() {
		initialSetup("../../../../../");

		return new AnimationExample(w,h);
	}

}

