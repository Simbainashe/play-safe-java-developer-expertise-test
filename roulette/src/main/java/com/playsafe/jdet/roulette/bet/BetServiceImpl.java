package com.playsafe.jdet.roulette.bet;

import com.playsafe.jdet.roulette.player.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Scanner;

/**
 * @author Fact S Musingarimi
 * 14/12/2020
 * 16:01
 */
@Service
class BetServiceImpl implements BetService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BetServiceImpl.class);
    private static final Scanner SCANNER = new Scanner(System.in);

    @Override
    public Bet placeBet(Player player) {
        LOGGER.info("Placing a bet for: {}\n", player.getName());
        System.out.printf("%s\t", player.getName());
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
        } catch (NumberFormatException exception) {
            return -1;
        }
    }

    private static class BettingOptionInput {
        private final BettingOption bettingOption;
        private final int singleNumber;

        BettingOptionInput(BettingOption bettingOption, int singleNumber) {
            this.bettingOption = bettingOption;
            this.singleNumber = singleNumber;
        }
    }
}
