package br.com.luvia.loader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import br.com.luvia.material.DiffuseMaterial;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class MaterialLoader {

	public static List<DiffuseMaterial> loadMaterial(String folder, String filename) throws IOException{
		
		File f = new File(folder+filename);
		
		BufferedReader reader = new BufferedReader(new FileReader(f));
		
		List<DiffuseMaterial> materials = new ArrayList<DiffuseMaterial>();
		
		DiffuseMaterial mat = new DiffuseMaterial();

		String line;
		
		while ((line = reader.readLine()) != null) {
		
			line = line.trim();
			
			String[] splitLine = line.split(" ");
			
			if(line.startsWith("newmtl ")) {
				
				if(mat!=null){
					materials.add(mat);
					mat = new DiffuseMaterial();
				}
				
				mat.setName(splitLine[1]);
								
			}
			else if (line.startsWith("ka ")) {
                float x = Float.valueOf(splitLine[1]);
                float y = Float.valueOf(splitLine[2]);
                float z = Float.valueOf(splitLine[3]);
                mat.setKa(new Vector3f(x, y, z));
            }else if (line.startsWith("kd ")) {
                float x = Float.valueOf(splitLine[1]);
                float y = Float.valueOf(splitLine[2]);
                float z = Float.valueOf(splitLine[3]);
                mat.setKd(new Vector3f(x, y, z));
            }else if (line.startsWith("ks ")) {
                float x = Float.valueOf(splitLine[1]);
                float y = Float.valueOf(splitLine[2]);
                float z = Float.valueOf(splitLine[3]);
                mat.setKs(new Vector3f(x, y, z));
            }else if (line.startsWith("illum ")) {
                int illum = Integer.valueOf(splitLine[1]);
                mat.setIllum(illum);
                
            }else if (line.startsWith("map_d ")) {
            	
            	//Image has full path
            	mat.setMapD(folder+splitLine[1]);
            	
            }
			
		}

		reader.close();

		if(mat!=null){
			materials.add(mat);
		}
		
		return materials;
	}

}
