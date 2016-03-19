package br.com.abby.core.vbo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.abby.core.material.OBJMaterial;
import br.com.abby.linear.BoundingBox3D;
import br.com.etyllica.core.linear.Point3D;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class VBO {
	
	private String name;
	
	private BoundingBox3D boundingBox = new BoundingBox3D();
	
	//Original vertices positions
	private List<Vector3> vertices = new ArrayList<Vector3>();
	private List<Vector3> normals = new ArrayList<Vector3>();
	private List<Vector2> textures = new ArrayList<Vector2>();

	private List<Face> faces = new ArrayList<Face>();
	private List<Group> groups = new ArrayList<Group>();
	
	private Map<String, OBJMaterial> materials = new HashMap<String, OBJMaterial>();

	public VBO() {
		super();
	}

	public List<Vector3> getVertices() {
		return vertices;
	}
	
	public void addVertex(Vector3 vertex) {
		vertices.add(vertex);
		boundingBox.add(vertex);
	}

	public List<Vector3> getNormals() {
		return normals;
	}

	public void setNormals(List<Vector3> normals) {
		this.normals = normals;
	}

	public List<Vector2> getTextures() {
		return textures;
	}

	public void setTextures(List<Vector2> textures) {
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
			Vector3 vertice = vertices.get(i);
			cx += vertice.x;
			cy += vertice.y;
			cz += vertice.z;
		}
		
		int n = face.vertexIndex.length;
		
		return new Point3D(cx/n, cy/n, cz/n);
	}
	
}
