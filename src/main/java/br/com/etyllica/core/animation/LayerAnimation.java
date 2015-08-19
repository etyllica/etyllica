package br.com.etyllica.core.animation;

import br.com.etyllica.core.animation.script.AnimationScript;
import br.com.etyllica.core.animation.script.MovementScript;
import br.com.etyllica.core.interpolation.Interpolator;
import br.com.etyllica.layer.ImageLayer;
import br.com.etyllica.layer.Layer;

public class LayerAnimation extends AnimationScript {

	protected Layer target;
	
	public LayerAnimation(long time) {
		super(time);
	}
	
	public LayerAnimation(Layer target) {
		super();
		this.target = target;
	}
	
	public LayerAnimation(Layer target, long time) {
		super();
		this.target = target;
		this.duration = time;
	}

	public LayerAnimation(long delay, long time) {
		super(delay, time);
	}
	
	public LayerAnimation(ImageLayer layer) {
		super();
		this.target = layer;
	}

	public Layer getLayer() {
		return target;
	}

	public void setLayer(ImageLayer layer) {
		this.target = layer;
	}
	

	public Layer getTarget() {
		return target;
	}

	public void setTarget(Layer target) {
		this.target = target;
	}

	@Override
	public void calculate(double factor) {
		// TODO Auto-generated method stub
	}
	
	public LayerAnimation startAt(long delayValue) {
		this.delay = delayValue;
		return this;
	}
	
	public LayerAnimation duration(long time) {
		this.duration = time;
		return this;
	}
	
	public LayerAnimation interpolator(Interpolator interpolator) {
		this.interpolator = interpolator;
		return this;
	}
	
	public MovementScript move(long time) {
		MovementScript script = new MovementScript(target, time);
		AnimationHandler.getInstance().add(script);
		
		return script;
	}
	
	public MovementScript move() {
		MovementScript script = new MovementScript(target);
		AnimationHandler.getInstance().add(script);
		
		return script;
	}

	public LayerAnimation then(LayerAnimation script) {
		setNext(script);
		return this;
	}
}
