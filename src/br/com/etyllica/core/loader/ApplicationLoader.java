package br.com.etyllica.core.loader;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import br.com.etyllica.core.application.Application;
import br.com.etyllica.core.application.InternalApplication;
import br.com.etyllica.core.application.LoadApplication;
import br.com.etyllica.gui.Window;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class ApplicationLoader implements Runnable{
	
	private ExecutorService loadExecutor;
	
	private Window window;
	
	private Application application;
	
	private LoadApplication loadApplication;

	public ApplicationLoader(){
		super();
	}
		
	public void loadApplication(){
		
		loadExecutor = Executors.newSingleThreadExecutor();

		loadExecutor.execute(new Runnable() {
			
			@Override
			public void run() {
				application.setSessionMap(window.getSessionMap());
				application.load();
			}
			
		});
				
		loadExecutor.shutdown();
		
	}

	public void run(){
				
		while(application.getLoading()<100){
			loadApplication.setText(application.getLoadingPhrase(), application.getLoading());
		}
		
		window.setApplication(application);
		
		window.restartWindow();

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