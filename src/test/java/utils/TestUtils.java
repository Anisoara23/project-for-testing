package utils;

import org.example.model.Booking;
import org.example.model.Brand;
import org.example.model.Car;
import org.example.model.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TestUtils {

    public static final User JOHN = new User(1, "John", "Smith", 23, "johny@mail.com", "0987654321");

    public static final User MARIA = new User(2, "Maria", "Moon", 23, "marry@mail.com", "1234567890");

    public static final String BOOKING_IS_ALREADY_USED_BY_THIS_USER = "Car is already booked by this user";

    public static final String BOOKING_IS_ALREADY_USED_BY_OTHER_USER = "Car is already booked by other user";

    public static final String NO_SUCH_BOOKING = "No such booking!";

    public static final Car MERCEDES = new Car(1, "ABC123", new BigDecimal("350"), Brand.MERCEDES, true);

    public static final Car BMW = new Car(2, "DEF456", new BigDecimal("250"), Brand.BMW, false);

    public static final Booking BOOKING = new Booking(1, LocalDateTime.now(), JOHN, MERCEDES);

    public static final String CAR_IS_ALREADY_BOOKED = "Car %s with regNumber = %s is already booked.";

    public static final String NO_BOOKING_FOR_CAR = "There is no booking for car %s with regNumber = %s";

    public static final String EMAIL_IS_NOT_VALID = "Email is not valid!";

    public static final String EMAIL_IS_NULL = "Email is null!";

    public static final String INVALID_PHONE_NUMBER_FORMAT = "Invalid phone number format!";

    public static final String PHONE_NUMBER_IS_NULL = "Phone number is null!";

    public static final String INVALID_REG_NUMBER = "Invalid reg number!";

    public static final String NULL_REG_NUMBER = "Null reg number!";
}
