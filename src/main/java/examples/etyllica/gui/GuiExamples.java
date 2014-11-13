package examples.etyllica.gui;

import br.com.etyllica.Etyllica;
import br.com.etyllica.context.Application;
import br.com.etyllica.core.Configuration;
import br.com.etyllica.theme.EtyllicTheme;
import br.com.etyllica.theme.ThemeManager;
import examples.etyllica.gui.simple.GeneralGuiExample;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class GuiExamples extends Etyllica {

	private static final long serialVersionUID = 1L;

	public GuiExamples() {
		super(640, 480);
	}
	
	@Override
	public Application startApplication() {
		initialSetup("../../../../../");
		
		//Enable Timer Click
		Configuration.getInstance().setTimerClick(true);
		
		//Change Theme
		ThemeManager.getInstance().setTheme(new EtyllicTheme());
		//ThemeManager.getInstance().setTheme(new EtyllicMonoTheme());
		
		return new GeneralGuiExample(w,h);
	}
	
}
