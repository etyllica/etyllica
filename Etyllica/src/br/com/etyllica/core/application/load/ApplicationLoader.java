package br.com.etyllica.core.application.load;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import br.com.etyllica.core.application.Application;
import br.com.etyllica.core.application.InternalApplication;
import br.com.etyllica.gui.Window;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class ApplicationLoader{

	private Loader loader;
	private Updater updater;
	
	private Window window;

	private Application application;

	private LoadApplication loadApplication;

	public ApplicationLoader(){
		super();
	}

	final ScheduledExecutorService loadExecutor = Executors.newScheduledThreadPool(2);
	
	public void loadApplication(){

		loader = new Loader();
		updater = new Updater();
		
		loadExecutor.submit(loader);
		loadExecutor.scheduleAtFixedRate(updater, 0, 10, TimeUnit.MILLISECONDS);	

	}
	
	private class Loader implements Runnable{

		public void run() {
			application.startLoad();
		}

	}
	
	private class Updater implements Runnable{

		public void run() {
			
			
			while(application.isLocked()){
				
				if(application.isLocked()&&!window.isLoaded()){
					loadApplication.setText(application.getLoadingPhrase(), application.getLoading());
				}

			}

			window.setApplication(application);

			window.setLoaded(true);
			
		}

	}

	public InternalApplication getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public LoadApplication getLoadApplication() {
		return loadApplication;
	}

	public void setLoadApplication(LoadApplication loadApplication) {
		this.loadApplication = loadApplication;
	}

	public Window getWindow() {
		return window;
	}

	public void setWindow(Window window) {
		this.window = window;
	}

}