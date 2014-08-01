package br.com.etyllica.theme.dalt;

import br.com.etyllica.theme.dalt.arrow.DefaultArrow;
import br.com.etyllica.theme.dalt.arrow.DefaultTextArrow;
import br.com.etyllica.theme.dalt.arrow.WaitingArrow;
import br.com.etyllica.theme.mouse.ArrowThemeImpl;
import br.com.etyllica.theme.mouse.MouseArrow;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class DaltArrowTheme extends ArrowThemeImpl {

	protected int arrowSize = 22;
	
	public DaltArrowTheme(){
		
		normalArrow = new DefaultArrow(arrowSize);
		clickArrow = new DefaultArrow(arrowSize);
		linkArrow = new DefaultArrow(arrowSize);
		helpArrow = new DefaultArrow(arrowSize);
		textArrow = new DefaultTextArrow(arrowSize);
		
		waitArrow = new WaitingArrow(arrowSize);
		
		horizontalArrow = new DefaultArrow(arrowSize);
		verticalArrow = new DefaultArrow(arrowSize);
		diagonalArrow = new DefaultArrow(arrowSize);
		invertedDiagonalArrow = new DefaultArrow(arrowSize);
		
	}

	public int getArrowSize() {
		return arrowSize;
	}

	public void setArrowSize(int arrowSize) {
		this.arrowSize = arrowSize;
	}

	public void setNormalArrow(MouseArrow normalArrow) {
		this.normalArrow = normalArrow;
	}

	public void setClickArrow(MouseArrow clickArrow) {
		this.clickArrow = clickArrow;
	}

	public void setTextArrow(MouseArrow textArrow) {
		this.textArrow = textArrow;
	}

	public void setWaitArrow(MouseArrow waitArrow) {
		this.waitArrow = waitArrow;
	}
	
	
}
