package br.com.etyllica.core.context.load;


/**
 * 
 * @author yuripourre
 *
 */

public interface LoadApplication {

	public void onChangeText(String phrase);
	
	public void onChangeLoad(float load);
	
	public void load();

}