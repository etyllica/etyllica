package examples.ui.kit;

import br.com.etyllica.Etyllica;
import br.com.etyllica.core.context.Application;
import br.com.etyllica.gui.theme.ThemeManager;
import br.com.etyllica.theme.etyllic.EtyllicTheme;
import examples.ui.kit.application.UIKitApplication;
import examples.ui.material.application.BlueButtonApplication;

public class UIKitExample extends Etyllica {

	private static final long serialVersionUID = 1L;

	public UIKitExample() {
		super(640, 480);
	}

	public static void main(String[] args){
		UIKitExample example = new UIKitExample();
		example.init();
	}

	@Override
	public Application startApplication() {
		ThemeManager.getInstance().setTheme(new EtyllicTheme());

		return new UIKitApplication(w,h);
	}

}
