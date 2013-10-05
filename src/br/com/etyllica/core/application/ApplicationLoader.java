package br.com.etyllica.core.application;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import br.com.etyllica.gui.Window;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class ApplicationLoader{

	private ScheduledExecutorService loadExecutor;

	private Window window;

	private Application application;

	private LoadApplication loadApplication;

	public ApplicationLoader(){
		super();
	}

	public void loadApplication(){

		//loadExecutor = new ThreadPoolExecutor(2, 2, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(2));
		loadExecutor = Executors.newScheduledThreadPool(2);

		loadExecutor.execute(new Runnable() {

			@Override
			public void run() {

				application.setLocked(true);

				//application.setLoading(0);
				application.load();

				//This way, even if developer forget to put loading = 100;
				
			}

		});

		loadExecutor.scheduleWithFixedDelay(new Runnable() {

			@Override
            public void run() {

                String lastPhrase = application.getLoadingPhrase();
                
                int i=0;
               
                while(application.getLoading()<100){

                	//All this is really important to avoid executor lose focus
                    if(!lastPhrase.equals(application.getLoadingPhrase())){
                        loadApplication.setText(application.getLoadingPhrase(), application.getLoading());
                    }
                    
                    i++;
                }
                
                if(i>0){               
                	application.setLocked(false);
                }                

                window.setApplication(application);

                window.restartWindow();
                                               
            }

		}, 0, 20, TimeUnit.MILLISECONDS);				

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