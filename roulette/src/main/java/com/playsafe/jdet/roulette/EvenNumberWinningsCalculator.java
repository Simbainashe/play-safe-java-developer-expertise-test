package com.playsafe.jdet.roulette;

import java.util.Objects;

/**
 * @author Fact S Musingarimi
 * 11/12/2020
 * 06:02
 */
class EvenNumberWinningsCalculator implements WinningsCalculator {
    static final EvenNumberWinningsCalculator INSTANCE = new EvenNumberWinningsCalculator();

    private EvenNumberWinningsCalculator() {
    }

    @Override
    public double calculateWinnings(RouletteWheel rouletteWheel, Bet bet) {
        Objects.requireNonNull(rouletteWheel, "rouletteWheel cannot be null");
        Objects.requireNonNull(bet, "bet cannot be null");
        if (rouletteWheel.getBallNumber() % 2 == 0) {
            return bet.getAmount() * 2;
        }
        return 0;
    }
}
