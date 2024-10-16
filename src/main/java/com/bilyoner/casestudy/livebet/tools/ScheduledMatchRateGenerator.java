package com.bilyoner.casestudy.livebet.tools;

import com.bilyoner.casestudy.livebet.websocket.SocketController;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Getter
@Setter
public class ScheduledMatchRateGenerator {
    private Map<Long, MatchRateGenerator> matchRateGenerators;
    private final SocketController socketController;

    @Autowired
    public ScheduledMatchRateGenerator(SocketController socketController) {
        this.socketController = socketController;
        matchRateGenerators = new HashMap<>();
    }

    private static final Logger log = LoggerFactory.getLogger(ScheduledMatchRateGenerator.class);

    @Scheduled(fixedRate = 1000)
    public void updateMatchRates() {
        log.info("Running Rate Generator...");
        for (MatchRateGenerator matchRateGenerator : matchRateGenerators.values()) {
            matchRateGenerator.generateMatchRate();
            log.info("Match Rate Generated for " + matchRateGenerator.toString());
        }
        socketController.sendMessage(this);
    }


}