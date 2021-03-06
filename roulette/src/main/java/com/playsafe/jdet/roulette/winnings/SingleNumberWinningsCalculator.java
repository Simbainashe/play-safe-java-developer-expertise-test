package com.playsafe.jdet.roulette.winnings;

import com.playsafe.jdet.roulette.bet.Bet;
import com.playsafe.jdet.roulette.wheel.RouletteWheel;

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
        if (rouletteWheel.getBallNumber() == singleNumber) {
            return bet.getAmount() * 36;
        }
        return 0;
    }
}
