package com.playsafe.jdet.roulette;

import java.util.Objects;

/**
 * @author Fact S Musingarimi
 * 11/12/2020
 * 06:09
 */
class SingleNumberWinningsCalculator implements WinningsCalculator {
    static final SingleNumberWinningsCalculator INSTANCE = new SingleNumberWinningsCalculator();

    private SingleNumberWinningsCalculator() {
    }

    @Override
    public double calculateWinnings(RouletteWheel rouletteWheel, Bet bet) {
        Objects.requireNonNull(rouletteWheel, "rouletteWheel cannot be null");
        Objects.requireNonNull(bet, "bet cannot be null");
        Integer singleNumber = (Integer) bet.getAdditionInformation().get("singleNumber");
        if (rouletteWheel.getBallNumber() % 2 == singleNumber) {
            return bet.getAmount() * 36;
        }
        return 0;
    }
}
