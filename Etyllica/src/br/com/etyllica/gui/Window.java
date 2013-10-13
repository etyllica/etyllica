package br.com.etyllica.gui;

import java.util.ArrayList;
import java.util.List;

import br.com.etyllica.core.application.Application;
import br.com.etyllica.core.application.InternalApplication;
import br.com.etyllica.core.application.SessionMap;
import br.com.etyllica.core.application.load.ApplicationLoader;
import br.com.etyllica.core.application.load.DefaultLoadApplication;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.video.Graphic;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Window extends GUIComponent{

	protected Application application;

	//TODO Change to Application backApplication
	protected List<InternalApplication> oldApplications = new ArrayList<InternalApplication>();

	protected SessionMap sessionMap = new SessionMap();

	//protected DefaultLoadApplication load;

	protected ApplicationLoader applicationLoader;

	protected boolean close = false;

	private boolean loaded = true;
	//private boolean locked = false;

	public Window(int x, int y, int w, int h){
		super(x,y,w,h);

		//load = new LoadApplication(x,y,w,h);
		//load = new GenericLoadApplication(x,y,w,h);
		//load.setBimg(new BufferedImage(w,h, BufferedImage.TYPE_INT_ARGB));
		//load.setBimg(new BufferedImage(w,h, BufferedImage.TYPE_INT_RGB));

		applicationLoader = new ApplicationLoader();
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

		if(event == GUIEvent.APPLICATION_CHANGED){
			//changeApplication();
			System.err.println("WINDOW - Not here anymore");
		}

	}

	@Override
	public void draw(Graphic g) {
		// TODO Auto-generated method stub
	}

	public Application getApplication() {
		return application;
	}

	public void restartWindow(){
		
	}

	public void setApplication(Application application) {
		this.application = application;

		clearComponents();
		add(application);

	}

	public void setLoadApplication(DefaultLoadApplication loadApplication) {
		clearComponents();
		add(loadApplication);
	}

	public void reload(Application application){
		
		if(loaded){
			
			loaded = false;

			DefaultLoadApplication load = application.getLoadApplication();
			load.load();
			setLoadApplication(load);

			applicationLoader.setWindow(this);
			applicationLoader.setApplication(application);
			applicationLoader.setLoadApplication(load);

			applicationLoader.loadApplication();
			
		}

	}

	public SessionMap getSessionMap() {
		return sessionMap;
	}

	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {

		//TODO Ctrl+F4...

		return GUIEvent	.NONE;
	}

	public void closeWindow(){
		setClose(true);
	}

	public boolean isClose() {
		return close;
	}

	public void setClose(boolean close) {
		this.close = close;
	}

	public boolean isLoaded() {
		return loaded;
	}

	public void setLoaded(boolean loaded) {
		this.loaded = loaded;
	}

}
