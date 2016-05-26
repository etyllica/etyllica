package examples.etyllica.animation;
import br.com.etyllica.Etyllica;
import br.com.etyllica.core.context.Application;
import examples.etyllica.animation.tween.*;

public class TweenApplication extends Etyllica {

	private static final long serialVersionUID = 1L;

	public TweenApplication() {
		super(800, 600);
	}

	public static void main(String[] args){
		TweenApplication viewer = new TweenApplication();
		viewer.init();
	}	
	
	@Override
	public Application startApplication() {
		initialSetup("../");

		return new TweenBallExample(w,h);
		//return new HelloWorldAnimated(w,h);
		//return new StriderAnimation(w,h);
	}

}

