package com.playsafe.jdet.roulette;

/**
 * @author Fact S Musingarimi
 * 11/12/2020
 * 05:53
 */
public class Roulette {
    private int number;

    private Roulette(int number) {
        this.number = number;
    }

    public static Roulette of(int number) {
        return new Roulette(number);
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Roulette{" +
                "number=" + number +
                '}';
    }
}
