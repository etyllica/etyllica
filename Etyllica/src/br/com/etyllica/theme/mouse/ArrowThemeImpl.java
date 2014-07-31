package br.com.etyllica.theme.mouse;


public class ArrowThemeImpl implements ArrowTheme {

	protected MouseArrow normalArrow;
	protected MouseArrow clickArrow;
	protected MouseArrow textArrow;
	protected MouseArrow loadingArrow;
	protected MouseArrow horizontalArrow;
	protected MouseArrow verticalArrow;
	protected MouseArrow diagonalArrow;
	protected MouseArrow invertedDiagonalArrow;
	
	public ArrowThemeImpl() {
		super();
	}
	
	public MouseArrow getNormalArrow() {
		return normalArrow;
	}

	public MouseArrow getClickArrow() {
		return clickArrow;
	}

	public MouseArrow getTextArrow() {
		return textArrow;
	}

	public MouseArrow getLoadingArrow() {
		return loadingArrow;
	}
	
	public MouseArrow getHorizontalArrow() {
		return horizontalArrow;
	}

	public MouseArrow getVerticalArrow() {
		return verticalArrow;
	}

	public MouseArrow getDiagonalArrow() {
		return diagonalArrow;
	}

	public MouseArrow getInvertedDiagonalArrow() {
		return invertedDiagonalArrow;
	}
	
}
