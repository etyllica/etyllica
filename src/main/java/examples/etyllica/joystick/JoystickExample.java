package examples.etyllica.joystick;

import br.com.etyllica.Etyllica;
import br.com.etyllica.commons.context.Application;
import br.com.etyllica.input.JoystickHandler;

public class JoystickExample extends Etyllica {

    private static final long serialVersionUID = 1L;

    public JoystickExample() {
        super(800, 600);
    }

    public static void main(String[] args) {
        JoystickExample app = new JoystickExample();
        app.init();
    }

    @Override
    public Application startApplication() {
        //initialSetup("../../../../../");
        //Searching for 5 joysticks
        JoystickHandler.getInstance().init(5);

        return new JoystickApplication(w, h);
    }

}
