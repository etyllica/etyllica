package org.dyn4j;

import java.awt.geom.AffineTransform;

import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.BodyFixture;

import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.layer.Layer;

/**
 * 
 * Envelope of Layers to use physics
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class RigidBody extends Body{
	
	private Layer layer;
	
	public RigidBody(Layer layer){
		super();
		this.layer = layer;
		
		addFixture(new BodyFixture(new org.dyn4j.geometry.Rectangle(layer.getW()*layer.getScale(), layer.getH()*layer.getScale())));
		translate(layer.getX()+(layer.getW()*layer.getScale())/2, layer.getY()+(layer.getH()*layer.getScale())/2);
		setMass();
		
	}
	
	public void draw(Graphic g){
		
		AffineTransform transform = new AffineTransform();
		
		float initialX = layer.getX()+layer.getW()/2;
		float initialY = layer.getY()+layer.getH()/2;
						
		transform.translate(this.transform.getTranslationX()-initialX, this.transform.getTranslationY()-initialY);
		transform.concatenate(AffineTransform.getRotateInstance(this.transform.getRotation(),layer.getX()+layer.getW()/2, layer.getY()+layer.getH()/2));
		
		this.layer.draw(g, transform);
		
		g.resetTransform();
	}
	
	
	

	public Layer getLayer() {
		return layer;
	}
		
}
