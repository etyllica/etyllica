package br.com.etyllica.core.loader;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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

public class ApplicationLoader{
	
	private ExecutorService loadExecutor;

	private Window window;

	private Application application;

	private LoadApplication loadApplication;

	public ApplicationLoader(){
		super();
	}

	public void loadApplication(){
		
		loadExecutor = new ThreadPoolExecutor(2, 2, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(2));
		//loadExecutor = Executors.newFixedThreadPool(2);

		loadExecutor.submit(new Runnable() {

			@Override
			public void run() {

				application.load();
				
			}

		});
		
		loadExecutor.execute(new Runnable() {


			@Override
			public void run() {

				while(application.getLoading()<100){
					loadApplication.setText(application.getLoadingPhrase(), application.getLoading());
				}

				application.setLocked(false);

				window.setApplication(application);

				window.restartWindow();
												
			}

		});				

	}
	
	public void cleanMemory(){
		loadExecutor.shutdownNow();
		System.gc();
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