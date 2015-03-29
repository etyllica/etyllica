package br.com.abby.vbo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import br.com.abby.material.OBJMaterial;

public class VBO {
	
	private String name;
	
	//Original vertices positions
	private List<Vector3f> vertices = new ArrayList<Vector3f>();
	private List<Vector3f> normals = new ArrayList<Vector3f>();
	private List<Vector2f> textures = new ArrayList<Vector2f>();

	private List<Face> faces = new ArrayList<Face>();

	private List<Group> groups = new ArrayList<Group>();
	
	private Map<String, OBJMaterial> materials = new HashMap<String, OBJMaterial>();

	public VBO() {
		super();
	}

	public List<Vector3f> getVertices() {
		return vertices;
	}

	public void setVertices(List<Vector3f> vertices) {
		this.vertices = vertices;
	}

	public List<Vector3f> getNormals() {
		return normals;
	}

	public void setNormals(List<Vector3f> normals) {
		this.normals = normals;
	}

	public List<Vector2f> getTextures() {
		return textures;
	}

	public void setTextures(List<Vector2f> textures) {
		this.textures = textures;
	}

	public List<Face> getFaces() {
		return faces;
	}

	public void setFaces(List<Face> faces) {
		this.faces = faces;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public Map<String, OBJMaterial> getMaterials() {
		return materials;
	}

	public void setMaterials(Map<String, OBJMaterial> materials) {
		this.materials = materials;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
		
}
