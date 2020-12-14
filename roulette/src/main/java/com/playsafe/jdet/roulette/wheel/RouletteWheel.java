package com.playsafe.jdet.roulette.wheel;

import java.util.Random;

/**
 * @author Fact S Musingarimi
 * 11/12/2020
 * 05:53
 */
public class RouletteWheel {
    private final int ballNumber;


    public RouletteWheel() {
        Random rand = new Random();
        this.ballNumber = 1 + rand.nextInt(36);
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
