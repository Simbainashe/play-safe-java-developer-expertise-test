package com.playsafe.jdet.roulette.game;

import com.playsafe.jdet.roulette.bet.Bet;
import com.playsafe.jdet.roulette.bet.BetService;
import com.playsafe.jdet.roulette.player.Player;
import com.playsafe.jdet.roulette.wheel.RouletteWheel;
import com.playsafe.jdet.roulette.winnings.WinningsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Fact S Musingarimi
 * 14/12/2020
 * 17:02
 */
@Service
class GameServiceImpl implements GameService {
    private static final Logger LOGGER = LoggerFactory.getLogger(GameServiceImpl.class);
    private final WinningsService winningsService;
    private final BetService betService;

    GameServiceImpl(WinningsService winningsService, BetService betService) {
        this.winningsService = winningsService;
        this.betService = betService;
    }

    @Override
    public GameResult play(List<Player> players) {
        LOGGER.info("Play roulette: {}", players);
        RouletteWheel rouletteWheel = new RouletteWheel();
        List<Bet> bets = players.stream().map(betService::placeBet).collect(Collectors.toList());
        bets = winningsService.awardWinnings(bets, rouletteWheel);
        return GameResult.of(rouletteWheel, bets);
    }
}
