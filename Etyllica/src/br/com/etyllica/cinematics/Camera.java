package br.com.etyllica.cinematics;

import java.awt.geom.AffineTransform;

public class Camera {

	private float w = 0;
	private float h = 0;
	
	private float x = 0;
	private float y = 0;
	
	private float zoomX = 1;
	private float zoomY = 3;
	
	private float angle = 0;
	
	private float skewX = 0;
	private float skewY = 0;
	
	public AffineTransform getTransform(){
		
		AffineTransform transform = AffineTransform.getScaleInstance(zoomX, zoomY);
				
		if(x!=0||y!=0){
			transform.concatenate(AffineTransform.getTranslateInstance(x, y));
		}

		return transform;
	}
	
}
