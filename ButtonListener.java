import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * Lister for the run tests button
 */
class ButtonListener implements ActionListener {

    private final JTextField textField;
    private final JTextArea textArea;

    /**
     * Constructor
     * @param tf - JTextField
     * @param ta - JTextArea
     */
    public ButtonListener(JTextField tf, JTextArea ta) {
        this.textField = tf;
        this.textArea = ta;
    }

    /**
     * Starts a MySwingWorker
     * @param e - Action event
     */
    public void actionPerformed(ActionEvent e) {

        new MySwingWorker(textField.getText(), textArea).execute();
    }
}