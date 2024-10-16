/**
 * Copyright © casestudy-bilyoner - MyCompanyf0012325 2024-2024
 */

package com.bilyoner.casestudy.livebet.tools;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * MatchRateGenerator
 *
 * @author f0012325
 * @date 14.10.2024
 */
@Getter
@Setter
@Component
public class MatchRateGenerator {
    private long matchId;
    private double rateMs1;
    private double rateMs2;
    private double rateMsX;
    private double profitPercent = 17d;
    private Random random = new Random();
    public void generateMatchRate() {
        rateMs1 = (80d * random.nextDouble());
        rateMs2 = (100d - rateMs1) * random.nextDouble();
        rateMsX = (100d - (rateMs1 + rateMs2));

        rateMs1 = (double) Math.round((100d - profitPercent) / rateMs1 * 100) / 100;
        rateMs2 = (double) Math.round((100d - profitPercent) / rateMs2 * 100) / 100;
        rateMsX = (double) Math.round((100d - profitPercent) / rateMsX * 100) / 100;
    }
    @Override
    public String toString() {
        return "MatchRateGenerator{" +
                "matchId=" + matchId +
                ", rateMs1=" + rateMs1 +
                ", rateMs2=" + rateMs2 +
                ", rateMsX=" + rateMsX +
                ", profitPercent=" + profitPercent +
                '}';
    }
}
