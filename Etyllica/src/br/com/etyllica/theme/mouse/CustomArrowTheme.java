package br.com.etyllica.theme.mouse;

import br.com.etyllica.theme.dalt.arrow.ImageMouseArrow;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class CustomArrowTheme implements ArrowTheme {

	protected ImageMouseArrow normalArrow;
	protected ImageMouseArrow clickArrow;
	protected ImageMouseArrow textArrow;
	protected ImageMouseArrow loadingArrow;
	
	public CustomArrowTheme(String normalArrowPath) {
		
		normalArrow = new ImageMouseArrow(normalArrowPath);
		clickArrow = normalArrow;
		textArrow = normalArrow;
		
		loadingArrow = normalArrow;
		
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
		
}
