package com.playsafe.jdet.roulette;

import java.util.Objects;

/**
 * @author Fact S Musingarimi
 * 11/12/2020
 * 06:08
 */
class OddNumberWinningsCalculator implements WinningsCalculator {
    static final OddNumberWinningsCalculator INSTANCE = new OddNumberWinningsCalculator();

    private OddNumberWinningsCalculator() {
    }

    @Override
    public double calculateWinnings(RouletteWheel rouletteWheel, Bet bet) {
        Objects.requireNonNull(rouletteWheel, "rouletteWheel cannot be null");
        Objects.requireNonNull(bet, "bet cannot be null");
        if (rouletteWheel.getBallNumber() % 2 != 0) {
            return bet.getAmount() * 2;
        }
        return 0;
    }
}
