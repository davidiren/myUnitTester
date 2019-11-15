import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listner for the "clear" button
 */
public class Clear implements ActionListener {

    private final JTextArea textArea;

    /**
     * Constructor
     * @param ta - JTextArea to clear
     */
    public Clear(JTextArea ta) {
        this.textArea = ta;
    }

    /**
     * Clears the JTextArea
     * @param e - Action event
     */
    public void actionPerformed(ActionEvent e) {

        textArea.selectAll();
        textArea.replaceSelection("");
        }
}
