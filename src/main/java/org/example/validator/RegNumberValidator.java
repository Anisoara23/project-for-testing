package org.example.validator;

import java.util.regex.Pattern;

public class RegNumberValidator {

    private static final Pattern REG_NUMBER_PATTERN = Pattern.compile("^\\w{3}\\d{3}?");

    public static boolean isRegNumberValid(String regNumber) {
        if (regNumber == null) {
            throw new NullPointerException("Null reg number!");
        } else if (!REG_NUMBER_PATTERN.matcher(regNumber).matches()) {
            throw new IllegalArgumentException("Invalid reg number!");
        }
        return true;
    }
}
