package br.com.abby.loader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import br.com.abby.material.OBJMaterial;
import br.com.abby.vbo.Face;
import br.com.abby.vbo.Group;
import br.com.abby.vbo.VBO;
import br.com.etyllica.util.PathHelper;


/**
 * 
 * @author Oskar
 * (This version was modified by yuripourre to support materials)
 * @license LGPLv3
 *  
 */

public class OBJLoader implements VBOLoader {

	private static final String DEFAULT_GROUP_NAME = "default";
	
	public static final String VERTEX = "v";
	public static final String FACE = "f";
	public static final String GROUP = "g";
	public static final String VERTEX_TEXCOORD = "vt";
	public static final String VERTEX_NORMAL = "vn";
	public static final String OBJECT = "o";
	public static final String MATERIAL_LIB = "mtllib";
	public static final String USE_MATERIAL = "usemtl";
		
	private static final String SEPARATOR = "/";

	public VBO loadModel(URL url) throws FileNotFoundException, IOException {

		String fpath = url.getPath();
		
		String modelFolder = PathHelper.upperDirectory(fpath);
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
		VBO vbo = new VBO();

		List<Group> groups = new ArrayList<Group>();

		Group group = new Group(DEFAULT_GROUP_NAME);

		String line;

		while ((line = reader.readLine()) != null) {

			line = fixLine(line);

			if (line.startsWith(GROUP+" ")) {

				if(!DEFAULT_GROUP_NAME.equals(group.getName())) {
					//Add last group
					groups.add(group);
				} else if(!group.getFaces().isEmpty()) {
					//If group has at least one face 
					groups.add(group);
				}
				group = new Group(line.split(" ")[1]);
				
			} else if (line.startsWith(USE_MATERIAL+" ")) {

				String materialName = line.split(" ")[1];
				group.setMaterial(vbo.getMaterials().get(materialName));
				
			} else if (line.startsWith(VERTEX+" ")) {
				parseVertex(line, vbo);                

			} else if (line.startsWith(VERTEX_NORMAL+" ")) {
				parseVertexNormal(line, vbo);

			} else if (line.startsWith(VERTEX_TEXCOORD+" ")) {
				parseVertexTexture(line, vbo);

			} else if (line.startsWith(FACE+" ")) {
				parseFace(vbo, group, line);

			} else if (line.startsWith(MATERIAL_LIB)) {
				parseMaterial(modelFolder, vbo, line);
			}
		}

		reader.close();

		//Add group to Model
		if(group!=null) {
			groups.add(group);
		}

		vbo.setGroups(groups);

		return vbo;
	}

	private void parseMaterial(String modelFolder, VBO vbo, String line)
			throws IOException {
		List<OBJMaterial> materials = parseMaterial(modelFolder, line);

		for(OBJMaterial material: materials) {
			vbo.getMaterials().put(material.getName(), material);
		}
	}

	private void parseFace(VBO vbo, Group group, String line) {
		String[] splitLine = line.substring(2).split(" ");

		int sides = splitLine.length;

		int[] vIndexes = new int[sides];
		int[] nIndexes = new int[sides];
		int[] texIndexes = new int[sides];

		for(int i=0;i<sides;i++) {

			String[] face = splitLine[i].split(SEPARATOR);

			if(face.length > 1) {

				//vIndexes starts in 0
				//Faces starts in 1
				vIndexes[i] = Integer.parseInt(face[0])-1;

				if(!face[1].isEmpty()) {
					texIndexes[i] = Integer.parseInt(face[1])-1;
				}

				if(face.length > 2) {
					nIndexes[i] = Integer.parseInt(face[2])-1;
				}

			} else {
				//Save only the vertexes indexes
				vIndexes[i] = Integer.parseInt(splitLine[i])-1;
			}
		}

		Face face = new Face(vIndexes, texIndexes, nIndexes);
		face.setSides(sides);

		group.getFaces().add(face);
		//TODO Remove face from model
		vbo.getFaces().add(face);
	}

	private String fixLine(String line) {
		return line.replaceAll("  "," ");
	}

	private static void parseVertex(String line, VBO vbo) {

		String[] parts = line.split(" ");

		float x = Float.valueOf(parts[1]);
		float y = Float.valueOf(parts[2]);
		float z = Float.valueOf(parts[3]);
		
		vbo.addVertex(new Vector3f(x, y, z));
	}

	private static void parseVertexNormal(String line, VBO vbo) {

		String[] parts = line.split(" ");

		float x = Float.valueOf(parts[1]);
		float y = Float.valueOf(parts[2]);
		float z = Float.valueOf(parts[3]);
		
		vbo.getNormals().add(new Vector3f(x, y, z));    	
	}

	private static void parseVertexTexture(String line, VBO vbo) {

		String[] parts = line.split(" ");

		float x = Float.valueOf(parts[1]);
		float y = Float.valueOf(parts[2]);

		vbo.getTextures().add(new Vector2f(x, y));     	
	}

	private static List<OBJMaterial> parseMaterial(String folder, String line) throws IOException {

		String filename = line.split(" ")[1];

		List<OBJMaterial> materials = MaterialLoader.loadMaterial(folder,filename);

		return materials;    	
	}

}
