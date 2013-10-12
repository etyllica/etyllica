package br.com.luvia.loader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import br.com.etyllica.core.loader.Loader;
import br.com.luvia.linear.Model3D;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class MeshLoader extends Loader{

	private static MeshLoader instancia = null;

	public static MeshLoader getInstance() {
		if(instancia==null){
			instancia = new MeshLoader();
		}

		return instancia;
	}
	
	private MeshLoader(){
		super();
		
		folder = "assets/models/";
	}
	
	public Model3D loadModel(String caminho){
		
		String diretorio = folder+caminho;
		
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
