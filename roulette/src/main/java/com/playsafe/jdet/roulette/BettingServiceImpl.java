package com.playsafe.jdet.roulette;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;

/**
 * @author Fact S Musingarimi
 * 11/12/2020
 * 06:40
 */
@Service
class BettingServiceImpl implements BettingService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BettingServiceImpl.class);
    private final WinningsCalculatorFactory winningsCalculatorFactory;

    BettingServiceImpl(WinningsCalculatorFactory winningsCalculatorFactory) {
        this.winningsCalculatorFactory = winningsCalculatorFactory;
    }

    @Override
    public void bet(Collection<Bet> bets) {
        LOGGER.info("Executing bets: {}", bets);
        Objects.requireNonNull(bets, "bets cannot be null");
        RouletteWheel rouletteWheel = new RouletteWheel();
        bets.forEach(bet -> winningsCalculatorFactory
                .getWinningsDeterminationStrategy(bet.getBettingOption())
                .calculateWinnings(rouletteWheel, bet));
    }
}
