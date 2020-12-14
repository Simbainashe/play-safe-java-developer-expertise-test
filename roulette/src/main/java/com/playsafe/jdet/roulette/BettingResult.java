package com.playsafe.jdet.roulette;

import java.util.Collection;

/**
 * @author Fact S Musingarimi
 * 14/12/2020
 * 14:29
 */
public class BettingResult {
    private final RouletteWheel rouletteWheel;
    private final Collection<Bet> bets;

    private BettingResult(RouletteWheel rouletteWheel, Collection<Bet> bets) {
        this.rouletteWheel = rouletteWheel;
        this.bets = bets;
    }

    public static BettingResult of(RouletteWheel rouletteWheel, Collection<Bet> bets) {
        return new BettingResult(rouletteWheel, bets);
    }

    public RouletteWheel getRouletteWheel() {
        return rouletteWheel;
    }

    public Collection<Bet> getBets() {
        return bets;
    }

    @Override
    public String toString() {
        return "BettingResult{" +
                "rouletteWheel=" + rouletteWheel +
                ", bets=" + bets +
                '}';
    }
}
