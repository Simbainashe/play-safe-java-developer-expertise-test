package com.playsafe.jdet.roulette;

import java.util.Objects;

/**
 * @author Fact S Musingarimi
 * 11/12/2020
 * 05:38
 */
public class Player {
    private final String name;
    private double totalWinnings;
    private double totalBets;

    private Player(String name) {
        Objects.requireNonNull(name, "name cannot be null");
        this.name = name;
    }

    public static Player of(String name) {
        return new Player(name);
    }

    public String getName() {
        return name;
    }

    public double getTotalWinnings() {
        return totalWinnings;
    }

    public void setTotalWinnings(double totalWinnings) {
        this.totalWinnings = totalWinnings;
    }

    public double getTotalBets() {
        return totalBets;
    }

    public void setTotalBets(double totalBets) {
        this.totalBets = totalBets;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", totalWinnings=" + totalWinnings +
                ", totalBets=" + totalBets +
                '}';
    }
}
