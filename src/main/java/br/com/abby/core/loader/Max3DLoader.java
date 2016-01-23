package br.com.abby.core.loader;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import br.com.abby.core.vbo.Face;
import br.com.abby.core.vbo.Group;
import br.com.abby.core.vbo.VBO;

/**
 * Code forked from min3d library https://code.google.com/p/min3d/
 *
 */
public class Max3DLoader extends StreamParser implements VBOLoader {

	private final int IDENTIFIER_3DS = 0x4D4D;
	private final int M3D_VERSION = 0x0002;
	private final int MESH_BLOCK = 0x3D3D;//3D Editor Chunk
	private final int OBJECT_BLOCK = 0x4000;
	private final int TRIMESH = 0x4100;
	private final int TRI_MATERIAL = 0x4130;
	private final int VERTICES = 0x4110;
	private final int FACES = 0x4120;
	private final int TEXCOORD = 0x4140;
	
	private final int TEX_FILENAME = 0xA300;
	private final int MATERIAL = 0xAFFF;
	private final int MATERIAL_NAME = 0xA000;
	private final int MATERIAL_AMBIENT_COLOR = 0xA010;
	private final int MATERIAL_DIFUSE_COLOR = 0xA020;
	private final int MATERIAL_SPECULAR_COLOR = 0xA030;
	private final int MATERIAL_TEXTURE_MAP = 0xA200;
	private final int MATERIAL_REFLECTION_MAP = 0xA220;
	private final int MATERIAL_BUMP_MAP = 0xA230;
	

	private int chunkID;
	private int chunkEndOffset;
	private boolean endReached;
	private String currentObjName;

	@Override
	public VBO loadModel(URL url) throws FileNotFoundException, IOException {

		BufferedInputStream stream = new BufferedInputStream(url.openStream());

		VBO vbo = new VBO();

		readHeader(stream, vbo);
		if(chunkID != IDENTIFIER_3DS) {
			System.err.println("Not a valid .3DS file!");
			return null;
		}

		List<Group> groups = new ArrayList<Group>();
		Group group = new Group(DEFAULT_GROUP_NAME);

		while(!endReached) {
			//Read Chunks
			readHeader(stream, vbo);

			System.out.println("Chunk: 0x"+Integer.toString(chunkID, 16));
			
			switch (chunkID) {
			case MESH_BLOCK:
				break;
			case OBJECT_BLOCK:
				//Equivalent to Object in OBJ
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
				System.out.println("Loading material: "+currentMaterialKey);
				break;
			case TEX_FILENAME:
				String fileName = readString(stream);
				System.out.println("Loading texture: "+fileName);
				
				//TODO Load texture
				StringBuffer textureName = new StringBuffer(fileName.toLowerCase());
				
				/*int dotIndex = textureName.lastIndexOf(".");
				if (dotIndex > -1)
					texture.append(textureName.substring(0, dotIndex));
				else
					texture.append(textureName);

				textureAtlas.addBitmapAsset(new BitmapAsset(currentMaterialKey, texture.toString()));*/

				break;
			case TRI_MATERIAL:
				String materialName = readString(stream);
				int numFaces = readShort(stream);

				for(int i=0; i<numFaces; i++)
				{
					int faceIndex = readShort(stream);

					//vbo.getFaces().get(faceIndex).materialKey = materialName;
				}
				break;
			case MATERIAL:
				break;
			case MATERIAL_TEXTURE_MAP:
				break;
			default:
				skipRead(stream);
			}			
		}

		//Add group to Model
		if(group!=null) {
			groups.add(group);
		}

		vbo.setGroups(groups);

		return vbo;
	}

	private void readHeader(InputStream stream, VBO co) throws IOException {
		chunkID = readShort(stream);
		chunkEndOffset = readInt(stream);
		endReached = chunkID < 0;
	}

	private void readChunk(InputStream stream, VBO vbo) throws IOException {

	}

	private void skipRead(InputStream stream) throws IOException
	{
		for(int i=0; (i<chunkEndOffset - 6) && !endReached; i++)
		{
			endReached = stream.read() < 0;
		}
	}

	private void readVertices(InputStream buffer, VBO vbo) throws IOException {
		float x, y, z, tmpy;
		int numVertices = readShort(buffer);
		System.out.println("Loading vertices: "+numVertices);
		
		for (int i = 0; i < numVertices; i++) {
			x = readFloat(buffer);
			y = readFloat(buffer);
			z = readFloat(buffer);
			
			Vector3f v = new Vector3f(x, z, -y);
			vbo.addVertex(v);
		}
	}

	private void readFaces(InputStream buffer, VBO vbo, Group group) throws IOException {
		int triangles = readShort(buffer);

		//int[] vertexIDs = new int[triangles]; 

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
			Vector2f uv = new Vector2f();
			uv.x = readFloat(buffer);
			uv.y = readFloat(buffer) * -1f;
			vbo.getTextures().add(uv);
		}
	}

}
