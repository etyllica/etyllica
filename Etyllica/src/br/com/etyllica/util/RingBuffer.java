package br.com.etyllica.util;

import java.util.ArrayList;
import java.util.List;

public class RingBuffer<T> {
	
	private List<T> list;

	private Class<T> cls;

	private int usedSlot = 0;
	
    private int minimumSlots = 0;
	
	public RingBuffer(Class<T> cls) {
		super();

		this.cls = cls;

		list = new ArrayList<T>();

	}

	public T getSlot() {

		T slot;

		if(usedSlot < list.size() ) {

			slot = list.get(usedSlot);

		} else {

			slot = create(); 

			list.add(slot);
			
		}
		
		usedSlot++;

		return slot;

	}

	public void pack() {

		int lastPack = usedSlot+1;

		if(list.size() > lastPack && list.size() > minimumSlots ) {

			removeLast(list.size() - usedSlot);
		}

		usedSlot = 0;

	}

	private void removeLast(int capacity) {

		if(capacity > list.size())
			return;

		for(int i = 0; i < capacity; i++) {
			list.remove(0);
		}

	}

	private T create() {

		try{
			return cls.newInstance();
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	public synchronized List<T> getList() {
		return list.subList(0, usedSlot);
	}
	
	public int getMinimumSlots() {
		return minimumSlots;
	}

	public void setMinimumSlots(int minimumSlots) {
		this.minimumSlots = minimumSlots;
	}

	public int size() {

		//return list.size();
		return usedSlot;
	}

}
