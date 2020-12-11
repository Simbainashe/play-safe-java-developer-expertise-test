package com.playsafe.jdet.roulette;

import java.util.Collection;

/**
 * @author Fact S Musingarimi
 * 11/12/2020
 * 06:37
 */
public interface BettingService {
    RouletteWheel bet(Collection<Bet> bets);
}
