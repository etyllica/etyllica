package br.com.abby.loader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import br.com.abby.vbo.VBO;

public interface VBOLoader {

	public VBO loadModel(URL url) throws FileNotFoundException, IOException;
	
}
