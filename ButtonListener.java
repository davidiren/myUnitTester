/**
 * Author:      David Irén
 * CS-user:     id17din
 * Mail:        id17din@cs.umu.se
 *
 * Date:        18-11-2019
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * Lister for the run tests button
 */
class ButtonListener implements ActionListener {

    private final JTextField textField;
    private final JTextArea textArea;
    private final JButton button;

    /**
     * Constructor
     * @param tf - JTextField
     * @param ta - JTextArea
     * @param b  - JButton
     */
    public ButtonListener(JTextField tf, JTextArea ta, JButton b) {
        this.textField = tf;
        this.textArea = ta;
        this.button = b;
    }

    /**
     * Starts a MySwingWorker
     * @param e - Action event
     */
    public void actionPerformed(ActionEvent e) {
        button.setEnabled(false);
        new MySwingWorker(textField.getText(), textArea, button).execute();
    }
}