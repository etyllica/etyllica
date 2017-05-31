package br.com.etyllica.gui;

import br.com.etyllica.gui.base.BaseTextField;
import br.com.etyllica.gui.base.BaseTextView;
import br.com.etyllica.gui.base.UIView;
import br.com.etyllica.gui.theme.ThemeManager;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class TextField extends UIView {

	private BaseTextField textField;

	public TextField(int x, int y, int w, int h) {
		super(x,y,w,h);

		this.textField = ThemeManager.getInstance().getTheme().createTextField(x, y, w, h);
		delegateView(textField);
	}

	public void rebuild() {
		BaseTextField view = ThemeManager.getInstance().getTheme().createTextField(x, y, w, h);
		view.copy(delegatedView);

		delegateView(view);
	}

	public String getText() {
		return textField.getText();
	}

	public void setText(String text) {
		textField.setText(text);
	}

	public void clearField() {
		textField.clearField();
	}

}