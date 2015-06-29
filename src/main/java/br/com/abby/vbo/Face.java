package br.com.abby.vbo;


/**
 * @author Based on Oskar's code
 */

public class Face {
	
	private int sides = 4;
	
    public int[] vertexIndex; // Vertex indices
    public int[] normalIndex; // Normal indexes
    public int[] textureIndex; // Texture coordinate indexes

    public Face(int sides) {
    	this.sides = sides;
    }
    
    public Face(int[] vertexIndex, int[] textureIndex, int[] normalIndex) {
    	this.vertexIndex = vertexIndex;
    	this.textureIndex = textureIndex;
    	this.normalIndex = normalIndex;
    }
    
    public Face addVertexes(int ... vertexIndex) {
    	this.vertexIndex = vertexIndex;
    	return this;
    }
    
    public Face addTextures(int ... textureIndex) {
    	this.textureIndex = textureIndex;
    	return this;
    }
    
    public Face addNormals(int ... normalIndex) {
    	this.normalIndex = normalIndex;
    	return this;
    }

	public int getSides() {
		return sides;
	}

	public void setSides(int sides) {
		this.sides = sides;
	}
    
}