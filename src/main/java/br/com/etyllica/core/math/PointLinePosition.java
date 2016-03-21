package br.com.etyllica.core.math;

public enum PointLinePosition {

	ON_SEGMENT,

	/**
	 * +
	 * a---------b
	 * */
	LEFT,

	/**
	 * a---------b
	 * +
	 * */
	RIGHT,
	
	/** 
	 * + a---------b 
	 * */
	INFRONT_OF_A,
	
	/** 
	 * a---------b +
	 * */
	BEHIND_B,
	ERROR;
	
}
