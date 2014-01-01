package examples.etyllica.tutorial09;

import br.com.etyllica.Etyllica;
import br.com.etyllica.core.Configuration;
import br.com.etyllica.theme.EtyllicTheme;
import br.com.etyllica.theme.ThemeManager;
import br.com.etyllica.theme.mono.EtyllicMonoTheme;
import examples.etyllica.tutorial09.application.SimpleGuiExample;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Tutorial9 extends Etyllica {

	private static final long serialVersionUID = 1L;

	public Tutorial9() {
		super(640, 480);
	}
	
	@Override
	public void startGame() {
		
		Configuration.getInstance().setTimerClick(true);
		
		ThemeManager.getInstance().setTheme(new EtyllicTheme());
		//ThemeManager.getInstance().setTheme(new EtyllicMonoTheme());
		
		setMainApplication(new SimpleGuiExample(w,h));
	}
	
}
