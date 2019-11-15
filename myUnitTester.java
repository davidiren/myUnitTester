import javax.swing.*;

/*
    MainClass
 */
public class myUnitTester {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable(){

            @Override
            public void run() {
                Controller c = new Controller();
                c.setupGUI();
            }
        });
    }
}
