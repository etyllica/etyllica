package br.com.abby.loader;

import java.io.BufferedReader;
import java.io.File;
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


/**
 * 
 * @author Oskar
 * (This version was modified by yuripourre to support materials)
 * @license LGPLv3
 *  
 */

public class OBJLoader implements VBOLoader {

	private static final String DEFAULT_GROUP_NAME = "default";
	
	private static final String VERTEX = "v";
	private static final String FACE = "f";
	private static final String GROUP = "g";
	private static final String VERTEX_TEXCOORD = "vt";
	private static final String VERTEX_NORMAL = "vn";
	private static final String OBJECT = "o";
	private static final String MATERIAL_LIB = "mtllib";
	private static final String USE_MATERIAL = "usemtl";
		
	private static final String SEPARATOR = "/";

	public VBO loadModel(URL url) throws FileNotFoundException, IOException {

		String fpath = url.getPath();
		String separator = File.pathSeparator;

		if(!fpath.contains(separator)) {
			separator="\\";
		}

		String modelFolder = fpath.substring(0,fpath.lastIndexOf(separator)+1);

		BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
		VBO vbo = new VBO();

		List<Group> groups = new ArrayList<Group>();

		Group group = null;

		String line;

		while ((line = reader.readLine()) != null) {

			line = fixLine(line);

			if (line.startsWith(GROUP+" ")) {

				if(group!=null) {
					//Add last group
					groups.add(group);
				}

				group = new Group(line.split(" ")[1]);
			}
			else if (line.startsWith(USE_MATERIAL+" ")) {

				String materialName = line.split(" ")[1];
				if(group == null) {
					group = new Group(DEFAULT_GROUP_NAME);
				}
				group.setMaterial(vbo.getMaterials().get(materialName));
			}

			if (line.startsWith(VERTEX+" ")) {

				parseVertex(line, vbo);                

			} else if (line.startsWith(VERTEX_NORMAL+" ")) {

				parseVertexNormal(line, vbo);

			} else if (line.startsWith(VERTEX_TEXCOORD+" ")) {
				parseVertexTexture(line, vbo);

			} else if (line.startsWith(FACE+" ")) {

				String[] splitLine = line.substring(2).split(" ");

				int sides = splitLine.length;

				int[] vIndexes = new int[sides];
				Vector3f[] nIndexes = new Vector3f[sides];
				Vector2f[] texIndexes = new Vector2f[sides];

				for(int i=0;i<sides;i++) {

					String[] face = splitLine[i].split(SEPARATOR);

					if(face.length > 1) {

						//-1 is very important
						vIndexes[i] = Integer.parseInt(face[0])-1;

						if(!splitLine[i].split(SEPARATOR)[1].isEmpty())
							texIndexes[i] = vbo.getTextures().get(Integer.parseInt(face[1])-1);

						if(face.length > 2)
							nIndexes[i] = vbo.getNormals().get(Integer.parseInt(face[2])-1);

					}else{
						//Save only the vertexes indexes
						vIndexes[i] = Integer.parseInt(splitLine[i])-1;
					}
				}

				Face face = new Face(vIndexes, texIndexes, nIndexes);
				face.setSides(sides);

				group.getFaces().add(face);
				//TODO Remove face from model
				vbo.getFaces().add(face);

			} else if (line.startsWith(MATERIAL_LIB)) {

				List<OBJMaterial> materials = parseMaterial(modelFolder, line);

				for(OBJMaterial material: materials) {
					vbo.getMaterials().put(material.getName(), material);
				}

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

	private String fixLine(String line) {
		return line.replaceAll("  "," ");
	}

	private static void parseVertex(String line, VBO vbo) {

		String[] parts = line.split(" ");

		float x = Float.valueOf(parts[1]);
		float y = Float.valueOf(parts[2]);
		float z = Float.valueOf(parts[3]);
		vbo.getVertices().add(new Vector3f(x, y, z));
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
