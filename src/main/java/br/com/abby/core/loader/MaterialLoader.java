package br.com.abby.core.loader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector3;

import br.com.abby.core.material.OBJMaterial;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class MaterialLoader {
	
	private static final String NEW_MATERIAL = "newmtl";
	private static final String DIFFUSE_COLOR = "Kd";
	private static final String DIFFUSE_TEX_MAP = "map_Kd";

	public static List<OBJMaterial> loadMaterial(String folder, String filename) throws IOException{
				
		File f = new File(folder+filename);
		
		BufferedReader reader = new BufferedReader(new FileReader(f));
		
		List<OBJMaterial> materials = new ArrayList<OBJMaterial>();
		
		OBJMaterial mat = new OBJMaterial();

		String line;
		
		while ((line = reader.readLine()) != null) {
		
			line = line.trim();
			
			String[] splitLine = line.split(" ");
			String prefix = splitLine[0];
			
			if(NEW_MATERIAL.equalsIgnoreCase(prefix)) {
				
				if(mat!=null){
					materials.add(mat);
					mat = new OBJMaterial();
				}
				
				mat.setName(splitLine[1]);
								
			} else if ("ka".equalsIgnoreCase(prefix)) {
                float x = Float.valueOf(splitLine[1]);
                float y = Float.valueOf(splitLine[2]);
                float z = Float.valueOf(splitLine[3]);
                mat.setKa(new Vector3(x, y, z));
            } else if (DIFFUSE_COLOR.equalsIgnoreCase(prefix)) {
                float x = Float.valueOf(splitLine[1]);
                float y = Float.valueOf(splitLine[2]);
                float z = Float.valueOf(splitLine[3]);
                mat.setKd(new Vector3(x, y, z));
            } else if ("ks".equalsIgnoreCase(prefix)) {
                float x = Float.valueOf(splitLine[1]);
                float y = Float.valueOf(splitLine[2]);
                float z = Float.valueOf(splitLine[3]);
                mat.setKs(new Vector3(x, y, z));
            } else if ("illum".equalsIgnoreCase(prefix)) {
                int illum = Integer.valueOf(splitLine[1]);
                mat.setIllum(illum);
            } else if ("map_d".equalsIgnoreCase(prefix)) {
            	mat.setMapD(folder+splitLine[1]);
            } else if (DIFFUSE_TEX_MAP.equalsIgnoreCase(prefix)) {
            	mat.setMapKd(folder+splitLine[1]);
            }else if ("map_ka".equalsIgnoreCase(prefix)) {
            	mat.setMapKa(folder+splitLine[1]);
            }
			
		}

		reader.close();

		if (mat != null) {
			materials.add(mat);
		}
		
		return materials;
	}

}
