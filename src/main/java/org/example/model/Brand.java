package org.example.model;

public enum Brand {
    BMW("Bmw"),
    MERCEDES("Mercedes"),
    PEUGEOT("Peugeot"),
    HONDA("Honda"),
    TOYOTA("Toyota");

    private final String name;

    Brand(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
