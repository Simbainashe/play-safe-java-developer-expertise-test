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

    private Player(String name, double totalWinnings, double totalBets) {
        Objects.requireNonNull(name, "name cannot be null");
        this.name = name;
        this.totalWinnings = totalWinnings;
        this.totalBets = totalBets;
    }

    public static Player of(String name, double totalWinnings, double totalBets) {
        return new Player(name, totalWinnings, totalBets);
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
