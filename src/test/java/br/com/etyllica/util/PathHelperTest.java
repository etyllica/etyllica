package br.com.etyllica.util;

import org.junit.Assert;
import org.junit.Test;

public class PathHelperTest {

	@Test
	public void testUpperDirectory() {
		
		String path = "/a/b/c";
		
		Assert.assertEquals("/a/b/", PathHelper.upperDirectory(path));
	}
	
}
