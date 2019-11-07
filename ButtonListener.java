import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

/**
 *
 * @author Johan Eliasson (johane@cs.umu.se)
 * @version 10 nov 2013
 */
class ButtonListener implements ActionListener {

    private final JTextField textField;

    public ButtonListener(JTextField textField) {
        this.textField = textField;
    }

    public void actionPerformed(ActionEvent e) {

        new MySwingWorker(textField.getName(), textField).run();
    }
}