package br.com.etyllica.debug;

public class Tester {

	private static boolean debug = true;
	
	public static void test(boolean value, boolean should){
		
		if(debug){
			
			if(should==value){
				Logger.log(".");	
			}else{
				Logger.log("F");
			}
		}
		
	}
	
}
