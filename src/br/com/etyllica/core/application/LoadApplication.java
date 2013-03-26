package br.com.etyllica.core.application;


/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public abstract class LoadApplication extends Application{

	protected String frase = "Carregando...";
	
	protected String porcent = "0%";

	protected int fill = 0;
	
	public LoadApplication(int x, int y, int w, int h) {
		super(x,y,w,h);
				
		load();
	}

	public abstract void setText(String frase, int andamento);

}