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

    private Body gui;
    private Clear clearButtonListner;
    private ButtonListener runButton;

    /**
     * Constructor
     */
    public Controller(){
        gui = new Body("My Unit Tester");
        clearButtonListner = new Clear(gui.getTextArea());
        runButton = new ButtonListener(gui.getTextField(), gui.getTextArea(), gui.getRunTest());
    }

    /**
     * Initializes the GUI and shows it
     */
    public void setupGUI(){
        gui.getRunTest().addActionListener(runButton);
        gui.getClear().addActionListener(clearButtonListner);

        gui.show();
    }

}
