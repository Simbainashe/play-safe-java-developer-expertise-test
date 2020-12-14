package com.playsafe.jdet.roulette;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author Fact S Musingarimi
 * 11/12/2020
 * 07:14
 */
@Component
class ConsoleRoulette implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleRoulette.class);
    private final WinningsAwardService winningsAwardService;
    private final PlayerRepository playerRepository;
    private final BetPlacementService betPlacementService;

    ConsoleRoulette(WinningsAwardService winningsAwardService, PlayerRepository playerRepository,
                    BetPlacementService betPlacementService) {
        this.winningsAwardService = winningsAwardService;
        this.playerRepository = playerRepository;
        this.betPlacementService = betPlacementService;
    }

    @Override
    public void run(String... args) {
        LOGGER.info("Running console roulette");
        List<Player> players = playerRepository.getPlayers();
        bet(players);
    }

    private void bet(List<Player> players) {
        RouletteWheel rouletteWheel = new RouletteWheel();
        List<Bet> bets = players.stream().map(betPlacementService::placeBet).collect(Collectors.toList());
        bets = winningsAwardService.award(bets, rouletteWheel);
        printResults(rouletteWheel, bets);
        try {
            TimeUnit.SECONDS.sleep(30);
            bet(players);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }

    private void printResults(RouletteWheel rouletteWheel, List<Bet> bets) {
        LOGGER.info("Number    {}", rouletteWheel.getBallNumber());
        LOGGER.info("Player    Bet    Outcome    Winnings");
        LOGGER.info("-----");
        bets.forEach(bet -> {
            String b = bet.getBettingOption().equals(BettingOption.SINGLE_NUMBER) ?
                    String.valueOf(bet.getAdditionInformation().get("singleNumber")) :
                    bet.getBettingOption().name();
            LOGGER.info("{}    {}   {}   {}", bet.getPlayer().getName(), b
                    , bet.getWinnings() > 0 ? "WIN" : "LOSE", bet.getWinnings());
        });
    }

}
