import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

//Exempel på ett enkelt gränssnitt.

/**
 * GUI class
 */
public class Body {

    private JFrame frame;

    private JButton runTest;
    private JButton clear;

    private JTextField textField;
    private JTextArea textArea;
    private JScrollPane scroll;


    /**
     * Constructor
     * @param title - Title on the program window
     */
    //Should only be called on EDT
    public Body(String title) {
        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Build panels
        JPanel upperPanel = buildUpperPanel();
        JPanel middlePanel = buildMiddlePanel();
        JPanel lowerPanel = buildLowerPanel();

        //Add panels to the frame
        frame.add(upperPanel, BorderLayout.NORTH);
        frame.add(middlePanel, BorderLayout.CENTER);
        frame.add(lowerPanel, BorderLayout.SOUTH);

        frame.setPreferredSize(new Dimension(700,600));
        frame.pack();


    }

    /**
     * Makes the GUI visible
     */
    //Should only be called on EDT
    public void show() {
        frame.setVisible(true);
    }

    /**
     * Creates the lower panel of the GUI
     * @return - the created lower panel
     */
    private JPanel buildLowerPanel() {
        JPanel lowerPanel = new JPanel();
        lowerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        clear = new JButton("Clear");
        lowerPanel.add(clear, BorderLayout.CENTER);

        return lowerPanel;
    }

    /**
     * Creates the middle panel of the GUI
     * @return - the created middle panel
     */
    private JPanel buildMiddlePanel() {
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new BorderLayout()/*(FlowLayout.CENTER)*/);
        textArea = new JTextArea();
        scroll = new JScrollPane(textArea);

        middlePanel.add(scroll);


        return middlePanel;
    }

    /**
     * Creates the upper panel of the GUI
     * @return - the created upper panel
     */
    private JPanel buildUpperPanel() {
        JPanel upperPanel = new JPanel();
        //upperPanel.setBorder(BorderFactory.createTitledBorder("Exempel"));
        upperPanel.setLayout(new BorderLayout());

        textField = new JTextField("Test1");
        upperPanel.add(textField, BorderLayout.CENTER);

        runTest = new JButton("Run Tests");

        upperPanel.add(runTest, BorderLayout.EAST);

        return upperPanel;
    }

    /**
     * GETTERS
     */
    public JTextField getTextField() {
        return textField;
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public JButton getRunTest() {
        return runTest;
    }

    public JButton getClear() {
        return clear;
    }

}
