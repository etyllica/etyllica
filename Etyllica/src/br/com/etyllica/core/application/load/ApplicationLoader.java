package br.com.etyllica.core.application.load;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import br.com.etyllica.core.application.Application;
import br.com.etyllica.core.application.Context;
import br.com.etyllica.gui.Window;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class ApplicationLoader implements LoadListener{

	private Loader loader;
	private Updater updater;
	
	private Window window;

	private Application application;

	private LoadApplication loadApplication;

	private boolean called = false;
	
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
			called = false;
			application.setLoadListener(ApplicationLoader.this);
			application.startLoad();
		}

	}
	
	private class Updater implements Runnable{

		public void run() {
			
			//while(application.isLocked()){
			if(!called){
				
				if(!window.isLoaded()){
					loadApplication.setText(application.getLoadingPhrase(), application.getLoading());
				}

			}else{
				
				window.setApplication(application);

				window.setLoaded(true);
			}
			
		}

	}

	public Context getApplication() {
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

	@Override
	public void loaded() {
		called = true;
	}

}