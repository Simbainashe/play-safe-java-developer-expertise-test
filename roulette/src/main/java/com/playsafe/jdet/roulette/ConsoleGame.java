package com.playsafe.jdet.roulette;

import com.playsafe.jdet.roulette.bet.BettingOption;
import com.playsafe.jdet.roulette.bettinground.BettingRound;
import com.playsafe.jdet.roulette.bettinground.BettingRoundService;
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
        players = playerRepository.saveAll(players);
        printTotals(players);
    }

    private void printResult(BettingRound bettingRound) {
        System.out.println("-----");
        System.out.printf("%-20s\t\t\t%d%n", "Number", bettingRound.getRouletteWheel().getBallNumber());
        System.out.printf("%-20s\t\t\t%-6s\t\t\t%-6s\t\t\t%-6s%n", "Player", "Bet", "Outcome", "Winnings");
        System.out.println("-----");
        bettingRound.getBets().forEach(bet -> {
            String b = bet.getBettingOption().equals(BettingOption.SINGLE_NUMBER) ?
                    String.valueOf(bet.getAdditionInformation().get("singleNumber")) :
                    bet.getBettingOption().name();
            System.out.printf("%-20s\t\t\t%-6s\t\t\t%-6s\t\t\t%-6s%n", bet.getPlayer().getName(), b
                    , bet.getWinnings() > 0 ? "WIN" : "LOSE", bet.getWinnings());
        });
        System.out.println("");

    }


    private void printTotals(List<Player> players) {
        System.out.println("-----");
        System.out.printf("%-20s\t\t\t%-10s\t\t\t%-10s%n", "Player", "Total Win", "Total Bet");
        System.out.println("------");
        players.forEach(player -> System.out.printf("%-20s\t\t\t%-10s\t\t\t%-10s%n", player.getName(),
                player.getTotalWinnings(), player.getTotalBets()));

    }

}
