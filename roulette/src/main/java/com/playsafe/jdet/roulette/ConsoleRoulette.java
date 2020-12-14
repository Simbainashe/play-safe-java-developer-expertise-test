package com.playsafe.jdet.roulette;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Fact S Musingarimi
 * 11/12/2020
 * 07:14
 */
@Component
class ConsoleRoulette implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleRoulette.class);
    private final PlayerRepository playerRepository;
    private final GamingService gamingService;

    ConsoleRoulette(PlayerRepository playerRepository, GamingService gamingService) {
        this.playerRepository = playerRepository;
        this.gamingService = gamingService;
    }

    @Override
    public void run(String... args) {
        LOGGER.info("Running console roulette");
        List<Player> players = playerRepository.getPlayers();
        GameResult gameResult = gamingService.play(players);
        printResult(gameResult);
    }

    private void printResult(GameResult gameResult) {
        LOGGER.info("Number    {}", gameResult.getRouletteWheel().getBallNumber());
        LOGGER.info("Player    Bet    Outcome    Winnings");
        LOGGER.info("-----");
        gameResult.getBets().forEach(bet -> {
            String b = bet.getBettingOption().equals(BettingOption.SINGLE_NUMBER) ?
                    String.valueOf(bet.getAdditionInformation().get("singleNumber")) :
                    bet.getBettingOption().name();
            LOGGER.info("{}    {}   {}   {}", bet.getPlayer().getName(), b
                    , bet.getWinnings() > 0 ? "WIN" : "LOSE", bet.getWinnings());
        });
    }

}
