package br.com.etyllica.context.load;


/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public interface LoadApplication {

	public void onChangeText(String phrase);
	
	public void onChangeLoad(float load);
	
	public void load();

}