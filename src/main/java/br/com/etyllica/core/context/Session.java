package br.com.etyllica.core.context;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Session {

	private Map<String, Object> map;
	
	public Session() {
		super();
		
		map = new HashMap<String, Object>();
	}
	
	public Session(int initialCapacity) {
		super();
		
		map = new HashMap<String, Object>(initialCapacity);
	}
	
	public boolean getAsBoolean(String param) {
		return Boolean.getBoolean(map.get(param).toString());		
	}
	
	public int getAsInt(String param) {
		return Integer.parseInt(map.get(param).toString());		
	}
	
	public String getAsString(String param) {
		return map.get(param).toString();		
	}
	
	public Object get(String param) {
		return map.get(param);		
	}
	
	public void put(String key, Object value) {
		map.put(key, value);
	}
	
}
