package br.com.abby.vbo;

import java.util.ArrayList;
import java.util.List;

import br.com.abby.material.OBJMaterial;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Group {
	
	private String name;
	
	private List<Face> faces = new ArrayList<Face>();
	
	private OBJMaterial material;
	
	public Group(String name){
		super();
		
		this.name = name;
	}

	public List<Face> getFaces() {
		return faces;
	}

	public void setFaces(List<Face> faces) {
		this.faces = faces;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public OBJMaterial getMaterial() {
		return material;
	}

	public void setMaterial(OBJMaterial material) {
		this.material = material;
	}
		
}
