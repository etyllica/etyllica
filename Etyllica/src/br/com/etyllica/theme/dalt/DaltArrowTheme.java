package br.com.etyllica.theme.dalt;

import br.com.etyllica.theme.dalt.arrow.DiagonalArrow;
import br.com.etyllica.theme.dalt.arrow.InvertedDiagonalArrow;
import br.com.etyllica.theme.dalt.arrow.NormalArrow;
import br.com.etyllica.theme.dalt.arrow.TextArrow;
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
		
		normalArrow = new NormalArrow(arrowSize);
		clickArrow = new NormalArrow(arrowSize);
		linkArrow = new NormalArrow(arrowSize);
		helpArrow = new NormalArrow(arrowSize);
		textArrow = new TextArrow(arrowSize);
		
		waitArrow = new WaitingArrow(arrowSize);
		
		horizontalArrow = new NormalArrow(arrowSize);
		verticalArrow = new NormalArrow(arrowSize);
		diagonalArrow = new DiagonalArrow(arrowSize);
		invertedDiagonalArrow = new InvertedDiagonalArrow(arrowSize);
		
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
