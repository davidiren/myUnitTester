import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
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


public class Body {

    private JFrame frame;

    private JButton runTest;

    private JCheckBox disableCheckBox;

    private JTextField textField;


    //Should only be called on EDT
    public Body(String title) {
        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Build panels
        JPanel upperPanel = buildUpperPanel();
        JPanel middlePanel = buildMiddlePanel();
        JPanel lowerPanel = buildLowerPanel();

        // SetupListeners
        setupListeners();

        //Add panels to the frame
        frame.add(upperPanel, BorderLayout.NORTH);
        frame.add(middlePanel, BorderLayout.CENTER);
        frame.add(lowerPanel, BorderLayout.SOUTH);

        frame.pack();

    }

    //Should only be called on EDT
    public void show() {
        frame.setVisible(true);
    }

    private JPanel buildLowerPanel() {
        JPanel lowerPanel = new JPanel();
        lowerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        return lowerPanel;
    }

    private JPanel buildMiddlePanel() {
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JTextField text = new JTextField();

        middlePanel.add(text);

        return middlePanel;
    }

    private JPanel buildUpperPanel() {
        JPanel upperPanel = new JPanel();
        //upperPanel.setBorder(BorderFactory.createTitledBorder("Exempel"));
        upperPanel.setLayout(new BorderLayout());

        textField = new JTextField("Enter Testclass");
        upperPanel.add(textField, BorderLayout.CENTER);

        runTest = new JButton("Run Tests");

        upperPanel.add(runTest, BorderLayout.EAST);

        return upperPanel;
    }

    private void setupListeners(){
        runTest.addActionListener(new ButtonListener(textField));
    }

}
