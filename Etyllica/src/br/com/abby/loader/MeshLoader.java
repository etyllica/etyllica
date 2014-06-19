package br.com.abby.loader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import br.com.abby.vbo.VBO;
import br.com.etyllica.core.loader.LoaderImpl;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class MeshLoader extends LoaderImpl {

	private static MeshLoader instance = null;

	public static MeshLoader getInstance() {
		if(instance==null){
			instance = new MeshLoader();
		}

		return instance;
	}
	
	private MeshLoader() {
		super();
		
		folder = "assets/models/";
	}
	
	public VBO loadModel(String path) {
		
		String diretorio = folder+path;
		
		URL dir = null;
		try {
			dir = new URL(url, diretorio);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		
		try {
			return OBJLoader.loadModel(dir);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
}
