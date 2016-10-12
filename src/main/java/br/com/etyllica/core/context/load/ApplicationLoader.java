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
 *
 */

public class ApplicationLoader {

	private Loader loader = new Loader();
	private Updater updater = new Updater();

	private Context application;
	private LoaderListener listener;
	private DefaultLoadApplication loadApplication;
	
	private boolean called = false;
	private ScheduledExecutorService loadExecutor;
	private Future<?> future;
	
	private static final int UPDATE_INTERVAL = 50;
	
	private String lastText = "";
	private float lastLoading = 0;
	
	public ApplicationLoader(int w, int h) {
		super();
		
		loadApplication = new GenericLoadApplication(0, 0, w, h);
		loadApplication.load();
	}

	public void loadApplication() {
		lastText = "";
		lastLoading = 0;
		
		loadExecutor = Executors.newScheduledThreadPool(2);
		
		future = loadExecutor.submit(loader);
		loadExecutor.scheduleAtFixedRate(updater, UPDATE_INTERVAL, UPDATE_INTERVAL, TimeUnit.MILLISECONDS);
	}

	private class Loader implements Runnable {

		public void run() {
			called = false;
			application.startLoad();
			called = true;
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
			//Ignore the Timeout Exception
			//e.printStackTrace();
		}
	}

	private void finishLoading() {
		listener.onLoad(application);
	}
	
	private void checkForLoadApplication(Context context) {
		DefaultLoadApplication loadApp = context.getLoadApplication();
		
		if(loadApp != null) {
			this.loadApplication = loadApp;	
		}
	}
	
	public DefaultLoadApplication reloadApplication(LoaderListener listener, Context context) {		
		context.setLoaded(false);
		checkForLoadApplication(context);
		
		this.listener = listener;
		this.application = context;
		
		loadApplication.load();
		loadApplication.setLoaded(true);
		loadApplication();
		return loadApplication;
	}

}