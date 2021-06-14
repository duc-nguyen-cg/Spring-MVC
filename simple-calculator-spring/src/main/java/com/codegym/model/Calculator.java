package com.codegym.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Calculator {
    public static final List<String> operations = new ArrayList<>(Arrays.asList("+", "-", "x", "/"));

    public static Float calculate(float first, float second, String operation){
        switch (operation){
            case "+":
                return first + second;
            case "-":
                return first - second;
            case "x":
                return first * second;
            case "/":
                if (second != 0){
                    return first / second;
                } else {
                    return null;
                }
            default:
                return null;
        }
    }
}
