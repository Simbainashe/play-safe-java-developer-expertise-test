package com.playsafe.jdet.roulette;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author Fact S Musingarimi
 * 11/12/2020
 * 06:45
 */
@Service
class WinningsCalculatorFactoryImpl implements WinningsCalculatorFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(WinningsCalculatorFactoryImpl.class);

    @Override
    public WinningsCalculator getWinningsDeterminationStrategy(BettingOption bettingOption) {
        LOGGER.info("Determining winnings strategy for betting option: {}", bettingOption);
        Objects.requireNonNull(bettingOption, "bettingOption cannot be null");
        switch (bettingOption) {
            case ODD:
                return OddNumberWinningsCalculator.INSTANCE;
            case EVEN:
                return EvenNumberWinningsCalculator.INSTANCE;
            case SINGLE_NUMBER:
                return SingleNumberWinningsCalculator.INSTANCE;
            default:
                throw new UnsupportedOperationException("The betting operation is not yet supported");
        }
    }
}
