package it.unibo.mvc;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 *
 */
public final class SimpleController implements Controller {

    private List<String> strings = new ArrayList<>();
    private int position = 0;

    @Override
    public void setString(String string) {
        if (string != null) {
            strings.add(string);
        } else {
            throw new IllegalArgumentException("The input string cannot be null");
        }
    }

    @Override
    public String getString() {
        if(position < strings.size()) {
            return strings.get(position);
        } else {
            throw new IllegalStateException("the next string is unset");
        }
    }

    @Override
    public List<String> getAllStrings() {
        List<String> result = new ArrayList<>();
        for (String string : strings) {
            result.add(string);
        }
        return result;
    }

    @Override
    public void printString() {
        if(position < strings.size()) {
            System.out.println(strings.get(position++));
        } else {
            throw new IllegalStateException("the current string is unset");
        }
    }

}
