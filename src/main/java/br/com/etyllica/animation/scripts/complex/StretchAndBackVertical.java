package br.com.etyllica.animation.scripts.complex;

import br.com.etyllica.animation.scripts.StretchVerticalAnimation;
import br.com.etyllica.layer.Layer;

public class StretchAndBackVertical extends StretchVerticalAnimation {

	private StretchVerticalAnimation backToNormal;

	public StretchAndBackVertical(long time) {
		super(time);
		init();
	}

	public StretchAndBackVertical(long delay, long time) {
		super(delay, time);
		init();
	}

	public StretchAndBackVertical(Layer target, long time) {
		super(target, time);
		init();
	}

	private void init() {
		backToNormal = new StretchVerticalAnimation(target, time);
		backToNormal.setInterval(endValue, startValue);
		setNext(backToNormal);
		backToNormal.setNext(this);
	}

	@Override
	public void setInterval(double startValue, double endValue) {
		super.setInterval(startValue, endValue);
		backToNormal.setInterval(endValue, startValue);
	}

	@Override
	public void finish(long now) {
		double offset = ((target.getH()/2)*endValue);
		target.setY((int)(originalY-offset));
		super.finish(now);
	}

}
