package lab3;

import lab3.controller.ConsoleView;
import lab3.gui.GuiController;

/**
 * Main class where program starts.
 */
public class StartApp {

    /**
     * Start point of the application
     * @param args command line arguments
     */
    public static void main(String[] args) throws Exception {
        ConsoleView menu =  new ConsoleView();
        //menu.consoleApp();
        GuiController gc = new GuiController();
        GuiController.main(null);
    }
}
