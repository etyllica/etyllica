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
	
	public static void test(int value, int should){
		
		if(debug){
			
			if(should==value){
				Logger.log(".");
			}else{
				Logger.log("F");
			}
		}
		
	}
	
	public static void test(float value, float should){
		
		if(debug){
			
			if(should==value){
				Logger.log(".");
			}else{
				Logger.log("F");
			}
		}
		
	}
	
	public static void test(double value, double should){
		
		if(debug){
			
			if(should==value){
				Logger.log(".");
			}else{
				Logger.log("F");
			}
		}
		
	}
	
	public static void test(String value, String should){
		
		if(debug){
			
			if(should.equals(value)){
				Logger.log(".");
			}else{
				Logger.log("F");
			}
		}
		
	}
	
}
