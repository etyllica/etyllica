package br.com.abby.loader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import br.com.abby.vbo.VBO;
import br.com.etyllica.core.loader.LoaderImpl;
import br.com.etyllica.util.StringUtils;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class MeshLoader extends LoaderImpl {

	private static MeshLoader instance = null;
	
	private Map<String, VBOLoader> loaders = new HashMap<String, VBOLoader>();
	
	private static final String OBJ = "obj";

	public static MeshLoader getInstance() {
		if(instance==null){
			instance = new MeshLoader();
		}

		return instance;
	}
	
	private MeshLoader() {
		super();
		
		folder = "assets/models/";
		
		loaders.put(OBJ, new OBJLoader());
	}
	
	public VBO loadModel(String path) {
				
		URL dir = null;
		try {
			dir = getFullURL(path);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		
		String ext = StringUtils.fileExtension(path);
		VBOLoader loader = getLoader(ext);
		
		try {
			return loader.loadModel(dir);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public URL getFullURL(String filename) throws MalformedURLException {
		return new URL(url, folder+filename);
	}
	
	public VBOLoader getLoader(String extension) {
		return loaders.get(extension);
	}
	
	public Set<String> supportedExtensions() {
		return loaders.keySet();
	}
	
}