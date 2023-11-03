package org.example.utils;

public class Utils {

    public static final String URL = "jdbc:postgresql://localhost:5432/postgres";

    public static final String USER = "postgres";

    public static final String PASSWORD = "1234567";

    public static final String INSERT_STATEMENT = "INSERT INTO \"user\"(" +
            "first_name, " +
            "last_name, " +
            "age, " +
            "email, " +
            "phone_number" +
            ") " +
            "VALUES(?, ?, ?, ?, ?);";

    public static final String EXISTS_BY_EMAIL_STATEMENT = "SELECT FROM \"user\" " +
            "WHERE email = ?;";

    public static final String EXISTS_BY_PHONE_NUMBER_STATEMENT = "SELECT FROM \"user\" " +
            "WHERE phone_number = ?;";

    public static final String CREATE_TABLE_STATEMENT = "CREATE TABLE \"user\"(" +
            "id int NOT NULL AUTO_INCREMENT," +
            "first_name varchar(100)," +
            "last_name varchar(100)," +
            "age int," +
            "email varchar(100)," +
            "phone_number varchar(100)" +
            ");";

    public static final String SELECT_ALL_USERS_STATEMENT = "SELECT * FROM \"user\"";

    public static final String DELETE_STATEMENT = "DELETE FROM \"user\" WHERE id = ?;";

    public static final String USERS_CSV = "src/main/resources/users.csv";
}
