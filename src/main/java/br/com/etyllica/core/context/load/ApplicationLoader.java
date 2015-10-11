package br.com.etyllica.core.context.load;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import br.com.etyllica.core.context.Context;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class ApplicationLoader implements ApplicationLoadListener {

	private Loader loader = new Loader();
	private Updater updater = new Updater();

	private Context application;
	private LoaderListener listener;
	private LoadApplication loadApplication;
	
	private boolean called = false;
	private ScheduledExecutorService loadExecutor;
	private Future<?> future;
	
	private static final int UPDATE_INTERVAL = 10;
	
	private String lastText = "";
	private float lastLoading = 0;
	
	public ApplicationLoader() {
		super();
	}

	public void loadApplication() {
		lastText = "";
		lastLoading = 0;
		
		loadExecutor = Executors.newScheduledThreadPool(2);
		
		future = loadExecutor.submit(loader);
		
		loadExecutor.scheduleAtFixedRate(updater, 0, UPDATE_INTERVAL, TimeUnit.MILLISECONDS);
	}

	private class Loader implements Runnable {

		public void run() {
			called = false;

			//Set the listener to listen to loaded event
			application.setLoadListener(ApplicationLoader.this);
			application.startLoad();
		}
	}

	private class Updater implements Runnable {

		public void run() {
			if(!called) {
				
				if(!application.isLoaded()) {
					notifyTextChanged();
					notifyLoadingChanged();
					
					getError();
				}
				
			} else {
				//Notify window
				finishLoading();
			}
		}
	}
	
	private void notifyTextChanged() {
		String info = application.getLoadingInfo();
		
		if(!lastText.equals(info)) {
			lastText = info;
			loadApplication.onChangeText(info);
		}
	}
	
	private void notifyLoadingChanged() {
		float loading = application.getLoading();
		
		if(lastLoading != loading) {
			lastLoading = loading;
			loadApplication.onChangeLoad(loading);
		}
	}

	public Context getApplication() {
		return application;
	}

	public void setApplication(Context application) {
		this.application = application;
	}

	public LoadApplication getLoadApplication() {
		return loadApplication;
	}

	public void setLoadApplication(LoadApplication loadApplication) {
		this.loadApplication = loadApplication;
	}

	@Override
	public void onApplicationLoaded() {
		called = true;
	}

	protected void getError() {
		try {
			future.get(UPDATE_INTERVAL/2, TimeUnit.MILLISECONDS);
		} catch (ExecutionException e) {
			Throwable cause = e.getCause();
			cause.printStackTrace();
			finishLoading();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			//Timeout no error until here
			//e.printStackTrace();
		}
	}

	private void finishLoading() {
		listener.onLoad(application);
		called = true;
		loadExecutor.shutdownNow();
	}

	public void setListener(LoaderListener listener) {
		this.listener = listener;
	}

}