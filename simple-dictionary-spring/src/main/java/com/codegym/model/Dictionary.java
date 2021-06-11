package com.codegym.model;

import java.util.*;

public class Dictionary {
    private static Map<String, String> dictionary;

    static {
        dictionary = new HashMap<>();
        dictionary.put("neko", "cat");
        dictionary.put("shinsekai", "New World");
        dictionary.put("arigato", "thank you");
    }

    public static String translate(String input){
        if (input.equals("")){
            return "";
        }
        for (Map.Entry<String, String> entry : dictionary.entrySet()) {
            if (input.equals(entry.getKey())) {
                return entry.getValue();
            } else if (input.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }
}
