package br.com.abby.linear;


import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgl.GL;
import org.jgl.GLAUX;
import org.lwjgl.util.vector.Vector3f;

import br.com.abby.GLDrawable;
import br.com.abby.loader.MeshLoader;
import br.com.abby.material.Texture;
import br.com.abby.vbo.Face;
import br.com.abby.vbo.Group;
import br.com.abby.vbo.VBO;
import br.com.etyllica.core.loader.image.ImageLoader;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Model3D extends AimPoint implements GLDrawable {
	
	private VBO vbo;
	
	private double scale = 1;

	private Set<Integer> vertexSelection = new HashSet<Integer>();

	private boolean drawTexture = true;
	private boolean drawFaces = true;
	private boolean drawVertices = true;

	//TODO Just for test, Remove!
	public Integer specialVertex = 0;
	
	private Map<String, Texture> textureMap = new HashMap<String, Texture>();

	public Model3D(String path) {
		super(0,0,0);
		
		this.vbo = MeshLoader.getInstance().loadModel(path);		
	}

	@Override
	public void draw(GLAUX gl) {

		gl.glPushMatrix();

		gl.glColor3i(color.getRed(), color.getGreen(), color.getBlue());
		
		if(scale!=1) {
			gl.glScaled(2, 2, 2);	
		}
		
		//gl.glTranslated(x, y, z);
		gl.glRotated(angleY, 0, 1, 0);
		gl.glRotated(angleZ, 0, 0, 1);
		//gl.glBegin(GL.GL_QUADS);

		drawFaces(gl);

		//drawVertexes(gl);

		//gl.glEnd();

		gl.glPopMatrix();

	}
	
	private void drawFaces(GLAUX gl){

		if(!drawFaces){
			return;
		}
		
		for(Group group: vbo.getGroups()) {

			String map = group.getMaterial().getMapD();
			//map = "";

			if(!map.isEmpty()){

				Texture texture = getTexture(map);
				
				
				if(drawTexture){

					setTexture(gl, texture);
					gl.glEnable (GL.GL_DEPTH_TEST);
					gl.glEnable (GL.GL_TEXTURE_2D);
					gl.glEnable (GL.GL_CULL_FACE);

				}

				//gl.glBegin(GL.GL_QUADS);

				float offsetX = 1f;
				float offsetZ = -3f;
				float size = 1.0f;

				/*gl.glTexCoord2f(0.0f, 1.0f);
			gl.glVertex3f(-offsetX-size, 0, +size+offsetZ);
			gl.glTexCoord2f(1.0f, 1.0f);
			gl.glVertex3f(-offsetX+size, 0.8f, +size+offsetZ);
			gl.glTexCoord2f(1.0f, 0.0f);
			gl.glVertex3f(-offsetX+size, 0.6f, +offsetZ);
			gl.glTexCoord2f(0.0f, 0.0f);
			gl.glVertex3f(-offsetX-size, 0, +offsetZ);*/


				//gl.glEnd();
			}

			for(Face face: group.getFaces()){

				if(face.vertex.length==3){

					gl.glBegin(GL.GL_TRIANGLES);

				}else{ //TODO Transform all faces in tris

					gl.glBegin(GL.GL_QUADS);

				}

				for(int i=0;i<face.vertex.length;i++){

					if(drawTexture){
						gl.glNormal3d(face.normal[i].getX(), face.normal[i].getY(), face.normal[i].getZ());
						gl.glTexCoord2d(face.texture[i].getX(), face.texture[i].getY());
					}
					gl.glVertex3d(face.vertex[i].getX(), face.vertex[i].getY(), face.vertex[i].getZ());
				}

				gl.glEnd();

			}
		
		}

	}
	
	private Texture getTexture(String map){
		
		Texture texture = textureMap.get(map);
		
		if(texture==null){
			
			System.out.println("Trying to load: "+map);
			
			texture = new Texture(ImageLoader.getInstance().getImage(map,true));
			textureMap.put(map, texture);
			
		}
		
		return texture;
		
	}

	public void drawVertexes(GLAUX gl){
		
		double vsize = 0.015;
		
		List<Vector3f> vertices = vbo.getVertices();
		
		for(int i=0;i<vertices.size(); i++){

			if(vertexSelection.contains(i)){
				gl.glColor3i(0xff,0xff,0xff);
			}else{
				//gl.glColor3i(0xdd,0x88,0x55);
				gl.glColor3i(0x66,0x44,0x44);
			}

			//TODO Remove
			if(i==specialVertex){
				gl.glColor3i(0xff,0xff,0x00);
				vsize*=2;
			}

			gl.glPushMatrix();
			gl.glTranslated(vertices.get(i).getX(), vertices.get(i).getY(), vertices.get(i).getZ());
			gl.auxSolidCube(vsize);
			gl.glPopMatrix();
		}

	}

	protected void setTexture(GL gl, Texture texture){

		gl.glTexImage2D(GL.GL_TEXTURE_2D, 0, GL.GL_RGB, (int)texture.getW(), (int)texture.getH(), 0,
				GL.GL_RGB, GL.GL_UNSIGNED_BYTE, texture.getBytes());

		//gl.glTexGeni(GL.GL_S, GL.GL_TEXTURE_GEN_MODE, GL.GL_OBJECT_LINEAR);

		//int sgenIparams[] = {1, 1, 1, 0};

		//gl.glTexGeniv(GL.GL_S, GL.GL_OBJECT_PLANE, sgenIparams);

	}

	protected void setAlphaTexture(GL gl, Texture texture){


		//gl.glEnable(GL.GL_ALPHA_TEST);

		gl.glTexImage2D(GL.GL_TEXTURE_2D, 0, GL.GL_RGBA, (int)texture.getW(), (int)texture.getH(), 0,
				GL.GL_RGBA, GL.GL_UNSIGNED_BYTE, texture.getAlphaBytes());

		gl.glEnable(GL.GL_BLEND);
		gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);

	}

	public Set<Integer> getVertexSelection() {
		return vertexSelection;
	}

	public void setVertexSelection(Set<Integer> vertexSelection) {
		this.vertexSelection = vertexSelection;
	}

	public boolean isDrawTexture() {
		return drawTexture;
	}

	public void setDrawTexture(boolean drawTexture) {
		this.drawTexture = drawTexture;
	}

	public boolean isDrawFaces() {
		return drawFaces;
	}

	public void setDrawFaces(boolean drawFaces) {
		this.drawFaces = drawFaces;
	}

	public boolean isDrawVertices() {
		return drawVertices;
	}

	public void setDrawVertices(boolean drawVertices) {
		this.drawVertices = drawVertices;
	}

	public double getScale() {
		return scale;
	}

	public void setScale(double scale) {
		this.scale = scale;
	}

	public VBO getVbo() {
		return vbo;
	}

	public void setVbo(VBO vbo) {
		this.vbo = vbo;
	}
			
}