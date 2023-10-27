package org.example.validator;

import java.util.regex.Pattern;

public class EmailValidator {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$");

    public static boolean isValidEmail(String email) {
        if (email == null){
            throw new NullPointerException("Email is null!");
        } else if (!EMAIL_PATTERN.matcher(email).matches()){
            throw new IllegalArgumentException("Email is not valid!");
        }

        return true;
    }
}
