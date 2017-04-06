package br.com.etyllica.loader;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import br.com.etyllica.util.io.IOHelper;

/**
 * 
 * @author yuripourre
 *
 */

public class FontLoader extends LoaderImpl {

	private static FontLoader instance = null;

	private String[] systemFonts;
	
	private Map<String, Font> fonts = new HashMap<String, Font>();

	private FontLoader() {
		super();
		
		folder = "assets/fonts/";
	}

	public static FontLoader getInstance() {
		if (instance == null) {
			instance = new FontLoader();
		}

		return instance;
	}

	public Font getFont(String fontName, float size) {
		Font font = loadFont(fontName);
		
		return font.deriveFont(size);
	}
		
	public Font loadFont(String path, boolean absolute) {

		String fullPath = fullPath(path, absolute);
		
		if (!fonts.containsKey(fullPath)) {
			
			URL dir = null;
			
			if (!absolute) {
				try {
					dir = new URL(url, fullPath);
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				}	
			} else {
				
				if (!fullPath.startsWith(IOHelper.FILE_PREFIX)) {
					fullPath = IOHelper.FILE_PREFIX + fullPath;	
				}
				
				try {
					dir = new URL(fullPath);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			try {
				Font font = Font.createFont( Font.TRUETYPE_FONT, dir.openStream());
				fonts.put(fullPath, font);
				return font;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (FontFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return fonts.get(fullPath);
	}

	public String[] getSystemFonts() {
		return systemFonts;
	}

	public void setSystemFonts(String[] systemFonts) {
		this.systemFonts = systemFonts;
	}
	
	public void disposeFont(String fontName) {
		String fullPath = fullPath(fontName, false); 
		fonts.remove(fullPath);
	}

	public Font loadFont(String path) {
		return loadFont(path, false);
	}
}
