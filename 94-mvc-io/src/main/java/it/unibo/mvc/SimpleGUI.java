package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private final JFrame frame = new JFrame();
    Controller controller = new SimpleController();

    public SimpleGUI() {
        JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());

        JTextField field = new JTextField();
        canvas.add(field, BorderLayout.NORTH);

        JTextArea area = new JTextArea();
        area.setEditable(false);
        canvas.add(area, BorderLayout.CENTER);

        
        JPanel south = new JPanel();
        JButton print = new JButton("Print");
        south.add(print, BorderLayout.WEST);
        JButton history = new JButton("Show history");
        south.add(history, BorderLayout.EAST);
        canvas.add(south, BorderLayout.SOUTH);

        print.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setString(field.getText());
                controller.printString();
            }
            
        });

        history.addActionListener(new ActionListener() {
            String allString = "";

            @Override
            public void actionPerformed(ActionEvent e) {
                for (String string : controller.getAllStrings()) {
                    allString = allString + string + "\n";
                }
                area.setText(allString);
            }
        });

        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    void display() {
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new SimpleGUI().display();
    }
}
