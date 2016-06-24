package examples.etyllica.gui;

import br.com.etyllica.EtyllicaApplet;
import br.com.etyllica.core.context.Application;
import br.com.etyllica.theme.ThemeManager;
import br.com.etyllica.theme.etyllic.EtyllicTheme;
import examples.etyllica.gui.simple.GeneralGuiExample;
import examples.etyllica.gui.slider.SliderExample;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class GuiExamples extends EtyllicaApplet {

	private static final long serialVersionUID = 1L;

	public GuiExamples() {
		super(640, 480);
	}
	
	@Override
	public Application startApplication() {
		initialSetup("../../../");
		
		//Enable Timer Click
		//Configuration.getInstance().setTimerClick(true);
		
		//Change Theme
		//ThemeManager.getInstance().setTheme(new EtyllicTheme());
		
		return new GeneralGuiExample(w,h);
		//return new TextFieldExample(w,h);
		//return new SliderExample(w,h);
	}
	
}
