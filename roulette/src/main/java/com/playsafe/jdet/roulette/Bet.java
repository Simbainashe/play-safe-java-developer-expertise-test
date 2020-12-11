package com.playsafe.jdet.roulette;

import java.util.Map;

/**
 * @author Fact S Musingarimi
 * 11/12/2020
 * 05:41
 */
public class Bet {
    private final Player player;
    private final BettingOption bettingOption;
    private final double amount;
    private final Map<String, Object> additionInformation;
    private double winnings;

    private Bet(Player player, BettingOption bettingOption, double amount, Map<String, Object> additionInformation) {
        this.player = player;
        this.bettingOption = bettingOption;
        this.amount = amount;
        this.additionInformation = additionInformation;
        this.player.setTotalBets(this.player.getTotalBets() + amount);
    }

    public static Bet of(Player player, BettingOption bettingOption, double amount, Map<String, Object> prediction) {
        return new Bet(player, bettingOption, amount, prediction);
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
