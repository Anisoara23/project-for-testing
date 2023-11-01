package org.example.validator;

public class AgeValidator {

    public static void validateAge(int age, int acceptedAge) {
        if (age < acceptedAge) {
            throw new IllegalArgumentException("Users with age less than %s can not book cars!".formatted(acceptedAge));
        }
    }
}
