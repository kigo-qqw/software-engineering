package ru.nstu.se.lab2;

public record CONSTFieldDescriptor(
        String name,
        int value,
        String title,
        String group,
        Class<?> clazz
) {
}
