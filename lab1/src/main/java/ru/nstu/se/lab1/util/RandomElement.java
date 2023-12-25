package ru.nstu.se.lab1.util;

public interface RandomElement<T extends Comparable<T>> {
    T makeRandom();
}
