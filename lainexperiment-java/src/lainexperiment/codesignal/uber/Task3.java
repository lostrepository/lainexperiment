/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
package lainexperiment.codesignal.uber;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Stack;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 09/06/2020
 *
 * Problem: Text Editor
 * Status: partially accepted
 * 
 * Problem
 * 
 * Implement an editor which will support following commands:
 * 
 * - INSERT <string> -- append string to the current string
 * - DELETE -- deletes last char
 * - COPY <INDEX> -- copies substring of current string [INDEX, END)
 * to clipboard
 * - PASTE -- append clipboard to the current string
 * - UNDO -- undo the last operation
 * 
 * This editor should accept list of commands and return the
 * final string.
 * 
 * Input Format
 * 
 * List of operations
 * 
 * Output Format
 * 
 * Resulting string
 * 
 * Input
 * 
"INSERT aaa",
"COPY 0",
"INSERT bb",
"PASTE",
"UNDO",
"UNDO"
 * 
 * Output
 * 
aaa
 * 
 * }</pre>
 */
public class Task3 {

    Stack<String> history = new Stack<>();
    String clipboard = "";

    @FunctionalInterface
    static interface Operation {
        Optional<String> edit(String input, String args);
    }

    Optional<String> copy(String input, String args) {
        int s = Integer.parseInt(args);
        if (s >= input.length()) return Optional.empty();
        clipboard = input.substring(s);
        return Optional.empty();
    }

    Optional<String> undo(String input, String args) {
        if (history.size() > 1)
            history.pop();
        return Optional.empty();
    }

    Optional<String> paste(String input, String args) {
        return Optional.of(input + clipboard);
    }

    Optional<String> delete(String input, String args) {
        String res = input;
        if (!input.isEmpty())
            res = input.substring(0, input.length() - 1);
        return Optional.of(res);
    }

    Optional<String> insert(String input, String args) {
        return Optional.of(input + args);
    }
    
    String newTextEditor(String[] operations) {
        Map<String, Operation> m = new HashMap<>();
        m.put("INSERT", this::insert);
        m.put("COPY", this::copy);
        m.put("PASTE", this::paste);
        m.put("UNDO", this::undo);
        m.put("DELETE", this::delete);
        history.push("");
        for (String operation: operations) {
            String[] o = operation.split(" ");
            String args = o.length == 2? o[1]: null;
            Optional<String> result = m.get(o[0]).edit(history.peek(), args);
            result.ifPresent(history::push);
        }
        return history.peek();
    }

    @Test
    public void test() {
        assertEquals("aaa", newTextEditor(new String[] {"INSERT aaa"}));
        assertEquals("aa", newTextEditor(new String[] {
                "INSERT aaa",
                "DELETE"}));
        assertEquals("aaabbaaa", newTextEditor(new String[] {
                "INSERT aaa",
                "COPY 0",
                "INSERT bb",
                "PASTE"}));
        assertEquals("aaabb", newTextEditor(new String[] {
                "INSERT aaa",
                "COPY 0",
                "INSERT bb",
                "PASTE",
                "UNDO"}));
        assertEquals("aaa", newTextEditor(new String[] {
                "INSERT aaa",
                "COPY 0",
                "INSERT bb",
                "PASTE",
                "UNDO",
                "UNDO"}));
        
        assertEquals("CodeSignal", newTextEditor(new String[] {
                "INSERT Code", "INSERT Signal", "DELETE", "UNDO"}));
        assertEquals("DaDaDaDaaam", newTextEditor(new String[] {
                "INSERT Da",
                "COPY 0",
                "UNDO",
                "PASTE",
                "PASTE",
                "COPY 2",
                "PASTE",
                "PASTE",
                "DELETE",
                "INSERT aaam"}));
    }
    
}
