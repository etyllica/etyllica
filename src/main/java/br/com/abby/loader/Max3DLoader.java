package br.com.abby.loader;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import br.com.abby.vbo.Face;
import br.com.abby.vbo.VBO;

public class Max3DLoader extends StreamParser implements VBOLoader {

	private final int IDENTIFIER_3DS = 0x4D4D;
	private final int MESH_BLOCK = 0x3D3D;
	private final int OBJECT_BLOCK = 0x4000;
	private final int TRIMESH = 0x4100;
	private final int TRI_MATERIAL = 0x4130;
	private final int VERTICES = 0x4110;
	private final int FACES = 0x4120;
	private final int TEXCOORD = 0x4140;
	private final int TEX_MAP = 0xA200;
	private final int TEX_NAME = 0xA000;
	private final int TEX_FILENAME = 0xA300;
	private final int MATERIAL = 0xAFFF;

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
		while(!endReached) {
			readChunk(stream, vbo);
		}

		return null;
	}

	private void readHeader(InputStream stream, VBO co) throws IOException {
		chunkID = readShort(stream);
		chunkEndOffset = readInt(stream);
		endReached = chunkID < 0;
	}

	private void readChunk(InputStream stream, VBO vbo) throws IOException {
		readHeader(stream, vbo);

		switch (chunkID) {
		case MESH_BLOCK:
			break;
		case OBJECT_BLOCK:
			currentObjName = readString(stream);			
			break;
		case TRIMESH:
			vbo.setName(currentObjName);
			
			//TODO Handle Multiple VBOs
			/*
			if(firstObject) {
				co.setName(currentObjName);
				firstObject = false;
			} else {
				co = new VBO();
				co.setName(currentObjName);
				parseObjects.add(co);
			}*/
			break;
		case VERTICES:
			readVertices(stream, vbo);
			break;
		case FACES:
			readFaces(stream, vbo);
			break;
		case TEXCOORD:
			readTexCoords(stream, vbo);
			break;
		case TEX_NAME:
			//currentMaterialKey = readString(stream);
			break;
		case TEX_FILENAME:
			String fileName = readString(stream);

			//TODO Load texture
			/*StringBuffer textureName = new StringBuffer(fileName.toLowerCase());
			int dotIndex = textureName.lastIndexOf(".");
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
		case TEX_MAP:
			break;
		default:
			skipRead(stream);
		}
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

		for (int i = 0; i < numVertices; i++) {
			x = readFloat(buffer);
			y = readFloat(buffer);
			z = readFloat(buffer);
			tmpy = y;
			y = z;
			z = -tmpy;

			vbo.getVertices().add(new Vector3f(x, y, z));
		}
	}

	private void readFaces(InputStream buffer, VBO vbo) throws IOException {
		int triangles = readShort(buffer);
		
		int[] vertexIDs = new int[triangles]; 
		
		for (int i = 0; i < triangles; i++) {
			//Set indices
			vertexIDs[0] = readShort(buffer);
            vertexIDs[1] = readShort(buffer);
            vertexIDs[2] = readShort(buffer);
			readShort(buffer);
			
			Face face = new Face(3);
			
			face.vertexIndex = vertexIDs;
			//face.uv = vertexIDs;
			//face.hasuv = true;
			
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
