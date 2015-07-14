package examples.etyllica.tutorial03;

import br.com.etyllica.core.animation.script.AnimationScript;
import br.com.etyllica.core.animation.script.OrbitAnimation;
import br.com.etyllica.core.animation.script.RotateAnimation;
import br.com.etyllica.core.context.Application;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.layer.ImageLayer;

public class StriderAnimation extends Application {

	public StriderAnimation(int w, int h) {
		super(w, h);
	}
	
	private ImageLayer background;
	
	private ImageLayer machine;
	
	private ImageLayer anotherMachine;

	@Override
	public void load() {
		
		loadingInfo = "Loading images...";
		
		background = new ImageLayer("scene.png");
		machine = new ImageLayer(180,270,"machine.png");
		anotherMachine = new ImageLayer(180,270,"machine.png");
		
		createAnimationScripts(machine, 0);
		
		createAnimationScripts(anotherMachine, 180);
				
		loading = 50;
		loadingInfo = "Loading animations...";		
				
	}
	
	private void createAnimationScripts(ImageLayer machine, int offsetAngle) {
		
		OrbitAnimation orbitAnimation = new OrbitAnimation(0,5000);
		orbitAnimation.setTarget(machine);
		orbitAnimation.setCenter(machine.getX(), machine.getY()+70);
		orbitAnimation.setInterval(offsetAngle, 360+offsetAngle);
		orbitAnimation.setRepeat(AnimationScript.REPEAT_FOREVER);
		this.scene.addAnimation(orbitAnimation);
		
		RotateAnimation rotateAnimation = new RotateAnimation(0,5000);
		rotateAnimation.setTarget(machine);
		rotateAnimation.setInterval(offsetAngle, 360+offsetAngle);
		rotateAnimation.setRepeat(AnimationScript.REPEAT_FOREVER);
		this.scene.addAnimation(rotateAnimation);
		
	}

	@Override
	public void draw(Graphic g) {
		background.draw(g);
		machine.draw(g);
		anotherMachine.draw(g);
	}
	
}
