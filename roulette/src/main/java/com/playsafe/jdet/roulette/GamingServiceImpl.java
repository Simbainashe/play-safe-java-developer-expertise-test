package com.playsafe.jdet.roulette;

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
class GamingServiceImpl implements GamingService {
    private static final Logger LOGGER = LoggerFactory.getLogger(GamingServiceImpl.class);
    private final WinningsAwardService winningsAwardService;
    private final BetPlacementService betPlacementService;

    GamingServiceImpl(WinningsAwardService winningsAwardService, BetPlacementService betPlacementService) {
        this.winningsAwardService = winningsAwardService;
        this.betPlacementService = betPlacementService;
    }

    @Override
    public GameResult play(List<Player> players) {
        LOGGER.info("Play roulette: {}", players);
        RouletteWheel rouletteWheel = new RouletteWheel();
        List<Bet> bets = players.stream().map(betPlacementService::placeBet).collect(Collectors.toList());
        bets = winningsAwardService.award(bets, rouletteWheel);
        return GameResult.of(rouletteWheel, bets);
    }
}
