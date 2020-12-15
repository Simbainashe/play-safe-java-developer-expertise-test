package com.playsafe.jdet.roulette.bettinground;

import com.playsafe.jdet.roulette.bet.BettingOption;
import com.playsafe.jdet.roulette.player.Player;
import com.playsafe.jdet.roulette.player.PlayerRepository;
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
class ConsoleGame implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleGame.class);
    private final PlayerRepository playerRepository;
    private final BettingRoundService bettingRoundService;

    ConsoleGame(PlayerRepository playerRepository, BettingRoundService bettingRoundService) {
        this.playerRepository = playerRepository;
        this.bettingRoundService = bettingRoundService;
    }

    @Override
    public void run(String... args) {
        LOGGER.info("Running console roulette");
        List<Player> players = playerRepository.getPlayers();
        BettingRound bettingRound = bettingRoundService.play(players);
        printResult(bettingRound);
    }

    private void printResult(BettingRound bettingRound) {
        LOGGER.info("Number    {}", bettingRound.getRouletteWheel().getBallNumber());
        LOGGER.info("Player    Bet    Outcome    Winnings");
        LOGGER.info("-----");
        bettingRound.getBets().forEach(bet -> {
            String b = bet.getBettingOption().equals(BettingOption.SINGLE_NUMBER) ?
                    String.valueOf(bet.getAdditionInformation().get("singleNumber")) :
                    bet.getBettingOption().name();
            LOGGER.info("{}    {}   {}   {}", bet.getPlayer().getName(), b
                    , bet.getWinnings() > 0 ? "WIN" : "LOSE", bet.getWinnings());
        });
    }

}