package br.com.abby.core.loader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import br.com.abby.core.material.OBJMaterial;
import br.com.abby.core.vbo.Face;
import br.com.abby.core.vbo.Group;
import br.com.abby.core.vbo.VBO;
import br.com.etyllica.util.PathHelper;


/**
 * 
 * @author Oskar
 * (This version was modified by yuripourre to support materials)
 * @license LGPLv3
 *  
 */

public class OBJLoader implements VBOLoader {
	
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

				String groupName = line.split(" ")[1];
				
				if(!DEFAULT_GROUP_NAME.equals(group.getName())) {
					//Add last group
					groups.add(group);
				} else if(!group.getFaces().isEmpty()) {
					//If group has at least one face 
					groups.add(group);
				}
				group = new Group(groupName);
				
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
		
		vbo.addVertex(new Vector3(x, y, z));
	}

	private static void parseVertexNormal(String line, VBO vbo) {

		String[] parts = line.split(" ");

		float x = Float.valueOf(parts[1]);
		float y = Float.valueOf(parts[2]);
		float z = Float.valueOf(parts[3]);
		
		vbo.getNormals().add(new Vector3(x, y, z));    	
	}

	private static void parseVertexTexture(String line, VBO vbo) {

		String[] parts = line.split(" ");

		float x = Float.valueOf(parts[1]);
		float y = Float.valueOf(parts[2]);

		vbo.getTextures().add(new Vector2(x, y));
	}

	private static List<OBJMaterial> parseMaterial(String folder, String line) throws IOException {

		String filename = line.substring(line.indexOf(" ")+1);

		List<OBJMaterial> materials = MaterialLoader.loadMaterial(folder,filename);

		return materials;    	
	}

}
