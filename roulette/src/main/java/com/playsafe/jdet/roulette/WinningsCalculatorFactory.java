package com.playsafe.jdet.roulette;

/**
 * @author Fact S Musingarimi
 * 11/12/2020
 * 06:44
 */
interface WinningsCalculatorFactory {
    WinningsCalculator getWinningsDeterminationStrategy(BettingOption bettingOption);
}
