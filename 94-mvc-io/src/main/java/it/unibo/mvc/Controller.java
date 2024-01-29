package it.unibo.mvc;

import java.util.List;

/**
 *
 */
public interface Controller {
    void setString(String input);

    String getString();

    List<String> getAllStrings();
    
    void printString();
}
