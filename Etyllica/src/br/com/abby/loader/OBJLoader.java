package br.com.abby.loader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import br.com.abby.material.OBJMaterial;
import br.com.abby.vbo.Face;
import br.com.abby.vbo.Group;
import br.com.abby.vbo.VBO;


/**
 * 
 * @author Oskar
 * (This version was modified by yuripourre to support materials)
 * @license LGPLv3
 *  
 */

public class OBJLoader {

    public static VBO loadModel(URL url) throws FileNotFoundException, IOException {
    	
    	String fpath = url.getPath();
    	String separator = "/";
    	
    	if(!fpath.contains(separator)) {
    		separator="\\";
    	}
    	
    	String folder = fpath.substring(0,fpath.lastIndexOf(separator)+1);
    	
    	
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        VBO m = new VBO();
                
        List<Group> groups = new ArrayList<Group>();
        
        Group group = null;
        
        String line;
        
        while ((line = reader.readLine()) != null) {
        	
        	line = line.replaceAll("  ", " ");
        	
        	if (line.startsWith("g ")) {
        		
        		if(group!=null) {
        			groups.add(group);
        		}
        		
        		group = new Group(line.split(" ")[1]);
        	}
        	else if (line.startsWith("usemtl ")) {
        		
        		String materialName = line.split(" ")[1];
        		
        		group.setMaterial(m.getMaterials().get(materialName));
        	}
        	
            if (line.startsWith("v ")) {
            	
            	parseVertex(line, m);                
                
            } else if (line.startsWith("vn ")) {
            	
                parseVertexNormal(line, m);
                
            } else if (line.startsWith("vt ")) {
            	parseVertexTexture(line, m);
                
            } else if (line.startsWith("f ")) {
            	
            	String[] splitLine = line.substring(2).split(" ");
            	
            	int quad = splitLine.length;
            	
            	Vector3f[] vIndexes = new Vector3f[quad];
            	Vector3f[] nIndexes = new Vector3f[quad];
            	Vector2f[] texIndexes = new Vector2f[quad];
            	
            	for(int i=0;i<quad;i++) {
            		            
            		String[] face = splitLine[i].split("/");
            		
            		if(face.length > 1) {
            		
            		//-1 is very important
                    vIndexes[i] = m.getVertices().get(Integer.parseInt(face[0])-1);
            		
                    if(!splitLine[i].split("/")[1].isEmpty())
                    texIndexes[i] = m.getTextures().get(Integer.parseInt(face[1])-1);
                    
                    nIndexes[i] = m.getNormals().get(Integer.parseInt(face[2])-1);
                    
            		}else{
            			vIndexes[i] = m.getVertices().get(Integer.parseInt(splitLine[i])-1);
            		}
            	}
            	
            	Face face = new Face(vIndexes, texIndexes, nIndexes);
            	face.setCount(quad);
            	
            	
            	group.getFaces().add(face);
            	//TODO Remove face from model
            	m.getFaces().add(face);
            	
            }
            else if (line.startsWith("mtllib")) {
            	
            	List<OBJMaterial> materials = parseMaterial(folder, line);
            	            	
            	for(OBJMaterial material: materials) {
            		m.getMaterials().put(material.getName(), material);
            	}
            	
            }
        }
                
        reader.close();
        
        //Add group to Model
        if(group!=null) {
			groups.add(group);
		}
        
        m.setGroups(groups);
        
        return m;
    }
    
    private static void parseVertex(String line, VBO vbo) {
    	
    	String[] parts = line.split(" ");
    	
    	float x = Float.valueOf(parts[1]);
        float y = Float.valueOf(parts[2]);
        float z = Float.valueOf(parts[3]);
        vbo.getVertices().add(new Vector3f(x, y, z));
    }
    
    private static void parseVertexNormal(String line, VBO vbo) {
    	
    	String[] parts = line.split(" ");
    	
    	float x = Float.valueOf(parts[1]);
        float y = Float.valueOf(parts[2]);
        float z = Float.valueOf(parts[3]);
        
        vbo.getNormals().add(new Vector3f(x, y, z));    	
    }
    
    private static void parseVertexTexture(String line, VBO vbo) {
    	
    	String[] parts = line.split(" ");
    	
    	float x = Float.valueOf(parts[1]);
        float y = Float.valueOf(parts[2]);
        
        vbo.getTextures().add(new Vector2f(x, y));     	
    }
    
    private static List<OBJMaterial> parseMaterial(String folder, String line) throws IOException {
    	
    	String filename = line.split(" ")[1];
    	    	
    	List<OBJMaterial> materials = MaterialLoader.loadMaterial(folder,filename);
    	
    	return materials;    	
    }
    
}
