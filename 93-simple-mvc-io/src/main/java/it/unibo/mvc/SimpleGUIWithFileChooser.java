package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    private final Controller controller = new Controller();

    private final JFrame frame = new JFrame();

    public SimpleGUIWithFileChooser() {
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());

        final JPanel north = new JPanel();
        final JTextField filePath = new JTextField(controller.getPathOfCurrentFile());
        filePath.setEditable(false);
        north.add(filePath, BorderLayout.CENTER);
        final JButton browse = new JButton("Browse...");
        north.add(browse, BorderLayout.LINE_END);

        final JTextArea text = new JTextArea();
        text.setToolTipText("Scrivi cio\' che vuoi salvare sul file...");
        canvas.add(text, BorderLayout.CENTER);
        final JButton save = new JButton("Save");
        canvas.add(save, BorderLayout.PAGE_END);

        browse.addActionListener(new ActionListener() {

            private JFileChooser fileChooser = new JFileChooser(System.getProperty("user.home"));
            

            @Override
            public void actionPerformed(ActionEvent e) {
                filePath.setText("choosing file...");
                final int result = fileChooser.showSaveDialog(filePath);
                if(result == JFileChooser.APPROVE_OPTION) {
                    controller.setCurrentFile(fileChooser.getSelectedFile());
                } else if(result == JFileChooser.CANCEL_OPTION) {}
                else {
                    JOptionPane.showMessageDialog(north, e, "Error!", result);
                }
                filePath.setText(controller.getPathOfCurrentFile());
            }
            
        });

        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.writeOnFile(text.getText());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            
        });

        canvas.add(north, BorderLayout.NORTH);

        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void display() {
        /*
         * Make the frame one fifth the resolution of the screen. This very method is
         * enough for a single screen setup. In case of multiple monitors, the
         * primary is selected. In order to deal coherently with multimonitor
         * setups, other facilities exist (see the Java documentation about this
         * issue). It is MUCH better than manually specify the size of a window
         * in pixel: it takes into account the current resolution.
         */
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 5 , sh / 5);
        /*
         * Instead of appearing at (0,0), upper left corner of the screen, this
         * flag makes the OS window manager take care of the default positioning
         * on screen. Results may vary, but it is generally the best choice.
         */

        frame.setLocationByPlatform(true);
        /*
         * OK, ready to push the frame onscreen
         */
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new SimpleGUIWithFileChooser().display();
    }
}
