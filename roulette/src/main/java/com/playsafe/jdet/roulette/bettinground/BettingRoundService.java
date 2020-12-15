package com.playsafe.jdet.roulette.bettinground;

import com.playsafe.jdet.roulette.player.Player;

import java.util.List;

/**
 * @author Fact S Musingarimi
 * 14/12/2020
 * 16:56
 */
public interface BettingRoundService {
    BettingRound play(List<Player> players);
}
