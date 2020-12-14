package com.playsafe.jdet.roulette.game;

import com.playsafe.jdet.roulette.bet.Bet;
import com.playsafe.jdet.roulette.wheel.RouletteWheel;

import java.util.Collection;

/**
 * @author Fact S Musingarimi
 * 14/12/2020
 * 14:29
 */
public class GameResult {
    private final RouletteWheel rouletteWheel;
    private final Collection<Bet> bets;

    private GameResult(RouletteWheel rouletteWheel, Collection<Bet> bets) {
        this.rouletteWheel = rouletteWheel;
        this.bets = bets;
    }

    public static GameResult of(RouletteWheel rouletteWheel, Collection<Bet> bets) {
        return new GameResult(rouletteWheel, bets);
    }

    public RouletteWheel getRouletteWheel() {
        return rouletteWheel;
    }

    public Collection<Bet> getBets() {
        return bets;
    }

    @Override
    public String toString() {
        return "BettingRound{" +
                "rouletteWheel=" + rouletteWheel +
                ", bets=" + bets +
                '}';
    }
}
