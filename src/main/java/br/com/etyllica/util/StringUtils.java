package br.com.etyllica.util;

public class StringUtils {

	public static final String NEW_LINE = "\n";
	public static final String WHITE_SPACE = " ";
	public static final String WINDOWS_SPACING = "%20";
	
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

	public static String formatFloat(float x, int i) {
		String decimals = Integer.toString(i);
		return String.format(java.util.Locale.US,"%."+decimals+"f", x); 
	}
	
}
