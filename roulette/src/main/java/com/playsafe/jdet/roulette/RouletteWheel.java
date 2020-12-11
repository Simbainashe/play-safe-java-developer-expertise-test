package com.playsafe.jdet.roulette;

/**
 * @author Fact S Musingarimi
 * 11/12/2020
 * 05:53
 */
public class RouletteWheel {
    private int ballNumber;

    private RouletteWheel(int ballNumber) {
        this.ballNumber = ballNumber;
    }

    public static RouletteWheel of(int number) {
        return new RouletteWheel(number);
    }

    public int getBallNumber() {
        return ballNumber;
    }

    @Override
    public String toString() {
        return "RouletteWheel{" +
                "ballNumber=" + ballNumber +
                '}';
    }
}
