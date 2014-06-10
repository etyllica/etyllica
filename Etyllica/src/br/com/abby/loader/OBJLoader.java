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

import br.com.abby.linear.Model3D;
import br.com.abby.material.DiffuseMaterial;
import br.com.abby.vbo.Face;
import br.com.abby.vbo.Group;


/**
 * 
 * @author Oskar
 * (This version was modified by yuripourre to support materials)
 * @license LGPLv3
 *  
 */

public class OBJLoader {
	/*
    public static int createDisplayList(Model m) {
        int displayList = glGenLists(1);
        glNewList(displayList, GL_COMPILE);
        {
            glColor3f(0.4f, 0.27f, 0.17f);
            glMaterialf(GL_FRONT, GL_SHININESS, 128.0f);
            glBegin(GL_TRIANGLES);
            for (Face face : m.faces) {
                Vector3f n1 = m.normals.get((int) face.normal.x - 1);
                glNormal3f(n1.x, n1.y, n1.z);
                Vector3f v1 = m.vertices.get((int) face.vertex.x - 1);
                glVertex3f(v1.x, v1.y, v1.z);
                Vector3f n2 = m.normals.get((int) face.normal.y - 1);
                glNormal3f(n2.x, n2.y, n2.z);
                Vector3f v2 = m.vertices.get((int) face.vertex.y - 1);
                glVertex3f(v2.x, v2.y, v2.z);
                Vector3f n3 = m.normals.get((int) face.normal.z - 1);
                glNormal3f(n3.x, n3.y, n3.z);
                Vector3f v3 = m.vertices.get((int) face.vertex.z - 1);
                glVertex3f(v3.x, v3.y, v3.z);
            }
            glEnd();
        }
        glEndList();
        return displayList;
    }
    

    private static FloatBuffer reserveData(int size) {
        FloatBuffer data = BufferUtils.createFloatBuffer(size);
        return data;
    }

    private static float[] asFloats(Vector3f v) {
        return new float[]{v.x, v.y, v.z};
    }

    private static int[] createVBO(Model model) {
        int vboVertexHandle = glGenBuffers();
        int vboNormalHandle = glGenBuffers();
        FloatBuffer vertices = reserveData(model.faces.size() * 9);
        FloatBuffer normals = reserveData(model.faces.size() * 9);
        for (Face face : model.faces) {
            vertices.put(asFloats(model.vertices.get((int) face.vertex.x - 1)));
            vertices.put(asFloats(model.vertices.get((int) face.vertex.y - 1)));
            vertices.put(asFloats(model.vertices.get((int) face.vertex.z - 1)));
            normals.put(asFloats(model.normals.get((int) face.normal.x - 1)));
            normals.put(asFloats(model.normals.get((int) face.normal.y - 1)));
            normals.put(asFloats(model.normals.get((int) face.normal.z - 1)));
        }
        vertices.flip();
        normals.flip();
        glBindBuffer(GL_ARRAY_BUFFER, vboVertexHandle);
        glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);
        glVertexPointer(3, GL_FLOAT, 0, 0L);
        glBindBuffer(GL_ARRAY_BUFFER, vboNormalHandle);
        glBufferData(GL_ARRAY_BUFFER, normals, GL_STATIC_DRAW);
        glNormalPointer(GL_FLOAT, 0, 0L);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        return new int[]{vboVertexHandle, vboNormalHandle};
    }*/

    public static Model3D loadModel(URL url) throws FileNotFoundException, IOException {
    	
    	String fpath = url.getPath();
    	String separator = "/";
    	
    	if(!fpath.contains(separator)){
    		separator="\\";
    	}
    	
    	String folder = fpath.substring(0,fpath.lastIndexOf(separator)+1);
    	
    	
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        Model3D m = new Model3D();
                
        List<Group> groups = new ArrayList<Group>();
        
        Group group = null;
        
        String line;
        
        while ((line = reader.readLine()) != null) {
        	
        	line = line.replaceAll("  ", " ");
        	
        	if (line.startsWith("g ")) {
        		
        		if(group!=null){
        			groups.add(group);
        		}
        		
        		group = new Group(line.split(" ")[1]);
        	}
        	else if (line.startsWith("usemtl ")) {
        		
        		String materialName = line.split(" ")[1];
        		
        		group.setMaterial(m.getMaterials().get(materialName));
        	}
        	
            if (line.startsWith("v ")) {
                float x = Float.valueOf(line.split(" ")[1]);
                float y = Float.valueOf(line.split(" ")[2]);
                float z = Float.valueOf(line.split(" ")[3]);
                m.vertexes.add(new Vector3f(x, y, z));
                
            } else if (line.startsWith("vn ")) {
                float x = Float.valueOf(line.split(" ")[1]);
                float y = Float.valueOf(line.split(" ")[2]);
                float z = Float.valueOf(line.split(" ")[3]);
                m.normals.add(new Vector3f(x, y, z));
                
            } else if (line.startsWith("vt ")) {
            	float x = Float.valueOf(line.split(" ")[1]);
                float y = Float.valueOf(line.split(" ")[2]);
                m.textures.add(new Vector2f(x, y));     
                
            } else if (line.startsWith("f ")) {
            	
            	String[] splitLine = line.substring(2).split(" ");
            	
            	int quad = splitLine.length;
            	
            	Vector3f[] vIndexes = new Vector3f[quad];
            	Vector3f[] nIndexes = new Vector3f[quad];
            	Vector2f[] texIndexes = new Vector2f[quad];
            	
            	for(int i=0;i<quad;i++){
            		            
            		String[] face = splitLine[i].split("/");
            		
            		if(face.length>1){
            		
            		//-1 is very important
                    vIndexes[i] = m.vertexes.get(Integer.parseInt(face[0])-1);
            		
                    if(!splitLine[i].split("/")[1].isEmpty())
                    texIndexes[i] = m.textures.get(Integer.parseInt(face[1])-1);
                    
                    nIndexes[i] = m.normals.get(Integer.parseInt(face[2])-1);
                    
            		}else{
            			vIndexes[i] = m.vertexes.get(Integer.parseInt(splitLine[i])-1);
            		}
            	}
            	
            	Face face = new Face(vIndexes, texIndexes, nIndexes);
            	face.setCount(quad);
            	
            	
            	group.getFaces().add(face);
            	//TODO Remove face from model
            	m.faces.add(face);
            	
            }
            else if (line.startsWith("mtllib")) {
            	
            	String filename = line.split(" ")[1];
            	
            	List<DiffuseMaterial> materials = MaterialLoader.loadMaterial(folder,filename);
            	
            	for(DiffuseMaterial material: materials){
            		m.getMaterials().put(material.getName(), material);
            	}
            	
            }
        }
                
        reader.close();
        
        //Add group to Model
        if(group!=null){
			groups.add(group);
		}
        
        m.setGroups(groups);
        
        return m;
    }
}
