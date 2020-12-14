package com.playsafe.jdet.roulette.winnings;

import com.playsafe.jdet.roulette.bet.BettingOption;

/**
 * @author Fact S Musingarimi
 * 11/12/2020
 * 06:44
 */
interface WinningsCalculatorFactory {
    WinningsCalculator getWinningsDeterminationStrategy(BettingOption bettingOption);
}
