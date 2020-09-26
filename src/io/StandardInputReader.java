package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StandardInputReader {

    public static String getInputString(String message) {
        System.out.print(message);
        String inputString = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                System.in));
        try {
            inputString = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException("Errors occurred", e);
        }
        return inputString;
    }

    public static int getInputInt(String message) throws NumberFormatException {
        return Integer.parseInt(getInputString(message));
    }

    public static double getInputDouble(String message) throws NumberFormatException {
        return Double.parseDouble(getInputString(message));
    }
}