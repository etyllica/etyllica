package br.com.etyllica.loader.image;

/**
 * PCX Image loader
 *
 *  include ported C code from:
 *
 *  PCXLoad
 *  Author	: Mustata Bogdan (LoneRunner)
 *  Contact	: lonerunner@planetquake.com (e-mail don't exist anymore)
 *
 * @author Michele Marcon
 * 
 * 
 * Updated by yuripourre to work with Etyllica Engine
 * 
 * This code had a minor bug and didn't open images smaller than 256x256 pixels.
 * 
 * I talked to a homonymous (Michele Marcon) who might be the owner.
 * 
 * See the e-mails below:
 * 
 *  2013/3/21 Yuri Pourre <yuripourre@gmail.com>
 *  
 *  Hi Michele,
 *  
 *  I found  a PCX Image Loader on Internet(sorry, I lost the link) and the author identify yourself just with Michele Marcon.
 *  
 *  I want to publish it under LGPL. So I'm looking for the authors.
 *  
 *  Reply me please.
 *  
 *  
 *  Best Regards
 *  
 *  Yuri 
 *  
 *  
 *  2013/3/22 Michele Marcon <michelemarcon77@gmail.com>
 *  
 *  How did you get my email?
 *  
 *  Well, I remember that about 10 or more years ago I did convert some C code to Java for importing Quake models... but a lot of time has passed, and I don't even remember if I finished the work and what I did with it. :(
 *  
 *  I probably am the author but I'm not sure...
 *  
 *  However, I'm not against publishing it under LGPL.
 *  
 *  
 *  2013/3/22 Yuri Pourre <yuripourre@gmail.com>
 *  
 *  Hi,
 *  
 *  I found your e-mail on google code, don't ask me how. Was a blind shot.
 *  
 *  I'm developing a Java Game Engine since 2009 http://yuripourre.github.com/etyllica/
 *  
 *  If you want to be assure I am attaching the class with few modifications, I lost the original code, sorry.
 *  
 *  
 *  Thank You for allow me to publish it as LGPL.
 *  
 *  Best Regards
 *
 *  Yuri
 * 
 * 
 * 2013/3/25 Michele Marcon <michelemarcon77@gmail.com>
 * This code is probably mine, but again, I'm not 100% sure.
 *     
 */

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class PCXReader implements ImageReader {
	
	private static class PCXHeader {
		
		private char	manufacturer;
		private char	version;
		private char	encoding;
		private char	bits_per_pixel;
		private short 	xmin,ymin,xmax,ymax;
		private short 	hres,vres;
		private int 	palette[]=new int[48];
		private char	reserved;
		private char	color_planes;
		private short  bytes_per_line;
		private short 	palette_type;
		private byte 	filler[]=new byte[58];
		private byte[]	data;

		PCXHeader(byte[] raw){
			manufacturer=(char)raw[0];
			version=(char)raw[1];
			encoding=(char)raw[2];
			bits_per_pixel=(char)raw[3];
			xmin=(short)((raw[4]+(raw[5]<<8))&0xff);
			ymin=(short)((raw[6]+(raw[7]<<8))&0xff);
			xmax=(short)((raw[8]+(raw[9]<<8))&0xff);
			ymax=(short)((raw[10]+(raw[11]<<8))&0xff);
			hres=(short)((raw[12]+(raw[13]<<8))&0xff);
			vres=(short)((raw[14]+(raw[15]<<8))&0xff);
			for (int i = 0; i < 48; i++)
				palette[i]=(raw[16+i]&0xff);
			reserved=(char)raw[64];
			color_planes=(char)raw[65];
			bytes_per_line=(short)((raw[66]+(raw[67]<<8))&0xff);
			palette_type=(short)((raw[68]+(raw[69]<<8))&0xff);
			for (int i = 0; i < 58; i++)
				filler[i]=raw[70+i];
			data=new byte[raw.length-128];
			for (int i = 0; i < raw.length-128; i++)
				data[i]=raw[128+i];
		}
	}

	public BufferedImage loadImage(URL url) throws IOException{

		//
		// load the file
		//

		InputStream	f = url.openStream();
		
		int len=f.available();
		
		byte[] buffer = new byte[len+1];
		
		buffer[len] = 0;

		for (int i = 0; i < len; i++){
			buffer[i] = (byte)f.read();
		}

		f.close();
		
		//
		// parse the PCX file
		//
		
		PCXHeader pcx = new PCXHeader(buffer);
		
		byte[] raw = pcx.data;

		if (pcx.manufacturer != 0x0a
				|| pcx.version != 5
				|| pcx.encoding != 1
				|| pcx.bits_per_pixel != 8
				|| pcx.xmax >= 640
				|| pcx.ymax >= 480)
		{ 
			System.err.println("Bad pcx file "+ url);
			return null;
		}

		int palette[] = new int[768];
		
		for (int i = 0; i < 768; i++){
			if ((len-128-768+i)<pcx.data.length){
				palette[i]=pcx.data[len-128-768+i]&0xff;
			}
		}

		int imageWidth = pcx.xmax+1;
		int imageHeight = pcx.ymax+1;

		int[] out = new int[(pcx.ymax+1) * (pcx.xmax+1)];

		int[] pic = out;
		int[] pix = out;

		int pixcount = 0;
		int rawcount = 0;
		
		int	dataByte, runLength;

		for (int y=0 ; y<= pcx.ymax ; y++, pixcount += pcx.xmax+1){

			for (int x=0 ; x<=pcx.xmax ; ){

				dataByte = raw[rawcount++];

				if((dataByte & 0xC0) == 0xC0){

					runLength = dataByte & 0x3F;
					dataByte = raw[rawcount++];
				}
				else{
					runLength = 1;
				}

				while(runLength-- > 0){
					pix[pixcount+x++] = dataByte&0xff;
				}
			}

		}

		if (pic == null || palette == null){
			return null;
		}

		byte[] imageData = new byte [(imageWidth+1)*(imageHeight+1)* 3];

		//convert to rgb format
		for (int k=0; k<(imageWidth*imageHeight); k++)
		{
			imageData[k * 3] = (byte)palette[pic[k] * 3 ];
			imageData[k * 3 + 1] = (byte)palette[pic[k] * 3 + 1];
			imageData[k * 3 + 2] = (byte)palette[pic[k] * 3 + 2];
		}

		//Convert to BufferedImage
		int pixel[] = new int[imageWidth*imageHeight*3];

		int z=0;
		int w=0;
		
		for (int i = 0; i < imageWidth; i++){
			for (int j = 0; j < imageHeight; j++){
				pixel[z++]=(0xff<<24)
						|((imageData[w++]&0x00ff)<<16)
						|((imageData[w++]&0x0000ff)<<8)
						|((imageData[w++]&0x000000ff));
			}
		}

		BufferedImage bimg = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);
		bimg.setRGB(0, 0, imageWidth,imageHeight, pixel, 0,imageWidth);

		return bimg;
	}

}