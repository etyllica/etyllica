package br.com.etyllica.core.loader;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import br.com.etyllica.core.application.Application;
import br.com.etyllica.core.application.LoadApplication;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class ApplicationLoader{
	
	private ExecutorService loadExecutor;
	
	private Application application;
	
	private LoadApplication loadApplication;

	public ApplicationLoader(){
		super();
	}
	
	public ApplicationLoader(Application application, LoadApplication loadApplication){
		super();
		this.application = application;
		this.loadApplication = loadApplication;
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
		
}