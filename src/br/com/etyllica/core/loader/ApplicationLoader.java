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
	
	private ExecutorService loadExecutor = Executors.newSingleThreadExecutor();
	
	private Application m;
	private LoadApplication load;

	public ApplicationLoader(Application m, LoadApplication load){
		this.m = m;
		this.load = load;				
	}
	
	public void loadApplication(){
		

		loadExecutor.execute(new Runnable() {
			
			@Override
			public void run() {
				m.load();
			}
			
		});
		
		loadExecutor.shutdown();
		
	}

	public void run(){
		
		while(m.getLoading()<100){
			load.setText(m.getLoadingPhrase(), m.getLoading());
		}

	}
	
}