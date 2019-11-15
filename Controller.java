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
                gui.getTextField(), gui.getTextArea()));
        gui.getClear().addActionListener(new Clear(gui.getTextArea()));

        gui.show();
    }

}
