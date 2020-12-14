package com.playsafe.jdet.roulette;

import java.util.List;

/**
 * @author Fact S Musingarimi
 * 14/12/2020
 * 16:56
 */
public interface GamingService {
    GameResult play(List<Player> players);
}
