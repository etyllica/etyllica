package br.com.etyllica.core;

public interface GameCore {

	public void update(double delta);
	
	public void render();
	
	public boolean isRunning();
	
	public void setFps(int fps);
	
}
