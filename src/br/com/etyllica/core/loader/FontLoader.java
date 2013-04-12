package br.com.etyllica.core.loader;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class FontLoader extends Loader{

	private static FontLoader instance = null;

	private Map<String, Font>fonts = new HashMap<String, Font>();

	private FontLoader(){
		super();
		
		folder = "res/fonts/";
	}

	public static FontLoader getInstance() {
		if(instance==null){
			instance = new FontLoader();
		}

		return instance;
	}

	public Font getFont(String fontName, float size){
		
		Font font = loadFont(fontName);
		
		
		return font.deriveFont(size);
		
	}
		
	public Font loadFont(String fontName){

		if(!fonts.containsKey(fontName)){

			String diretorio = folder+fontName;
			
			URL dir = null;
			
			try {
				dir = new URL(url, diretorio);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			
			try {
				Font font = Font.createFont( Font.TRUETYPE_FONT, dir.openStream());
				fonts.put(fontName, font);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (FontFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		return fonts.get(fontName);

	}
	
}
