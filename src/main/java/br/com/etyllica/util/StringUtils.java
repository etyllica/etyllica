package br.com.etyllica.util;

public class StringUtils {

	public static int countOccurrences(String text, char match) {
		int count = 0;
		for(int i=0;i<text.length();i++) {
			if(text.charAt(i) == match) {
				count++;
			}
		}
		return count;
	}
	
	public static String fileExtension(String path) {
		int mid = path.lastIndexOf(".");
		String ext = path.substring(mid+1,path.length()).toLowerCase();
		return ext;
	}
	
}
