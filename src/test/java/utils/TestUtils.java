package utils;

import org.example.dto.BookingDto;
import org.example.dto.CarDto;
import org.example.dto.UserDto;
import org.example.model.Booking;
import org.example.model.Brand;
import org.example.model.Car;
import org.example.model.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TestUtils {

    public static final User JOHN = new User(1, "John", "Smith", 23, "johny@mail.com", "098-968-586");

    public static final UserDto JOHN_DTO = new UserDto("John", "Smith", 23, "johny@mail.com", "098-968-586");

    public static final User MARIA = new User(2, "Maria", "Moon", 23, "marry@mail.com", "123456789");

    public static final String BOOKING_IS_ALREADY_USED_BY_THIS_USER = "Car is already booked by this user";

    public static final String BOOKING_IS_ALREADY_USED_BY_OTHER_USER = "Car is already booked by other user";

    public static final String NO_SUCH_BOOKING = "No such booking!";

    public static final Car MERCEDES = new Car(1, "ABC123", new BigDecimal("100"), Brand.MERCEDES, true, true);

    public static final CarDto MERCEDES_DTO = new CarDto("ABC123", new BigDecimal("100"), Brand.MERCEDES, true, true);

    public static final Car BMW = new Car(2, "DEF456", new BigDecimal("250"), Brand.BMW, false, false);

    public static final CarDto BMW_DTO = new CarDto("DEF456", new BigDecimal("250"), Brand.BMW, false, false);

    public static final Booking BOOKING = new Booking(1, LocalDateTime.of(2023, 1, 23, 10, 10), LocalDateTime.of(2023, 1, 27, 10, 10), JOHN, MERCEDES);

    public static final BookingDto BOOKING_DTO = new BookingDto( LocalDateTime.of(2023, 1, 23, 10, 10), LocalDateTime.of(2023, 1, 27, 10, 10),JOHN, MERCEDES, new BigDecimal("340"));

    public static final String CAR_IS_ALREADY_BOOKED = "Car %s with regNumber = %s is already booked.";

    public static final String NO_BOOKING_FOR_CAR = "There is no booking for car %s with regNumber = %s";

    public static final String EMAIL_IS_NOT_VALID = "Email is not valid!";

    public static final String INVALID_PHONE_NUMBER_FORMAT = "Invalid phone number format!";

    public static final String INVALID_REG_NUMBER = "Invalid reg number!";

    public static final String INVALID_DATA = "Invalid";

    public static final String EMAIL_IS_TAKEN = "Email is already taken by other user!";

    public static final String PHONE_NUMBER_IS_TAKEN = "Phone number is already taken by other user!";

    public static final int AGE_LESS_THAN_ACCEPTED_USER_AGE = 17;

    public static final String AGE_LESS_THAN_ACCEPTED = "Users with age less than 18 can not book cars!";

    public static final String NO_USER_WITH_PROVIDED_DETAILS = "No user with provided details!";

    public static final String REG_NUMBER_IS_ALREADY_USED = "Reg Number is already used";

    public static final String NO_CAR_WITH_REG_NUMBER = "No car with reg number = " + INVALID_DATA;

    public static final String SHOULD_BE_EQUAL_OR_MORE_THAN_50 = "Rental Price should be equal or more than 50";

    public static final String SHOULD_BE_EQUAL_OR_LESS_THAN_500 = "Rental Price should be equal or less than 500";

    public static final BigDecimal LESS_RENTAL_PRICE = new BigDecimal("49");

    public static final BigDecimal MORE_RENTAL_PRICE = new BigDecimal("501");

    public static final String VALID_EMAIL = "johny@mail.com";

    public static final String VALID_PHONE_NUMBER = "098765433";

    public static final int VALID_AGE = 18;

    public static final String VALID_REG_NUMBER = "ABC123";

    public static final BigDecimal VALID_RENTAL_PRICE = new BigDecimal("100");
}
