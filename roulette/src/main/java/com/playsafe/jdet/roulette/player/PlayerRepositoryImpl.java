package com.playsafe.jdet.roulette.player;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * @author Fact S Musingarimi
 * 14/12/2020
 * 15:31
 */
@Service
class PlayerRepositoryImpl implements PlayerRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerRepositoryImpl.class);
    private static final String PLAYER_FILE_NAME = "playerNames.txt";

    @Override
    public List<Player> getPlayers() {
        LOGGER.info("Retrieving player list.");
        List<String> playerRows;
        try {
            playerRows = Files.readAllLines(new ClassPathResource("/" + PLAYER_FILE_NAME).getFile().toPath());
        } catch (IOException e) {
            throw new NoSuchElementException("The system could not retrieve player information from " + PLAYER_FILE_NAME);
        }
        playerRows.forEach(LOGGER::info);
        return playerRows.stream().map(this::convertToPlayer)
                .collect(Collectors.toList());

    }

    private Player convertToPlayer(String playerRow) {
        String[] playerDetails = playerRow.split(",");
        String name = null;
        double totalWins = 0;
        double totalBets = 0;
        for (int i = 0; i < playerDetails.length; i++) {
            if (i == 0) {
                name = playerDetails[i];
            } else if (i == 1) {
                totalWins = Double.parseDouble(playerDetails[i]);
            } else if (i == 2) {
                totalBets = Double.parseDouble(playerDetails[i]);
            }
        }
        return Player.of(name, totalWins, totalBets);
    }
}
