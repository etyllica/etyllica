package br.com.abby.vbo;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

/**
 * @author Based on Oskar's code
 */

public class Face {
	
	//TODO ALL Private
	private int sides = 4;
	
    public int[] vertexIndex; // three indices, not vertices or normals!
    public Vector3f[] normal;
    public Vector2f[] texture;

    public Face(int sides) {
    	this.sides = sides;
    }
    
    public Face(int[] vertexIndex, Vector2f[] texture, Vector3f[] normal) {
    	this.vertexIndex = vertexIndex;
    	this.texture = texture;
    	this.normal = normal;
    }

	public int getSides() {
		return sides;
	}

	public void setSides(int sides) {
		this.sides = sides;
	}
         
}