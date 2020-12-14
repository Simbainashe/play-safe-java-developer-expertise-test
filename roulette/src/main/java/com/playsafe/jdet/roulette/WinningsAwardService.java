package com.playsafe.jdet.roulette;

import java.util.List;

/**
 * @author Fact S Musingarimi
 * 11/12/2020
 * 06:37
 */
public interface WinningsAwardService {
    List<Bet> award(List<Bet> bets, RouletteWheel rouletteWheel);
}
