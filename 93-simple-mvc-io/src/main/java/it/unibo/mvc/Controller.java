package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Application controller. Performs the I/O.
 */
public class Controller {
    private File currentFile;
    private final static String PATH = System.getProperty("user.home") + File.separator + "output.txt";

    public Controller(String path) {
        currentFile = new File(path);
    }

    public Controller() {
        this(PATH);
    }

    public void setCurrentFile(File file) {
        this.currentFile = file;
    }

    public File getCurrentFile() {
        return currentFile;
    }

    public String getPathOfCurrentFile() {
        return currentFile.getAbsolutePath();
    }

    public void writeOnFile(String input) throws IOException {
        PrintStream ps = new PrintStream(getPathOfCurrentFile());
        ps.println(input);
        ps.close();
    }
}
