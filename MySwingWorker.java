import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MySwingWorker extends SwingWorker<ArrayList<String>, Integer> {

    private String testName;
    private JTextArea textArea;

    public MySwingWorker(String s, JTextArea ta){
        this.testName = s;
        this.textArea = ta;

    }

    @Override
    protected ArrayList<String> doInBackground(){
        ArrayList<String> s;
        FindTests fa = new FindTests();
        s = new ArrayList<>(fa.findTests(testName));

        return s;
    }

    @Override
    protected void process(List chunks) {


    }

    @Override
    protected void done(){

        try {
            ArrayList<String> results = get();
            for (String s:results) {
                textArea.append(s+"\n");
            }

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
