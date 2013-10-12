package br.com.etyllica.debug;


public class Logger {
	
	private static boolean debug = true;
	
	public static void log(Object message){
		
		if(debug){
			System.out.println(message.toString());
		}
	}
	
	public static void logWithoutNewLine(Object message){
		
		if(debug){
			System.out.print(message.toString());
		}
	}

}
