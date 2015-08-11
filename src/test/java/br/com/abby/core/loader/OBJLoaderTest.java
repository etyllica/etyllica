package br.com.abby.core.loader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.abby.core.vbo.VBO;
import br.com.etyllica.util.PathHelper;

public class OBJLoaderTest {

	private OBJLoader loader;
	
	@Before
	public void setUp() throws MalformedURLException {
		String path = PathHelper.currentDirectory();
				
		URL url = new URL(path+"../");
		
		MeshLoader.getInstance().setUrl(url.toString());
		
		loader = (OBJLoader) MeshLoader.getInstance().getLoader("obj");
	}
	
	@Test
	public void testLoadModel() {
		
		//Load a cube made with triangles
		String filename = "cube.obj";
		
		URL dir = null;
		try {
			dir = MeshLoader.getInstance().getFullURL(filename);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			VBO vbo = loader.loadModel(dir);
			Assert.assertNotNull(vbo);
			Assert.assertEquals(12, vbo.getFaces().size());
			Assert.assertEquals(8, vbo.getVertices().size());
			
		} catch (FileNotFoundException e) {
			Assert.fail();
			e.printStackTrace();
		} catch (IOException e) {
			Assert.fail();
			e.printStackTrace();
		}
		
	}
	
}
