package br.com.etyllica.theme.mouse;

import br.com.etyllica.theme.dalt.arrow.ImageMouseArrow;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class CustomArrowTheme extends ArrowThemeImpl {

	public CustomArrowTheme(String normalArrowPath) {
		
		normalArrow = new ImageMouseArrow(normalArrowPath);
		clickArrow = normalArrow;
		textArrow = normalArrow;
		
		loadingArrow = normalArrow;
		
		horizontalArrow = normalArrow;
		verticalArrow = normalArrow;
		diagonalArrow = normalArrow;
		invertedDiagonalArrow = normalArrow;
		
	}

	public void setNormalArrow(ImageMouseArrow normalArrow) {
		this.normalArrow = normalArrow;
	}

	public void setClickArrow(ImageMouseArrow clickArrow) {
		this.clickArrow = clickArrow;
	}

	public void setTextArrow(ImageMouseArrow textArrow) {
		this.textArrow = textArrow;
	}

	public void setLoadingArrow(ImageMouseArrow loadingArrow) {
		this.loadingArrow = loadingArrow;
	}

	public void setHorizontalArrow(ImageMouseArrow horizontalArrow) {
		this.horizontalArrow = horizontalArrow;
	}

	public void setVerticalArrow(ImageMouseArrow verticalArrow) {
		this.verticalArrow = verticalArrow;
	}

	public void setDiagonalArrow(ImageMouseArrow diagonalArrow) {
		this.diagonalArrow = diagonalArrow;
	}

	public void setInvertedDiagonalArrow(ImageMouseArrow invertedDiagonalArrow) {
		this.invertedDiagonalArrow = invertedDiagonalArrow;
	}
			
}
