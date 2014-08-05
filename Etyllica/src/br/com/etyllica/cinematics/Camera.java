package br.com.etyllica.cinematics;

import java.awt.geom.AffineTransform;

import br.com.etyllica.layer.GeometricLayer;

public class Camera extends GeometricLayer {
	
	private float zoomX = 1;
	private float zoomY = 1;
		
	private float skewX = 0;
	private float skewY = 0;
	
	public Camera(int x, int y, int w, int h) {
		super(x, y, w, h);
	}	
	
	public AffineTransform getTransform() {
		
		AffineTransform transform = AffineTransform.getScaleInstance(zoomX, zoomY);
				
		if(x != 0 || y != 0) {
			transform.concatenate(AffineTransform.getTranslateInstance(-x, -y));
		}

		return transform;
	}
	
}
