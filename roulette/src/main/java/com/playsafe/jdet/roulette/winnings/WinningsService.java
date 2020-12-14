package com.playsafe.jdet.roulette.winnings;

import com.playsafe.jdet.roulette.bet.Bet;
import com.playsafe.jdet.roulette.wheel.RouletteWheel;

import java.util.List;

/**
 * @author Fact S Musingarimi
 * 11/12/2020
 * 06:37
 */
public interface WinningsService {
    List<Bet> award(List<Bet> bets, RouletteWheel rouletteWheel);
}
