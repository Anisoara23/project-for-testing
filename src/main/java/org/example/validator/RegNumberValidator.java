package org.example.validator;

import java.util.regex.Pattern;

public class RegNumberValidator {

    private static final Pattern REG_NUMBER_PATTERN = Pattern.compile("^\\w{3}\\d{3}?");

    public static boolean isRegNumberValid(String regNumber) {
        return regNumber != null && REG_NUMBER_PATTERN.matcher(regNumber).matches();
    }
}
