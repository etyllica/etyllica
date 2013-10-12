package br.com.etyllica.core;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class SharedCoreTest {

	private int INITIAL_WIDTH = 20;
	private int INITIAL_HEIGHT = 20;
	
	private SharedCore core;
	
	@Before
	public void setUp(){
		core = new SharedCore(null,INITIAL_WIDTH,INITIAL_HEIGHT);
	}
	
	@Test
	public void testConstructor() {
		assertEquals(INITIAL_WIDTH,core.getW());
		assertEquals(INITIAL_HEIGHT,core.getH());
	}

}
