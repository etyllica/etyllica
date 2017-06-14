package examples.ui.simple;

import br.com.etyllica.Etyllica;
import br.com.etyllica.core.Configuration;
import br.com.etyllica.commons.context.Application;
import br.com.etyllica.ui.theme.ThemeManager;
import br.com.etyllica.theme.etyllic.EtyllicTheme;
import examples.ui.simple.UIExample.BackgroundColorChangerApplication;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class BackgroundColorChanger extends Etyllica {

	private static final long serialVersionUID = 1L;

	public BackgroundColorChanger() {
		super(640, 480);
	}

	public static void main(String[] args){
		BackgroundColorChanger example = new BackgroundColorChanger();
		example.init();
	}

	@Override
	public Application startApplication() {

		Configuration.getInstance().setTimerClick(true);
		
		ThemeManager.getInstance().setTheme(new EtyllicTheme());

		//ThemeManager.getInstance().getTheme().setTextColor(Color.black);
		//ThemeManager.getInstance().setTheme(new EtyllicMonoTheme());
		
		return new BackgroundColorChangerApplication(w,h);
	}
	
}
