package br.com.etyllica.core.loader.image;
/**
 * Based on Adobe Specification
 * http://www.adobe.com/devnet-apps/photoshop/fileformatashtml/
 * 
 * @author mscythe
 *
 */


import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class PSDReader{
	
	private final String DEFAULT_SIGNATURE = "8BPS";
	private final short COLORMODE_BITMAP = 0;
	private final short COLORMODE_GRAYSCALE = 1;
	private final short COLORMODE_INDEXED = 2;
	private final short COLORMODE_RGB = 3;
	private final short COLORMODE_CMYK = 4;
	private final short COLORMODE_MULTICHANNEL = 7;
	private final short COLORMODE_DUOTONE = 8;
	private final short COLORMODE_LAB = 9;
	
	private static class PSDHeader{
		private char[] signature = new char[4];
		private short version;
		private char[] reserved = new char[6];
		
		private int height;
		private int width;
		private short depth;
		private short colorMode;
	}		
	
	/**
	 *Only indexed color and duotone (see the mode field in the File header section) have color mode data. For all other modes, this section is just the 4-byte length field, which is set to zero.
	 *
	 *Indexed color images: length is 768; color data contains the color table for the image, in non-interleaved order.
	 *
	 *Duotone images: color data contains the duotone specification (the format of which is not documented). Other applications that read Photoshop files can treat a duotone image as a gray image, and just preserve the contents of the duotone information when reading and writing the file.
	 *
	 */
	private static class ColorModeDataHeader{
		private static int lenght;
		private static byte[] colorModelData;
		
	}

	public static BufferedImage loadImage(URL url) throws IOException
	{
		
		return null;

	}

}