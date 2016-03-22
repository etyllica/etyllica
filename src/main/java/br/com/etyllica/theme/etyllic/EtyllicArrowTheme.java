package br.com.etyllica.theme.etyllic;

import br.com.etyllica.theme.etyllic.arrow.DiagonalArrow;
import br.com.etyllica.theme.etyllic.arrow.HorizontalArrow;
import br.com.etyllica.theme.etyllic.arrow.InvertedDiagonalArrow;
import br.com.etyllica.theme.etyllic.arrow.MoveArrow;
import br.com.etyllica.theme.etyllic.arrow.NormalArrow;
import br.com.etyllica.theme.etyllic.arrow.TextArrow;
import br.com.etyllica.theme.etyllic.arrow.VerticalArrow;
import br.com.etyllica.theme.etyllic.arrow.WaitingArrow;
import br.com.etyllica.theme.mouse.ArrowThemeImpl;
import br.com.etyllica.theme.mouse.arrow.MouseArrow;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class EtyllicArrowTheme extends ArrowThemeImpl {

	protected int arrowSize = 22;
	
	public EtyllicArrowTheme() {
		
		normalArrow = new NormalArrow(arrowSize);
		clickArrow = new NormalArrow(arrowSize);
		linkArrow = new NormalArrow(arrowSize);
		helpArrow = new NormalArrow(arrowSize);
		textArrow = new TextArrow(arrowSize);
		
		waitArrow = new WaitingArrow(arrowSize);
		
		moveArrow = new MoveArrow(arrowSize);
		
		horizontalArrow = new HorizontalArrow(arrowSize);
		verticalArrow = new VerticalArrow(arrowSize);		
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
