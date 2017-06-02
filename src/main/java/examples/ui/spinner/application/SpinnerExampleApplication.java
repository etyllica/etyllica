package examples.ui.spinner.application;

import java.util.ArrayList;
import java.util.List;

import br.com.etyllica.core.context.Application;
import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.core.ui.UI;
import br.com.etyllica.gui.spinner.IntegerSpinner;
import br.com.etyllica.gui.spinner.StringSpinner;

public class SpinnerExampleApplication extends Application{

	public SpinnerExampleApplication(int w, int h) {
		super(w, h);
	}

	@Override
	public void load() {
				
		IntegerSpinner spinnerMaxPlayers = new IntegerSpinner(280, 100, 200, 40);
		spinnerMaxPlayers.setValue(1);
		spinnerMaxPlayers.setMaxValue(4);
		spinnerMaxPlayers.setMinValue(1);
		UI.add(spinnerMaxPlayers);
		
		List<String> options = new ArrayList<String>();
		options.add("Forest");
		options.add("Mountain");
		options.add("Snow");		
		
		StringSpinner spinnerSelectMap = new StringSpinner(280, 160, 200, 40);
		spinnerSelectMap.setOptions(options);
		UI.add(spinnerSelectMap);
		
		loading = 100;
	}

	@Override
	public void draw(Graphics g) {
		
	}
}
