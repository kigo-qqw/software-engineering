package ru.nstu.se.lab2;

import javafx.scene.Node;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CONST {
    String title();

    String group();

    Class<? extends Node> clazz();
}