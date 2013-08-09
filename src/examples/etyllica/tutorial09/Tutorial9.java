package examples.etyllica.tutorial09;

import br.com.etyllica.Etyllica;
import examples.etyllica.tutorial09.application.SimpleGuiExample;

public class Tutorial9 extends Etyllica {

	private static final long serialVersionUID = 1L;

	public Tutorial9() {
		super(640, 480);
	}
	
	@Override
	public void startGame() {
		
		//Configuration.getInstance().setTheme(new EtyllicMonoTheme());
		
		setMainApplication(new SimpleGuiExample(w,h));
	}
	
}
