import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 *
 * @author Johan Eliasson (johane@cs.umu.se)
 * @version 10 nov 2013
 */
class ButtonListener implements ActionListener {

    private final JTextField textField;
    private final JTextArea textArea;

    public ButtonListener(JTextField tf, JTextArea ta) {
        this.textField = tf;
        this.textArea = ta;
    }

    public void actionPerformed(ActionEvent e) {

        new MySwingWorker(textField.getText(), textArea).execute();
    }
}