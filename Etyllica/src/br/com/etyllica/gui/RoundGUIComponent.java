package br.com.etyllica.gui;

public abstract class RoundGUIComponent extends View{

	public RoundGUIComponent(float x, float y, float w, float h){
		super(x,y,w,h);
	}
	
	protected int roundness;
	
	public int getRoundness() {
		return roundness;
	}

	public void setRoundness(int roundness) {
		this.roundness = roundness;
	}	
	
}
