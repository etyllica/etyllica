package br.com.etyllica.core.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author mscythe
 * @license LGPLv3
 *
 */

public class GUIAction {
		
	private Object object;
	
	private String methodName = "";
	
	private List<Object> parameters = new ArrayList<Object>();

	/**
	 * Constructor to execute methods without parameters (parameters can be setted later)
	 * 
	 * @param component - GUIComponent that trigger the method
	 * @param event - GUIEvent that trigger the method
	 * @param object - object that have the component and the method to be executed
	 * @param methodName - the name of a public method form object
	 */
	public GUIAction(Object object, String methodName) {
		super();
		
		this.object = object;
		this.methodName = methodName;
	}
	
	public GUIAction(Object object, String methodName, List<Object> parameters){
		this(object, methodName);
		
		this.parameters.addAll(parameters);
	}
	
	public GUIAction(Object object, String methodName, Object ... parameters){
		this(object, methodName);
		
		for(Object obj: parameters){
			this.parameters.add(obj);	
		}
		
	}
		
	public void executeAction(){

		Method method = null;
		
		/**
		 * Classes of parameters
		 */
		Class<?>[] classes = null;
		Object[] values = null;
		
		if(!parameters.isEmpty()){
		
			classes = new Class<?>[parameters.size()];
			
			for(int i=0;i<parameters.size();i++){
				classes[i] = parameters.get(i).getClass();
			}
			
			values = parameters.toArray();
			
		}else{
			
			classes = new Class<?>[]{};
			
			values = new Object[]{};
		}
		
		//Creating method from Object Class.
		//Telling the parameters' classes
		 
		Class<?> cls = object.getClass();
		
		try {
			method = cls.getMethod(methodName, classes);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		
		
		//Invoking method with parameters
		
		try {
			method.invoke(object, values);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
	}

	public List<Object> getParameters() {
		return parameters;
	}

	public void setParameters(List<Object> parameters) {
		this.parameters = parameters;
	}
	
}
