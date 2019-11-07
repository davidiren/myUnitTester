import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MySwingWorker extends SwingWorker {

    private String testname;
    private JTextField textField;

    public MySwingWorker(String s, JTextField tf){
        this.testname = s;
        this.textField = tf;

    }

    @Override
    protected ArrayList<String> doInBackground(){
        ArrayList<String> s;
        FindTests ft = new FindTests();
        s = new ArrayList<>(ft.findTests(testname));

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

            }

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
