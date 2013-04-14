package br.com.etyllica.gui;

import java.util.ArrayList;
import java.util.List;

import br.com.etyllica.core.application.Application;
import br.com.etyllica.core.application.DefaultLoadApplication;
import br.com.etyllica.core.application.GenericLoadApplication;
import br.com.etyllica.core.application.SessionMap;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyboardEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.loader.ApplicationLoader;
import br.com.etyllica.core.video.Grafico;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Window extends GUIComponent implements Runnable{

	protected Application application;

	//TODO Important to back arrow
	protected List<Application> oldApplications = new ArrayList<Application>();

	protected SessionMap variaveis = new SessionMap();

	protected DefaultLoadApplication load;

	protected ApplicationLoader c;

	protected Application anotherApplication;

	public Window(int x, int y, int w, int h){
		super(x,y,w,h);

		//load = new LoadApplication(x,y,w,h);
		load = new GenericLoadApplication(x,y,w,h);
		//load.setBimg(new BufferedImage(w,h, BufferedImage.TYPE_INT_ARGB));
		//load.setBimg(new BufferedImage(w,h, BufferedImage.TYPE_INT_RGB));

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

	public void setMainApplication(Application application) {
		
		anotherApplication = application;
		anotherApplication.setSessionMap(variaveis);

		reload();
		
	}

	public void changeApplication(){

		anotherApplication = application.getReturnApplication();
		anotherApplication.setSessionMap(variaveis);

		reload();
	}

	protected void reload(){

		//load = new LoadApplication(m.getX(), m.getY(), m.getW(),m.getH());
		//load.setBimg(new BufferedImage(m.getW(), m.getH(), BufferedImage.TYPE_INT_RGB));
		load.load();
		application = load;
		add(application);

		c = new ApplicationLoader(anotherApplication,load);
		anotherApplication.load();
		
		new Thread(this).start();
	}

	@Override
	public void run() {

		c.run();				

		//components.remove(load);
		clearComponents();

		//m.setBimg(load.getBimg());

		setApplication(anotherApplication);

		remove(load);

	}

	public boolean isStillWantClose() {
		return stillWantClose;
	}

	@Override
	public GUIEvent updateKeyboard(KeyboardEvent event) {
		// TODO Auto-generated method stub
		return GUIEvent	.NONE;
	}

}
