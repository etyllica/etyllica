package br.com.etyllica.gui;

public abstract class RoundGUIComponent extends GUIComponent{

	public RoundGUIComponent(int x, int y, int w, int h){
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
