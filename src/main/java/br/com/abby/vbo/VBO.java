package br.com.abby.vbo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import br.com.abby.linear.BoundingBox3D;
import br.com.abby.material.OBJMaterial;
import br.com.etyllica.linear.Point3D;

public class VBO {
	
	private String name;
	
	private BoundingBox3D boundingBox = new BoundingBox3D();
	
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
	
	public void addVertex(Vector3f vertex) {
		vertices.add(vertex);
		boundingBox.add(vertex);
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

	public BoundingBox3D getBoundingBox() {
		return boundingBox;
	}

	public Point3D centroid(Face face) {
		float cx = 0, cy = 0, cz = 0;
		
		for(int i:face.vertexIndex) {
			Vector3f vertice = vertices.get(i);
			cx += vertice.getX();
			cy += vertice.getY();
			cz += vertice.getZ();
		}
		
		int n = face.vertexIndex.length;
		
		return new Point3D(cx/n, cy/n, cz/n);
	}
	
}
