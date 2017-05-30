package br.com.etyllica.core;

import java.awt.Graphics;

import br.com.etyllica.core.context.Application;
import br.com.etyllica.core.engine.EtyllicaFrame;

public interface Core {
	void initMonitors(int width, int height);
	void moveToCenter();
	void setEngine(EtyllicaFrame frame);
	void startEngine();
	void startCore(Application application);
	void setPath(String path);
	void paint(Graphics g);
	String getPath();

	void update(double delta) throws Exception;
	void render();
	boolean isRunning();
	void setFps(int fps);
}
