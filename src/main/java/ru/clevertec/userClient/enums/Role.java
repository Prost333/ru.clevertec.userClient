package ru.clevertec.userClient.enums;

public enum Role {
    ADMIN("ADMIN"),
    JOURNALIST("JOURNALIST"),
    SUBSCRIBER("SUBSCRIBER");

    String name;
    Role(String name) {
        this.name = name;
    }
}
