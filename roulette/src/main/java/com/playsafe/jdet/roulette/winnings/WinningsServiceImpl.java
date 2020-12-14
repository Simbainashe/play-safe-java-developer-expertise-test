package com.playsafe.jdet.roulette.winnings;

import com.playsafe.jdet.roulette.bet.Bet;
import com.playsafe.jdet.roulette.wheel.RouletteWheel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author Fact S Musingarimi
 * 11/12/2020
 * 06:40
 */
@Service
class WinningsServiceImpl implements WinningsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(WinningsServiceImpl.class);
    private final WinningsCalculatorFactory winningsCalculatorFactory;

    WinningsServiceImpl(WinningsCalculatorFactory winningsCalculatorFactory) {
        this.winningsCalculatorFactory = winningsCalculatorFactory;
    }

    @Override
    public List<Bet> award(List<Bet> bets, RouletteWheel rouletteWheel) {
        LOGGER.info("Awarding winnings fo bets: {}, {}", bets, rouletteWheel);
        Objects.requireNonNull(bets, "bets cannot be null");
        Objects.requireNonNull(rouletteWheel, "rouletteWheel cannot be null");
        bets.forEach(bet -> this.awardBetWinnings(rouletteWheel, bet));
        return bets;
    }

    private void awardBetWinnings(RouletteWheel rouletteWheel, Bet bet) {
        WinningsCalculator winningsCalculator = winningsCalculatorFactory.getWinningsDeterminationStrategy(bet.getBettingOption());
        bet.awardWinnings(winningsCalculator, rouletteWheel);
    }
}
