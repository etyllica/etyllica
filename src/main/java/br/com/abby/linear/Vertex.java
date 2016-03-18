package br.com.abby.linear;

import com.badlogic.gdx.math.Vector3;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Vertex{

	private Vector3 vector;
	
	private float weight = 0;
	
	public Vertex(float x, float y, float z){
		super();
		vector = new Vector3(x,y,z);
	}
	
	public Vertex(Vector3 vector) {
		super();
		this.vector = vector;
	}

	public Vector3 getVector() {
		return vector;
	}

	public void setVector(Vector3 vector) {
		this.vector = vector;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}
	
}
