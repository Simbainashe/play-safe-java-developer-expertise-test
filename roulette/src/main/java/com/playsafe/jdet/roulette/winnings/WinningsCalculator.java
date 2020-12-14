package com.playsafe.jdet.roulette.winnings;

import com.playsafe.jdet.roulette.bet.Bet;
import com.playsafe.jdet.roulette.wheel.RouletteWheel;

/**
 * @author Fact S Musingarimi
 * 11/12/2020
 * 06:01
 */
public interface WinningsCalculator {
    double calculateWinnings(RouletteWheel rouletteWheel, Bet bet);
}
