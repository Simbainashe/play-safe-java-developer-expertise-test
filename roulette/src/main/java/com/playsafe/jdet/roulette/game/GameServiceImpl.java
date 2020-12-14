package com.playsafe.jdet.roulette.game;

import com.playsafe.jdet.roulette.bet.Bet;
import com.playsafe.jdet.roulette.bet.BetPlacementService;
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
    private final BetPlacementService betPlacementService;

    GameServiceImpl(WinningsService winningsService, BetPlacementService betPlacementService) {
        this.winningsService = winningsService;
        this.betPlacementService = betPlacementService;
    }

    @Override
    public GameResult play(List<Player> players) {
        LOGGER.info("Play roulette: {}", players);
        RouletteWheel rouletteWheel = new RouletteWheel();
        List<Bet> bets = players.stream().map(betPlacementService::placeBet).collect(Collectors.toList());
        bets = winningsService.awardWinnings(bets, rouletteWheel);
        return GameResult.of(rouletteWheel, bets);
    }
}
