/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simh.data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author tarlach
 */
public class ContextualParser {

    HashMap<String, String> regexLibrary;

    public ContextualParser(Path library) throws IOException {
        List<String> regexes;
        regexes = Files.readAllLines(library, null);
        for (String temp : regexes) {
            String[] temp2 = temp.split("\"=\"");
            regexLibrary.put(temp2[0].substring(1, temp2[0].length()), temp2[1].substring(0, temp2[1].length() - 1));
        }
    }

    public boolean match(String type, String raw) {
        if (regexLibrary.containsKey(type)) {
            if (raw.matches(regexLibrary.get(type))) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
