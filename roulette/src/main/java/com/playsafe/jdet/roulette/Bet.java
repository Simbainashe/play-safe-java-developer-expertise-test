package com.playsafe.jdet.roulette;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Fact S Musingarimi
 * 11/12/2020
 * 05:41
 */
public class Bet {
    private final Player player;
    private final BettingOption bettingOption;
    private final double amount;
    private final Map<String, Object> additionInformation = new HashMap<>();
    private double winnings;

    private Bet(Player player, BettingOption bettingOption, double amount) {
        Objects.requireNonNull(player, "player cannot be null");
        Objects.requireNonNull(bettingOption, "bettingOption cannot be null");
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than or equal to zero");
        }
        this.player = player;
        this.bettingOption = bettingOption;
        this.amount = amount;
        this.player.setTotalBets(this.player.getTotalBets() + amount);
    }

    public static Bet of(Player player, BettingOption bettingOption, double amount) {
        return new Bet(player, bettingOption, amount);
    }

    public Player getPlayer() {
        return player;
    }

    public BettingOption getBettingOption() {
        return bettingOption;
    }

    public double getAmount() {
        return amount;
    }

    public Map<String, Object> getAdditionInformation() {
        return additionInformation;
    }

    public double getWinnings() {
        return winnings;
    }

    public void awardWinnings(WinningsCalculator winningsCalculator, RouletteWheel rouletteWheel) {
        this.winnings = winningsCalculator.calculateWinnings(rouletteWheel, this);
        this.player.setTotalWinnings(this.player.getTotalBets() + this.winnings);
    }

    @Override
    public String toString() {
        return "Bet{" +
                "player=" + player +
                ", bettingOption=" + bettingOption +
                ", additionInformation=" + additionInformation +
                ", winnings=" + winnings +
                '}';
    }
}
