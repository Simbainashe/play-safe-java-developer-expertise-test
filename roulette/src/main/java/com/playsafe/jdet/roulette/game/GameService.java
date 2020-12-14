package com.playsafe.jdet.roulette.game;

import com.playsafe.jdet.roulette.player.Player;

import java.util.List;

/**
 * @author Fact S Musingarimi
 * 14/12/2020
 * 16:56
 */
public interface GameService {
    GameResult play(List<Player> players);
}
