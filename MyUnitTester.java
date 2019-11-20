/**
 * Author:      David Irén
 * CS-user:     id17din
 * Mail:        id17din@cs.umu.se
 *
 * Date:        18-11-2019
 */
import javax.swing.*;

/*
    MainClass
 */
public class MyUnitTester {
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
