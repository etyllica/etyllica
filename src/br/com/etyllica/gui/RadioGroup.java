package br.com.etyllica.gui;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author mscythe
 * @license LGPLv3
 *
 */

public class RadioGroup {

	private List<Radio> radios;
	
	public RadioGroup(){
		super();
		
		radios = new ArrayList<Radio>();
	}
	
	public void addRadio(Radio radio){
		this.radios.add(radio);
		
		radio.setGroup(this);		
	}
	
	public void removeRadio(Radio radio){
		this.radios.remove(radio);
	}
	
	public void mark(Radio radio){
		for(Radio rad: radios){
			if(rad!=radio){
				if(rad.isChecked()){
					rad.setChecked(false);
				}
			}
		}
	}
	
	public String getValue(){
		for(Radio rad: radios){
			if(rad.isChecked()){
				return rad.getValue();
			}
		}
		
		return "";
	}
}
