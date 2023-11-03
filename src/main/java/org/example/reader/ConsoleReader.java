package org.example.reader;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleReader<T> implements Reader {

    private final Class<T> type;

    public ConsoleReader(Class<T> type) {
        this.type = type;
    }

    @Override
    public List<List<String>> readData() {
        List<String> data = new ArrayList<>();
        Field[] fields = type.getDeclaredFields();

        try (Scanner scanner = new Scanner(System.in)) {
            for (Field field : fields) {
                System.out.println(field.getName());
                data.add(scanner.nextLine());
            }
            return List.of(data);
        }
    }
}
