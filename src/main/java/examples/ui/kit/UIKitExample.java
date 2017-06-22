package examples.ui.kit;

import br.com.etyllica.Etyllica;
import br.com.etyllica.commons.context.Application;
import br.com.etyllica.commons.graphics.Color;
import br.com.etyllica.ui.theme.ThemeManager;
import br.com.etyllica.theme.etyllic.EtyllicTheme;
import examples.ui.kit.application.UIKitApplication;

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
		ThemeManager.getInstance().getTheme().setBaseColor(Color.DEEP_SKY_BLUE);
		ThemeManager.getInstance().getTheme().setTextColor(Color.WHITE);

		return new UIKitApplication(w,h);
	}

}
