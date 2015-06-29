package br.com.abby.writer;

import java.io.IOException;

import br.com.abby.vbo.VBO;

public interface VBOWriter {

	public void writeVBO(VBO vbo, String filename) throws IOException;
	
}
