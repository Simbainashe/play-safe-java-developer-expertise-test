package com.playsafe.jdet.roulette;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;
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
    private static final Scanner SCANNER = new Scanner(System.in);
    private final BettingService bettingService;
    private final PlayerRepository playerRepository;

    ConsoleRoulette(BettingService bettingService, PlayerRepository playerRepository) {
        this.bettingService = bettingService;
        this.playerRepository = playerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("Running console roulette");
        List<Player> players = playerRepository.getPlayers();
        bet(players);
    }

    private void bet(List<Player> players) {
        List<Bet> bets = players.stream().map(this::placeBet).collect(Collectors.toList());
        BettingRound bettingRound = bettingService.bet(bets);
        printResults(bettingRound);
        try {
            TimeUnit.SECONDS.sleep(30);
            bet(players);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }



    private Bet placeBet(Player player) {
        System.out.print(">>>>>>>>Place your player bet: " + player.getName());
        BettingOptionInput bettingOptionInput = readBetOption();
        double amount = SCANNER.nextDouble();
        Bet bet = Bet.of(player, bettingOptionInput.bettingOption, amount);
        if (bettingOptionInput.bettingOption.equals(BettingOption.SINGLE_NUMBER)) {
            bet.getAdditionInformation().put("singleNumber", bettingOptionInput.singleNumber);
        }
        return bet;
    }

    private BettingOptionInput readBetOption() {
        String betOptionInput = SCANNER.next();
        switch (betOptionInput) {
            case "EVEN":
                return new BettingOptionInput(BettingOption.EVEN, -1);
            case "ODD":
                return new BettingOptionInput(BettingOption.ODD, -1);
            default:
                int number = convertSingleNumber(betOptionInput);
                if (number < 1 || number > 36) {
                    LOGGER.info(">>>>>>>>>>>>>>>>>Invalid number try again:");
                    return readBetOption();
                }
                return new BettingOptionInput(BettingOption.SINGLE_NUMBER, number);

        }
    }

    private int convertSingleNumber(String singleNumber) {
        try {
            return Integer.parseInt(singleNumber);
        } catch (
                NumberFormatException exception) {
            return -1;
        }

    }

    static class BettingOptionInput {
        private final BettingOption bettingOption;
        private final int singleNumber;

        BettingOptionInput(BettingOption bettingOption, int singleNumber) {
            this.bettingOption = bettingOption;
            this.singleNumber = singleNumber;
        }
    }

    private void printResults(BettingRound bettingRound) {
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
