package br.com.etyllica.core.loader;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import br.com.etyllica.core.application.Application;
import br.com.etyllica.core.application.DefaultLoadApplication;
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
				application.load();
			}
			
		});
		
		loadExecutor.shutdown();
		
	}

	public void run(){
		
		while(application.getLoading()<100){
			loadApplication.setText(application.getLoadingPhrase(), application.getLoading());
		}
		
		window.clearComponents();

		window.setApplication(application);

	}

	public Application getApplication() {
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
		
		this.loadApplication = window.getLoadApplication();

		this.loadApplication.load();
		
		window.setApplication(window.getLoadApplication());

	}
			
}