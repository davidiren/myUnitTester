import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Executes and processes the logic of the program, then updates textarea in gui
 */
public class MySwingWorker extends SwingWorker<ArrayList<String>, Integer> {

    private String testName;
    private JTextArea textArea;

    /**
     * Constructor
     * @param s - Text from a JTextField
     * @param ta - JTextArea to update
     */
    public MySwingWorker(String s, JTextArea ta){
        this.testName = s;
        this.textArea = ta;

    }

    /**
     * Processes the logic
     * @return - List with text that should be added to the JTextField
     */
    @Override
    protected ArrayList<String> doInBackground() {
        try {
            ArrayList<String> s;
            FindTests fa = new FindTests();
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
     * Updates the JTextArea with text from the list doInBackground returns
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
            e.printStackTrace();
        } catch (NoClassDefFoundError e){
            System.out.println("Double check spelling (Testclasses always" +
                    " start with an uppercase letter)");
        }
    }
}
