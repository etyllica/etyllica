package examples.etyllica.gui;

import br.com.etyllica.Etyllica;
import br.com.etyllica.core.context.Application;
import examples.etyllica.gui.list.TableView;
import examples.etyllica.gui.simple.GeneralGuiExample;
import examples.etyllica.gui.textfield.TextFieldExample;
import examples.etyllica.gui.textview.TextViewExample;

/**
 * @author yuripourre
 * @license LGPLv3
 */

public class GuiExamples extends Etyllica {

    private static final long serialVersionUID = 1L;

    public GuiExamples() {
        super(640, 480);
    }

    public static void main(String[] args) {
        GuiExamples app = new GuiExamples();
        app.init();
    }

    @Override
    public Application startApplication() {
        initialSetup("../../../");

        //Enable Timer Click
        //Configuration.getInstance().setTimerClick(true);

        //Change Theme
        //ThemeManager.getInstance().setTheme(new EtyllicTheme());

        //return new GeneralGuiExample(w,h);
        //return new ComponentsList(w,h);
        return new TableView(w, h);
        //return new TextViewExample(w, h);
        //return new TextFieldExample(w,h);
        //return new SliderExample(w,h);
    }

}
