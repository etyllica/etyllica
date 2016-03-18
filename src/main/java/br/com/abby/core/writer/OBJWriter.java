package br.com.abby.core.writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import br.com.abby.core.loader.OBJLoader;
import br.com.abby.core.vbo.Face;
import br.com.abby.core.vbo.VBO;
import br.com.etyllica.util.StringUtils;
import br.com.etyllica.util.io.IOHelper;

import com.badlogic.gdx.math.Vector3;

public class OBJWriter implements VBOWriter {
	
	private static final String FACE_SEPARATOR = "/";

	@Override
	public void writeVBO(VBO vbo, String filename) throws IOException {

		Writer writer = null;

		try {
			File file = IOHelper.getFile(filename);

			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(file), IOHelper.ENCODING_UTF_8));

			writeVertexes(vbo, writer);
			
			//Optional
			writer.write(StringUtils.NEW_LINE);
			
			writeNormals(vbo, writer);
			
			//Optional
			writer.write(StringUtils.NEW_LINE);
			
			writeFaces(vbo, writer);
			

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			writer.close();			
		}

	}

	private void writeFaces(VBO vbo, Writer writer) throws IOException {
		
		for(Face face: vbo.getFaces()) {
			
			StringBuilder sb = new StringBuilder();
			sb.append(OBJLoader.FACE+" ");
			
			for(int i=0;i<face.getSides();i++) {
				
				if(i>0) {
					sb.append(" ");
				}
		
				sb.append(face.vertexIndex[i]);
				
				boolean hasTexture = false;
				if(face.textureIndex!=null && face.textureIndex.length > 0) {
					hasTexture = true;
					sb.append(FACE_SEPARATOR);
					sb.append(face.textureIndex[i]);
					sb.append(FACE_SEPARATOR);
				}
				
				if(face.normalIndex!=null && face.normalIndex.length > 0) {
					if(!hasTexture) {
						sb.append(FACE_SEPARATOR);
						sb.append(FACE_SEPARATOR);
					}
					sb.append(face.normalIndex[i]);
				}				
			}
			
			sb.append(StringUtils.NEW_LINE);
			
			writer.write(sb.toString());
		}
	}
	
	private void writeNormals(VBO vbo, Writer writer) throws IOException {
		for(Vector3 vector: vbo.getNormals()) {
			String text = OBJLoader.VERTEX_NORMAL+" "+vector.x+" "+vector.y+" "+vector.z+StringUtils.NEW_LINE;
			writer.write(text);
		}
	}

	private void writeVertexes(VBO vbo, Writer writer) throws IOException {
		for(Vector3 vector: vbo.getVertices()) {
			String text = OBJLoader.VERTEX+" "+vector.x+" "+vector.y+" "+vector.z+StringUtils.NEW_LINE;
			writer.write(text);
		}
	}

}
