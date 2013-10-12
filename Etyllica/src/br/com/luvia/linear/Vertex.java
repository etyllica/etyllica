package br.com.luvia.linear;

import org.lwjgl.util.vector.Vector3f;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Vertex{

	private Vector3f vector;
	
	private float weight = 0;
	
	public Vertex(float x, float y, float z){
		super();
		vector = new Vector3f(x,y,z);
	}
	
	public Vertex(Vector3f vector){
		super();
		this.vector = vector;
	}

	public Vector3f getVector() {
		return vector;
	}

	public void setVector(Vector3f vector) {
		this.vector = vector;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}
	
}
