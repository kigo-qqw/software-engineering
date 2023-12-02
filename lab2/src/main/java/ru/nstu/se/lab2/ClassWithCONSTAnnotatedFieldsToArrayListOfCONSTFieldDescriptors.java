package ru.nstu.se.lab2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ClassWithCONSTAnnotatedFieldsToArrayListOfCONSTFieldDescriptors {
    public static ArrayList<CONSTFieldDescriptor> convert(Object object) {
        return Arrays.stream(object.getClass().getDeclaredFields())
                .filter(field -> field.getAnnotation(CONST.class) != null)
                .map(field -> {
                    field.setAccessible(true);
                    CONST annotation = field.getAnnotation(CONST.class);
                    try {
                        return new CONSTFieldDescriptor(
                                field.getName(),
                                field.getInt(object),
                                annotation.title(),
                                annotation.group(),
                                annotation.clazz());
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
