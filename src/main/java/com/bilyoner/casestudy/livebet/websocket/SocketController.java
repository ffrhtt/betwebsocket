package com.bilyoner.casestudy.livebet.websocket;

import com.bilyoner.casestudy.livebet.tools.MatchRateGenerator;
import com.bilyoner.casestudy.livebet.tools.ScheduledMatchRateGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class SocketController {
    private final SimpMessagingTemplate template;

    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public OutputMessage send(Message message) throws Exception {
        return new OutputMessage(message.getFrom(), message.getText(), new Date());
    }


    public void sendMessage(ScheduledMatchRateGenerator scheduledMatchRateGenerator) {
        try {
            Map<Long, MatchRateGenerator> rateGenerators = scheduledMatchRateGenerator.getMatchRateGenerators();
            this.template.convertAndSend("/topic/bet-live-rates", rateGenerators);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
