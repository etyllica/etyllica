package examples.etyllica.tutorial09;

import br.com.etyllica.EtyllicaApplet;
import br.com.etyllica.core.Configuration;
import br.com.etyllica.core.context.Application;
import br.com.etyllica.theme.ThemeManager;
import br.com.etyllica.theme.etyllic.EtyllicTheme;
import examples.etyllica.tutorial09.application.SimpleGuiExample;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Tutorial9 extends EtyllicaApplet {

	private static final long serialVersionUID = 1L;

	public Tutorial9() {
		super(640, 480);
	}
	
	@Override
	public Application startApplication() {

		Configuration.getInstance().setTimerClick(true);
		
		ThemeManager.getInstance().setTheme(new EtyllicTheme());
		//ThemeManager.getInstance().setTheme(new EtyllicMonoTheme());
		
		return new SimpleGuiExample(w,h);
	}
	
}
