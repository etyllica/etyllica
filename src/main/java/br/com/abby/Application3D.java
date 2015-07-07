package br.com.abby;

import org.jgl.GL;
import org.jgl.GLAUX;

import br.com.abby.light.Lamp;
import br.com.abby.linear.Box3D;
import br.com.abby.linear.Polygon3D;
import br.com.abby.material.Texture;
import br.com.abby.util.CameraGL;
import br.com.etyllica.linear.Point3D;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public abstract class Application3D extends GLAUX {

	public Application3D(int w, int h) {
		super(w,h);
	}

	protected void desenhaPoligono(Polygon3D polygon) {
		
		glPushMatrix();
		glLoadIdentity();
		glColor3d(polygon.getColor().getRed()/0xff,polygon.getColor().getGreen()/0xff,polygon.getColor().getBlue()/0xff);
		glTranslated(polygon.getX(), polygon.getY(), polygon.getZ());

		//Consertar!
		glRotated(polygon.getAngleX(), 1,0,0);
		glRotated(polygon.getAngleY(), 0,1,0);		
		glRotated(polygon.getAngleZ(), 0,0,1);

		glBegin(GL_QUADS);

		double x,y,z;

		for(int i=0;i<polygon.getVertices().size();i++){
			x = polygon.getVertices().get(i).getX();
			y = polygon.getVertices().get(i).getY();
			z = polygon.getVertices().get(i).getZ();
			glVertex3d(x,y,z);
		}
		glEnd();

		glPopMatrix();
	}


	protected void desenhaCaixa(Box3D box){
		glPushMatrix();
		//glLoadIdentity();
		glColor3d(box.getColor().getRed()/0xff,box.getColor().getGreen()/0xff,box.getColor().getBlue()/0xff);
		glTranslated(box.getX(), box.getY(), box.getZ());
		glRotated(box.getAngleZ(), 0.0, 0.0, 1.0);
		glRotated(box.getAngleY(), 0.0, 1.0, 0.0);
		auxSolidBox(box.getAltura(), box.getLargura(), box.getProfundidade());
		glPopMatrix();
	}

	protected void setLamp(Lamp lamp){

		float light_position[] = {(float)lamp.getX(),(float)lamp.getY(), (float)lamp.getZ(), 0};

		glLightfv (GL_LIGHT0, GL_POSITION, light_position);

		glEnable (GL_LIGHTING);
		glEnable (GL_LIGHT0);

		glColorMaterial (GL_FRONT, GL_DIFFUSE);
		glEnable (GL_COLOR_MATERIAL);
	}

	protected void lookatCamera(CameraGL cam){
		Point3D target = cam.getTarget();

		gluLookAt(cam.getX(), cam.getY(), cam.getZ(),target.getX(),target.getY(),target.getZ(),0,0,1);
	}

	//Texture routines
	protected void enableTextureDefault(){

		glShadeModel (GL_FLAT);
		
		glTexEnvf(GL.GL_TEXTURE_ENV, GL.GL_TEXTURE_ENV_MODE, GL.GL_MODULATE);
		glTexParameteri (GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);
		glTexParameteri (GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);

		glTexParameteri (GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_S, GL.GL_REPEAT);
		glTexParameteri (GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_T, GL.GL_REPEAT);


		glEnable (GL.GL_DEPTH_TEST);
		glDepthFunc (GL.GL_LESS);
		
		glEnable (GL.GL_TEXTURE_2D);
		
		glEnable (GL.GL_CULL_FACE);
		glCullFace (GL.GL_BACK);		
		
	}

	protected void enableTextureNoRepeat(){

	}

	protected void setTexture(Texture texture){
		
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, (int)texture.getW(), (int)texture.getH(), 0,
				GL_RGB, GL_UNSIGNED_BYTE, texture.getBytes());

		//glTexGeni(GL_S, GL_TEXTURE_GEN_MODE, GL_OBJECT_LINEAR);

		//int sgenIparams[] = {1, 1, 1, 0};

		//glTexGeniv(GL_S, GL_OBJECT_PLANE, sgenIparams);

	}
	protected void setAlphaTexture(Texture texture){
		
				
		//glEnable(GL_ALPHA_TEST);
		
		
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, (int)texture.getW(), (int)texture.getH(), 0,
				GL_RGBA, GL_UNSIGNED_BYTE, texture.getAlphaBytes());
		
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
	}
	
	protected int[] getViewPort() {

		int viewport[] = new int[4];
		glGetIntegerv(GL.GL_VIEWPORT, viewport);

		return viewport;
	}

	protected double[] getModelView() {

		double modelView[] = new double[16];
		glGetDoublev(GL.GL_MODELVIEW_MATRIX, modelView);

		return modelView;
	}

	protected double[] getProjection() {

		double projection[] = new double[16];

		glGetDoublev(GL.GL_PROJECTION_MATRIX, projection);

		return projection;
	}
	
}
