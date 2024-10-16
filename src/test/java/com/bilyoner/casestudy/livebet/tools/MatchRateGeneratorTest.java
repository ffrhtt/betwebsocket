package com.bilyoner.casestudy.livebet.tools;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.*;

class MatchRateGeneratorTest {
    private MatchRateGenerator matchRateGenerator;

    @BeforeEach
    void setUp() {
        matchRateGenerator = new MatchRateGenerator();
    }

    // Happy Path: Test that the rates are generated correctly
    @RepeatedTest(1000)
    void testGenerateMatchRate_HappyPath() {
        matchRateGenerator.generateMatchRate();
        System.out.println("Rate MS1: " + matchRateGenerator.getRateMs1());
        System.out.println("Rate MS2: " + matchRateGenerator.getRateMs2());
        System.out.println("Rate MSX: " + matchRateGenerator.getRateMsX());
        System.out.println("============================================");
        assertFalse(
                (matchRateGenerator.getRateMs1() > 3  &&
                matchRateGenerator.getRateMs2() > 3 &&
                matchRateGenerator.getRateMsX() > 3),
                "Rate All should be less than or equal to 3");
    }
}
