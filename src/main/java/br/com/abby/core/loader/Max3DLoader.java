package br.com.abby.core.loader;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import br.com.abby.core.material.OBJMaterial;
import br.com.abby.core.vbo.Face;
import br.com.abby.core.vbo.Group;
import br.com.abby.core.vbo.VBO;

/**
 * 
 * Code forked from min3d library https://code.google.com/p/min3d/
 * Reference: http://cse.csusb.edu/tongyu/courses/cs520/notes/3DSINFO.TXT
 *
 */
public class Max3DLoader extends StreamParser implements VBOLoader {

	private final int IDENTIFIER_3DS = 0x4D4D; //Main Chunk
	private final int M3D_VERSION = 0x0002;
	private final int MESH_BLOCK = 0x3D3D; //3D Editor Chunk
	private final int OBJECT_BLOCK = 0x4000;
	private final int TRIMESH = 0x4100;
	private final int TRI_MATERIAL = 0x4130;
	private final int VERTICES = 0x4110;
	private final int FACES = 0x4120;
	private final int TEXCOORD = 0x4140;

	private final int MATERIAL = 0xAFFF;
	private final int MATERIAL_NAME = 0xA000;
	private final int MATERIAL_AMBIENT_COLOR = 0xA010;
	private final int MATERIAL_DIFUSE_COLOR = 0xA020;
	private final int MATERIAL_SPECULAR_COLOR = 0xA030;
	private final int MATERIAL_RENDER_TYPE = 0xA100;
	private final int MATERIAL_TEXTURE_MAP = 0xA200;
	private final int MATERIAL_TEXTURE_FILENAME = 0xA300;
	private final int MATERIAL_REFLECTION_MAP = 0xA220;
	private final int MATERIAL_BUMP_MAP = 0xA230;

	private final int COLOR_F = 0x0010;
	private final int COLOR_24 = 0x0011;
	private final int LIN_COLOR_24 = 0x0012;
	private final int LIN_COLOR_F = 0x0013;
	private final int INT_PERCENTAGE = 0x0030;
	private final int FLOAT_PERCENTAGE = 0x0031;
	
	private class Chunk {
		public int id;
		public int endOffset;
	}

	@Override
	public VBO loadModel(URL url) throws FileNotFoundException, IOException {

		BufferedInputStream stream = new BufferedInputStream(url.openStream());

		String currentObjName = DEFAULT_GROUP_NAME;
		
		VBO vbo = new VBO();
		Chunk chunk = new Chunk();
		
		boolean endReached = false;
		
		endReached = readChunk(stream, chunk);
		if(chunk.id != IDENTIFIER_3DS) {
			System.err.println("Not a valid .3DS file!");
			return null;
		}

		List<Group> groups = new ArrayList<Group>();
		Group group = new Group(DEFAULT_GROUP_NAME);
		OBJMaterial currentMaterial = null;

		while(!endReached) {
			//Read Chunks
			endReached = readChunk(stream, chunk);
			if(endReached) {
				break;
			}

			System.out.println("Chunk: 0x"+Integer.toString(chunk.id, 16));

			switch (chunk.id) {
			case MESH_BLOCK:
				break;
			case OBJECT_BLOCK:
				//Equivalent to Object in OBJ format
				currentObjName = readString(stream);
				break;
			case TRIMESH:
				//Transform mesh in groups to use OBJ Structure
				String groupName = currentObjName;

				System.out.println("Loading group: "+groupName);

				if(!DEFAULT_GROUP_NAME.equals(group.getName())) {
					//Add last group
					groups.add(group);
				} else if(!group.getFaces().isEmpty()) {
					//If group has at least one face 
					groups.add(group);
				}
				group = new Group(groupName);
				break;
			case VERTICES:
				readVertices(stream, vbo);
				break;
			case FACES:
				readFaces(stream, vbo, group);
				break;
			case TEXCOORD:
				readTexCoords(stream, vbo);
				break;
			case MATERIAL_NAME:
				String currentMaterialKey = readString(stream);
				currentMaterial = new OBJMaterial();
				vbo.getMaterials().put(currentMaterialKey, currentMaterial);

				System.out.println("Loading material: "+currentMaterialKey);
				break;
			case MATERIAL_DIFUSE_COLOR:
				/*int rgb = readInt(stream);
				
				float r = (rgb >> 16) & 0xFF;
				float g = (rgb >> 8) & 0xFF;
				float b = rgb & 0xFF;
				
				Chunk colorChunk = new Chunk();
				endReached = readChunk(stream, colorChunk);
				System.out.println("Color: "+colorChunk.id);
				
				float r = readFloat(stream);
				float g = readFloat(stream);
				float b = readFloat(stream);

				
				Vector3f color = new Vector3f(255/r, 255/g, 255/b);

				currentMaterial.setKd(color);
				*/
				break;
			case MATERIAL_TEXTURE_FILENAME:
				String fileName = readString(stream);
				System.out.println("Loading texture: "+fileName);

				currentMaterial.setMapKd(fileName);
				break;
			case TRI_MATERIAL:
				String materialName = readString(stream);
				int numFaces = readShort(stream);

				for(int i=0; i<numFaces; i++) {
					int faceIndex = readShort(stream);

					//vbo.getFaces().get(faceIndex).materialKey = materialName;
				}
				break;
			case MATERIAL:
				break;
			case MATERIAL_TEXTURE_MAP:
				break;
			default:
				endReached = skipRead(stream, chunk);
			}
		}

		//Add group to Model
		if (group != null) {
			groups.add(group);
		}

		vbo.setGroups(groups);

		return vbo;
	}
	
	private boolean readChunk(InputStream stream, Chunk chunk) throws IOException {
		chunk.id = readShort(stream);
		chunk.endOffset = readInt(stream);
		return chunk.id < 0;
	}

	private boolean skipRead(InputStream stream, Chunk chunk) throws IOException {
		boolean endReached = false;
		for(int i=0; (i<chunk.endOffset - 6) && !endReached; i++) {
			endReached = stream.read() < 0;
		}
		return endReached;
	}

	private void readVertices(InputStream buffer, VBO vbo) throws IOException {
		float x, y, z;
		int numVertices = readShort(buffer);

		for (int i = 0; i < numVertices; i++) {
			x = readFloat(buffer);
			y = readFloat(buffer);
			z = readFloat(buffer);

			Vector3 v = new Vector3(x, z, -y);
			vbo.addVertex(v);
		}
	}

	private void readFaces(InputStream buffer, VBO vbo, Group group) throws IOException {
		int triangles = readShort(buffer); 

		for (int i = 0; i < triangles; i++) {
			//Set indices
			int[] vertexIndexes = new int[3];

			vertexIndexes[0] = readShort(buffer);
			vertexIndexes[1] = readShort(buffer);
			vertexIndexes[2] = readShort(buffer);
			readShort(buffer);

			Face face = new Face(3);
			face.vertexIndex = vertexIndexes;
			//face.uv = vertexIDs;
			//face.hasuv = true;

			group.getFaces().add(face);
			vbo.getFaces().add(face);
			//co.calculateFaceNormal(face);
		}
	}

	private void readTexCoords(InputStream buffer, VBO vbo) throws IOException {
		int numVertices = readShort(buffer);

		for (int i = 0; i < numVertices; i++) {
			Vector2 uv = new Vector2();
			uv.x = readFloat(buffer);
			uv.y = -readFloat(buffer);
			vbo.getTextures().add(uv);
		}
	}

}
