package br.com.etyllica.util.concurrency;

import java.util.ArrayList;
import java.util.List;

public class ConcurrentList<T> {

	private List<T> list;
	
	private List<T> alternativeList;
	
	private boolean locked = false;
	
	public ConcurrentList() {
		super();
		
		list = new ArrayList<T>();
		alternativeList = new ArrayList<T>();
	}
	
	public void add(T t) {
		if(!locked) {
			list.add(t);
		} else {
			alternativeList.add(t);
		}
	}
	
	public List<T> lock() {
		locked = true;
		
		return list;
	}
	
	public void unlock() {
		list.clear();
		
		locked = false;
		
		if(!alternativeList.isEmpty()) {
			list.addAll(alternativeList);
			alternativeList.clear();
		}
	}

	public List<T> getList() {
		return list;
	}

	public void clear() {
		list.clear();
	}

	public boolean isEmpty() {
		return list.isEmpty() && alternativeList.isEmpty();
	}
		
}