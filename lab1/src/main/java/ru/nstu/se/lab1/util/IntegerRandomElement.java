package ru.nstu.se.lab1.util;

import java.util.Random;

public class IntegerRandomElement implements RandomElement<Integer> {
    private final Random random = new Random();

    @Override
    public Integer makeRandom() {
        return random.nextInt(-10, 10 + 1);
    }
}
