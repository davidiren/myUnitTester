/**
 * Author:      David Irén
 * CS-user:     id17din
 * Mail:        id17din@cs.umu.se
 *
 * Date:        18-11-2019
 */
import javax.swing.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Executes and processes the logic of the program,
 * then updates textarea in the gui
 */
public class MySwingWorker extends SwingWorker<ArrayList<String>, Integer> {

    private String testName;
    private JTextArea textArea;
    private JButton button;

    /**
     * Constructor
     * @param s - Text from a JTextField
     * @param ta - JTextArea to update
     * @param b  - JButton
     */
    public MySwingWorker(String s, JTextArea ta, JButton b){
        this.testName = s;
        this.textArea = ta;
        this.button = b;
    }

    /**
     * Processes the logic
     * @return -List with text that should be added to the JTextField
     */
    @Override
    protected ArrayList<String> doInBackground() {
        try {
            ArrayList<String> s;
            GenerateResults fa = new GenerateResults();
            s = new ArrayList<>(fa.findTests(testName));

            //Thread.sleep(5000);

            return s;
        }catch (NoClassDefFoundError e){
            ArrayList<String> s = new ArrayList<>();
            s.add("Double check spelling," +
                    " (Testclasses always start with an uppercase letter)");
            return s;
        }/* catch (InterruptedException e) {
            return new ArrayList<>();
        }*/
    }

    /**
     * Updates the JTextArea with text from the list
     * doInBackground returns
     */
    @Override
    protected void done(){

        textArea.selectAll();
        textArea.replaceSelection("");

        try {
            ArrayList<String> results = get();
            for (String s:results) {
                textArea.append(s+"\n");
            }

        } catch (InterruptedException | ExecutionException e) {
            // Do nothing
        }
        button.setEnabled(true);
    }
}
