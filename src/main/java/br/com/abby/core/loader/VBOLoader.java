package br.com.abby.core.loader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import br.com.abby.core.vbo.VBO;

public interface VBOLoader {

	static final String DEFAULT_GROUP_NAME = "default";
	
	public VBO loadModel(URL url) throws FileNotFoundException, IOException;
	
}
