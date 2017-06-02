package examples.ui.kit;

import br.com.etyllica.Etyllica;
import br.com.etyllica.awt.SVGColor;
import br.com.etyllica.core.context.Application;
import br.com.etyllica.gui.theme.ThemeManager;
import br.com.etyllica.theme.etyllic.EtyllicTheme;
import examples.ui.kit.application.UIKitApplication;
import examples.ui.material.application.BlueButtonApplication;
import examples.ui.material.application.model.MaterialTheme;

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
		//ThemeManager.getInstance().setTheme(new MaterialTheme());
		ThemeManager.getInstance().getTheme().setShadow(false);
		ThemeManager.getInstance().getTheme().setBaseColor(SVGColor.DEEP_SKY_BLUE);
		ThemeManager.getInstance().getTheme().setTextColor(SVGColor.WHITE);

		return new UIKitApplication(w,h);
	}

}
