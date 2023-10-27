package org.example.validator;

import java.util.regex.Pattern;

public class PhoneNumberValidator {

    private static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile("^0\\d{2}-?\\d{3}-?\\d{3}$");

    public static boolean isPhoneNumberValid(String phoneNumber) {
        if (phoneNumber == null) {
            throw new NullPointerException("Phone number is null!");
        } else if (!PHONE_NUMBER_PATTERN.matcher(phoneNumber).matches()) {
            throw new IllegalArgumentException("Invalid phone number format!");
        }
        return true;
    }
}
