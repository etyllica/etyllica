package br.com.abby.core.writer;

import java.io.IOException;
import java.net.URL;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.lwjgl.util.vector.Vector3f;

import br.com.abby.core.loader.OBJLoader;
import br.com.abby.core.vbo.Face;
import br.com.abby.core.vbo.VBO;
import br.com.etyllica.util.PathHelper;

public class OBJWriterTest {

	private static final String FILENAME = "test.obj";
	
	private VBO vbo;
	
	@Before
	public void setUp() {
		vbo = new VBO();
		
		vbo.getVertices().add(new Vector3f(0,0,0));
		vbo.getVertices().add(new Vector3f(0,0,1));
		vbo.getVertices().add(new Vector3f(0,1,0));
		vbo.getVertices().add(new Vector3f(0,1,1));
		vbo.getVertices().add(new Vector3f(1,0,0));
		vbo.getVertices().add(new Vector3f(1,0,1));
		vbo.getVertices().add(new Vector3f(1,1,0));
		vbo.getVertices().add(new Vector3f(1,1,1));
		
		vbo.getNormals().add(new Vector3f(0,0,1));
		vbo.getNormals().add(new Vector3f(0,0,-1));
		vbo.getNormals().add(new Vector3f(0,1,0));
		vbo.getNormals().add(new Vector3f(0,-1,0));
		vbo.getNormals().add(new Vector3f(1,0,0));
		vbo.getNormals().add(new Vector3f(-1,0,0));
		
		vbo.getFaces().add(new Face(3).addVertexes(1,7,5).addNormals(2,2,2));
		vbo.getFaces().add(new Face(3).addVertexes(1,3,7).addNormals(2,2,2));
		vbo.getFaces().add(new Face(3).addVertexes(1,4,3).addNormals(6,6,6));
		vbo.getFaces().add(new Face(3).addVertexes(1,2,4).addNormals(6,6,6));
		vbo.getFaces().add(new Face(3).addVertexes(3,8,7).addNormals(3,3,3));
		vbo.getFaces().add(new Face(3).addVertexes(3,4,8).addNormals(3,3,3));
		vbo.getFaces().add(new Face(3).addVertexes(5,7,8).addNormals(5,5,5));
		vbo.getFaces().add(new Face(3).addVertexes(5,8,6).addNormals(5,5,5));
		vbo.getFaces().add(new Face(3).addVertexes(1,5,6).addNormals(4,4,4));
		vbo.getFaces().add(new Face(3).addVertexes(1,6,2).addNormals(4,4,4));
		vbo.getFaces().add(new Face(3).addVertexes(2,6,8).addNormals(1,1,1));
		vbo.getFaces().add(new Face(3).addVertexes(2,8,4).addNormals(1,1,1));
	}
	
	@Test
	public void testVBOWriter() {
		
		String path = PathHelper.currentDirectory();
		
		VBOWriter writer = new OBJWriter();
		try {
			writer.writeVBO(vbo, path+FILENAME);
						
			URL url = new URL(path+FILENAME);
			
			VBO loaded = new OBJLoader().loadModel(url);
			
			Assert.assertEquals(8, loaded.getVertices().size());
			Assert.assertEquals(6, loaded.getNormals().size());
			Assert.assertEquals(12, loaded.getFaces().size());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Assert.fail();
			e.printStackTrace();
		}
		
		
	}
	
}
