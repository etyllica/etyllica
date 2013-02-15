package br.com.etyllica.gui;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import br.com.etyllica.core.application.Application;
import br.com.etyllica.core.application.GenericLoadApplication;
import br.com.etyllica.core.application.LoadApplication;
import br.com.etyllica.core.application.SessionMap;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.loader.ApplicationLoader;
import br.com.etyllica.core.video.Grafico;

/**
 * 
 * @author mscythe
 * @license LGPLv3
 *
 */

public class Window extends GUIComponent implements Runnable{

	protected Application application;
	
	//TODO Important to back arrow
	protected List<Application> oldApplications = new ArrayList<Application>();

	protected SessionMap variaveis = new SessionMap();

	protected LoadApplication load;

	protected ApplicationLoader c;

	protected Application m;

	public Window(int x, int y, int w, int h){
		super(x,y,w,h);
		
		//load = new LoadApplication(x,y,w,h);
		load = new GenericLoadApplication(x,y,w,h);
		//load.setBimg(new BufferedImage(w,h, BufferedImage.TYPE_INT_ARGB));
		load.setBimg(new BufferedImage(w,h, BufferedImage.TYPE_INT_RGB));
		
	}

	@Override
	public GUIEvent updateMouse(PointerEvent event) {

		if(onMouse(event)){
			//TODO Melhorar evento de colisao com janela
			//return GUIEvent.MOUSE_OVER_UNCLICKABLE;	
		}

		//if(event.getKeyPressed(Tecla.TSK_ESC)){

			//System.out.println("Close Window@");

		//}


		return GUIEvent.NONE;
	}
	
	@Override
	public void update(GUIEvent event) {
		// TODO Auto-generated method stub
		if(event == GUIEvent.WINDOW_CLOSE){
			close();
		}
	}

	protected boolean stillWantClose = true;
	
	protected void close(){
		stillWantClose = true;
	}

	@Override
	public void draw(Grafico g) {
		// TODO Auto-generated method stub
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
		//application.carrega();

		clearComponents();
		
		add(load);
		//TODO Posso adicionar o closeButton
		//e o IconButton
		//Trazer restart de DefaultWindow
		add(application);
	
	}
	
	public boolean changeApplication(Application application){

		if(application!=this.application){

			m = application;
			m.setVariaveis(variaveis);

			recarrega();

			return true;
		}

		return false;
	}

	protected void recarrega(){

		//load = new LoadApplication(m.getX(), m.getY(), m.getW(),m.getH());
		//load.setBimg(new BufferedImage(m.getW(), m.getH(), BufferedImage.TYPE_INT_RGB));
		load.load();
		application = load;
		add(application);
		
		c = new ApplicationLoader(m);
		c.start();
		
		new Thread(this).start();
		
	}

	@Override
	public void run() {

		while(c.getLoaded()<100){
			load.setText(c.getCarregandoFrase(), c.getLoaded());
		}
		
		//components.remove(load);
		clearComponents();
		
		m.setBimg(load.getBimg());
		
		setApplication(m);

		remove(load);
		
	}

	public boolean isStillWantClose() {
		return stillWantClose;
	}

}
