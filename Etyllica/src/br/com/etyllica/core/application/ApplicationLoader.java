package br.com.etyllica.core.application;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import br.com.etyllica.gui.Window;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class ApplicationLoader{

	private Window window;

	private Application application;

	private LoadApplication loadApplication;

	public ApplicationLoader(){
		super();
	}
	
	public void loadApplication(){
				
		final ExecutorService loadExecutor = Executors.newFixedThreadPool(2);

		loadExecutor.execute(new Runnable() {

			@Override
			public void run() {

				application.startLoad();
				
			}

		});

		loadExecutor.execute(new Runnable() {

			@Override
            public void run() {
                               
                while(application.isLocked()){

                        loadApplication.setText(application.getLoadingPhrase(), application.getLoading());
                }

                window.setApplication(application);
                                
                window.setLoaded(true);
                window.setLocked(false);

                loadExecutor.shutdown();
            }

		});

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