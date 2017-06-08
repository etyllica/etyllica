package br.com.etyllica.core.loader.image;

/**
 * 
 * @author yuripourre
 *
 */

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class ICOReader implements ImageReader {

	private static final short ICO = 1;
	private static final short CUR = 2;
	
	private static ICOReader instance = null;
	
	public static ICOReader getInstance(){
		if(instance==null){
			instance = new ICOReader();
		}

		return instance;
	}
	
	private class ICOHeader{
		
		private short	reserved;
		private short	imageType;
		private short	images;
		
		ICOHeader(DataInputStream in) throws IOException{
			
			reserved = in.readShort();
			System.out.println("Reserved = "+reserved);
			
			ByteBuffer bbType = ByteBuffer.allocate(2);
			bbType.order(ByteOrder.LITTLE_ENDIAN);
			bbType.put(in.readByte());
			bbType.put(in.readByte());
			imageType = bbType.getShort(0);
			
			printImageType(imageType);
			
			ByteBuffer bbImages = ByteBuffer.allocate(2);
			bbImages.order(ByteOrder.LITTLE_ENDIAN);
			bbImages.put(in.readByte());
			bbImages.put(in.readByte());
			images = bbImages.getShort(0);
			System.out.println("images = "+images);

						
		}
			
	}
	
	private void printImageType(short imageType){
		
		if(imageType==ICO){
			System.out.println("imageType = ICO");	
		}
		else if(imageType==CUR){
			System.out.println("imageType = CUR");	
		}
		
	}
	
	private class ImageEntry{
		
		private byte width;
		private byte height;
		private byte paletteColors;
		private byte reserved;
		
		private short colorPlanes;
		private short bitsPerPixel;
		
		private int size;//in bytes
		private int offset;//in bytes
		
		private byte[] data;
		
		public ImageEntry(DataInputStream in) throws IOException{
		
			width = in.readByte();
			System.out.println("width = "+width);
			height = in.readByte();
			System.out.println("height = "+height);
			
			paletteColors = in.readByte();
			System.out.println("paletteColors = "+paletteColors);
			
			reserved = in.readByte();
			
			ByteBuffer bbColorPlanes = ByteBuffer.allocate(2);
			bbColorPlanes.order(ByteOrder.LITTLE_ENDIAN);
			bbColorPlanes.put(in.readByte());
			bbColorPlanes.put(in.readByte());
			colorPlanes = bbColorPlanes.getShort(0);
			System.out.println("colorPlanes = "+colorPlanes);
			
			ByteBuffer bbBitsPerPixel = ByteBuffer.allocate(2);
			bbBitsPerPixel.order(ByteOrder.LITTLE_ENDIAN);
			bbBitsPerPixel.put(in.readByte());
			bbBitsPerPixel.put(in.readByte());
			bitsPerPixel = bbBitsPerPixel.getShort(0);
			System.out.println("bitsPerPixel = "+bitsPerPixel);
						
			ByteBuffer bbSize = ByteBuffer.allocate(4);
			bbSize.order(ByteOrder.LITTLE_ENDIAN);
			bbSize.put(in.readByte());
			bbSize.put(in.readByte());
			bbSize.put(in.readByte());
			bbSize.put(in.readByte());
			size = bbSize.getInt(0);

			System.out.println("size = "+size);
						
			
			ByteBuffer bbOffset = ByteBuffer.allocate(4);
			bbOffset.order(ByteOrder.LITTLE_ENDIAN);
			bbOffset.put(in.readByte());
			bbOffset.put(in.readByte());
			bbOffset.put(in.readByte());
			bbOffset.put(in.readByte());
			offset = bbOffset.getInt(0);

			System.out.println("offset = "+offset);
			
			data = new byte[size];
			in.read(data);
			
			
			for(int i=0;i<colorPlanes;i++){
				//Loading color Planes
			}
						
		}
	}
	
	public BufferedImage loadImage(URL url) throws IOException{

		InputStream	f = url.openStream();

		DataInputStream in = new DataInputStream(f);

		ICOHeader header = new ICOHeader(in);
		
		ImageEntry ie = new ImageEntry(in);
		
		f.close();
		
		//InputStream is = new ByteArrayInputStream(fakeBMPHeader(ie.width, ie.height, ie.data));
		//BufferedImage bimg = ImageIO.read(is);
		
		//BufferedImage bimg = getBmpByteArray(ie.data, ie.width, ie.height, ie.bitsPerPixel);
		
		//return bimg;
		
		
		List<ImageEntry> entries = new ArrayList<ImageEntry>();
		
		for(int i=0;i<header.images;i++){
			
			System.out.println("loading image "+i);
					
			InputStream is = new ByteArrayInputStream(ie.data);
			BufferedImage bimg = ImageIO.read(in);
			
			return bimg;
			
			//entries.add(ie);
			
		}

		return null;		

	}
	
	private byte[] fakeBMPHeader(int width, int height, byte[] data){
		
		ByteBuffer bb = ByteBuffer.allocate(54+data.length);
		
		//Signature (2 bytes)
		bb.putShort((short)0x4D42);
		
		//Size (4 bytes)
		bb.putInt(data.length);
		
		//Reserved (2 bytes each)
		bb.putShort((short)0);
		bb.putShort((short)0);
		
		//Offset (4 bytes)
		bb.putShort((short)0);
		
		//Size of Header(4 bytes)
		bb.putInt(40);
		
		//Width (4 bytes)
		bb.putInt(width);
		
		//Height (4 bytes)
		bb.putInt(height);
		
		//Color planes (2 bytes)
		bb.putShort((short)1);
		
		//Bits per Pixel (2 bytes)
		bb.putShort((short)24);
		
		//Compression Type (4 bytes)
		bb.putInt(0); //0 = none, 1 = RLE-8, 2 = RLE-4
		
		//Image Size (in bytes)
		bb.putInt(data.length);
		
		//Horizontal Resolution (4 bytes)
		bb.putInt(0);
		
		//Vertical Resolution (4 bytes)
		bb.putInt(0);
		
		//Number of Colors
		bb.putInt(0);
		
		//Number of Important Colors
		bb.putInt(0);
		
		//From http://www.fastgraph.com/help/bmp_header_format.html
//		offset 		size		description
//  	0		2		signature, must be 4D42 hex
//  	2		4		size of BMP file in bytes (unreliable)
//  	6		2		reserved, must be zero
//  	8		2		reserved, must be zero
//     10		4		offset to start of image data in bytes
//     14		4		size of BITMAPINFOHEADER structure, must be 40
//     18		4		image width in pixels
//     22		4		image height in pixels
//     26		2		number of planes in the image, must be 1
//     28		2		number of bits per pixel (1, 4, 8, or 24)
//     30		4		compression type (0=none, 1=RLE-8, 2=RLE-4)
//     34		4		size of image data in bytes (including padding)
//     38		4		horizontal resolution in pixels per meter (unreliable)
//     42		4		vertical resolution in pixels per meter (unreliable)
//     46		4		number of colors in image, or zero
//     50		4		number of important colors, or zero
		
		bb.put(data);
		
		return bb.array();
		
	}
		
}