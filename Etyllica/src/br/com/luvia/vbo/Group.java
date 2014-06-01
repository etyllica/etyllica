package br.com.luvia.vbo;

import java.util.ArrayList;
import java.util.List;

import br.com.luvia.material.DiffuseMaterial;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Group {
	
	private String name;
	
	private List<Face> faces = new ArrayList<Face>();
	
	private DiffuseMaterial material;
	
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

	public DiffuseMaterial getMaterial() {
		return material;
	}

	public void setMaterial(DiffuseMaterial material) {
		this.material = material;
	}
		
}
