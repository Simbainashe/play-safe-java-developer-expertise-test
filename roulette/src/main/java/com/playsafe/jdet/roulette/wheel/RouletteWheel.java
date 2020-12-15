package com.playsafe.jdet.roulette.wheel;

import java.util.Random;

/**
 * @author Fact S Musingarimi
 * 11/12/2020
 * 05:53
 */
public class RouletteWheel {
    private final Random random = new Random();
    private int ballNumber;

    public RouletteWheel() {
    }

    public int getBallNumber() {
        return ballNumber;
    }

    public void spin() {
        this.ballNumber = 1 + random.nextInt(36);
    }

    @Override
    public String toString() {
        return "RouletteWheel{" +
                "ballNumber=" + ballNumber +
                '}';
    }
}
