package br.com.etyllica.core.graphics;

import br.com.etyllica.commons.layer.GeometricLayer;

import java.awt.GraphicsDevice;

public class Monitor extends GeometricLayer {

	private GraphicsDevice device;
	
	public Monitor(int x, int y, int w, int h, GraphicsDevice device) {
		super(x,y,w,h);
		this.device = device;
	}

	public GraphicsDevice getDevice() {
		return device;
	}

	public void setDevice(GraphicsDevice device) {
		this.device = device;
	}
		
}
