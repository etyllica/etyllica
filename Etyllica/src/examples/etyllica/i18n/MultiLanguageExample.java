package examples.etyllica.i18n;

import br.com.etyllica.Etyllica;
import br.com.etyllica.context.Application;

public class MultiLanguageExample extends Etyllica {

	private static final long serialVersionUID = 1L;

	public MultiLanguageExample() {
		super(800, 600);
	}

	@Override
	public Application startApplication() {
		initialSetup("../../../../");
		return new MultiLanguageApplication(w,h);
	}

}
