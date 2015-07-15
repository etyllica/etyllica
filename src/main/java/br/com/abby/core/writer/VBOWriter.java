package br.com.abby.core.writer;

import java.io.IOException;

import br.com.abby.core.vbo.VBO;

public interface VBOWriter {

	public void writeVBO(VBO vbo, String filename) throws IOException;
	
}
