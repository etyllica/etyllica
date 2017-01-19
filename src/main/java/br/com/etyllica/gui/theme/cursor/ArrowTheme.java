package br.com.etyllica.gui.theme.cursor;

import br.com.etyllica.gui.theme.cursor.arrow.MouseArrow;


public interface ArrowTheme {

	public MouseArrow getNormalArrow();
	
	public MouseArrow getClickArrow();
	
	public MouseArrow getLinkArrow();
	
	public MouseArrow getHelpArrow();
		
	public MouseArrow getTextArrow();
	
	public MouseArrow getWaitArrow();
	
	public MouseArrow getMoveArrow();
			
	public MouseArrow getHorizontalArrow();
	
	public MouseArrow getVerticalArrow();
	
	public MouseArrow getDiagonalArrow();
	
	public MouseArrow getInvertedDiagonalArrow();
	
}
