/**
 * Author:      David Irén
 * CS-user:     id17din
 * Mail:        id17din@cs.umu.se
 *
 * Date:        18-11-2019
 */
/**
 * Creates action listeners for the buttons
 */
public class Controller {

    Body gui;

    /**
     * Constructor
     */
    public Controller(){
        gui = new Body("My Unit Tester");

    }

    /**
     * Initializes the GUI and shows it
     */
    public void setupGUI(){
        gui.getRunTest().addActionListener(new ButtonListener(
                gui.getTextField(), gui.getTextArea(), gui.getRunTest()));
        gui.getClear().addActionListener(new Clear(gui.getTextArea()));

        gui.show();
    }

}
