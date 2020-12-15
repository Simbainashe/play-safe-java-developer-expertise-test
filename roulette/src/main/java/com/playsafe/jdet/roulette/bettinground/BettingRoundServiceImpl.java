package com.playsafe.jdet.roulette.bettinground;

import com.playsafe.jdet.roulette.bet.Bet;
import com.playsafe.jdet.roulette.bet.BetService;
import com.playsafe.jdet.roulette.player.Player;
import com.playsafe.jdet.roulette.wheel.RouletteWheel;
import com.playsafe.jdet.roulette.winnings.WinningsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @author Fact S Musingarimi
 * 14/12/2020
 * 17:02
 */
@Service
class BettingRoundServiceImpl implements BettingRoundService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BettingRoundServiceImpl.class);
    private final WinningsService winningsService;
    private final BetService betService;

    BettingRoundServiceImpl(WinningsService winningsService, BetService betService) {
        this.winningsService = winningsService;
        this.betService = betService;
    }

    @Override
    public BettingRound play(List<Player> players) {
        LOGGER.info("Play roulette: {}", players);
        final RouletteWheel rouletteWheel = new RouletteWheel();
        this.spinWheelAtRegularIntervals(rouletteWheel);
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Callable<List<Bet>> bettingTask = () -> players.stream().map(betService::placeBet).collect(Collectors.toList());
        Future<List<Bet>> bettingTaskResult = executor.submit(bettingTask);
        List<Bet> bets = this.getBets(bettingTaskResult);
        bets = winningsService.awardWinnings(bets, rouletteWheel);
        return BettingRound.of(rouletteWheel, bets);
    }

    private void spinWheelAtRegularIntervals(RouletteWheel rouletteWheel) {
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        Runnable spinningWheelTask = rouletteWheel::spin;
        scheduledExecutorService.scheduleWithFixedDelay(spinningWheelTask, 0, 30, TimeUnit.SECONDS);
    }

    private List<Bet> getBets(Future<List<Bet>> future) {
        try {
            return future.get();
        } catch (InterruptedException e) {
            throw new IllegalStateException("Placing of bets has been interrupted", e);
        } catch (ExecutionException e) {
            throw new IllegalStateException("Placing of bets has failed to execute", e);
        }
    }
}
