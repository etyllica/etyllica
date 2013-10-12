package br.com.luvia.vbo;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

/**
 * @author Based on Oskar's code
 */

public class Face {
	
	//TODO ALL Private
	private int quad = 4;
	
    public Vector3f[] vertex; // three indices, not vertices or normals!
    public Vector3f[] normal;
    public Vector2f[] texture;

    public Face(int quad) {
    	this.quad = quad;
    }
    
    public Face(Vector3f[] vertex, Vector2f[] texture, Vector3f[] normal) {
    	this.vertex = vertex;
    	this.texture = texture;
    	this.normal = normal;
    }

	public int getQuad() {
		return quad;
	}

	public void setQuad(int quad) {
		this.quad = quad;
	}
         
}