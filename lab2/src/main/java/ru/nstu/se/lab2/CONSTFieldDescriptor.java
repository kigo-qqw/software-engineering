package ru.nstu.se.lab2;

import javafx.scene.Node;

public record CONSTFieldDescriptor(
        String name,
        int value,
        String title,
        String group,
        Class<? extends Node> clazz
) {
}
