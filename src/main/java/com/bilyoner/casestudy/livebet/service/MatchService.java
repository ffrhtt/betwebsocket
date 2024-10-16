/**
 * Copyright © casestudy - MyCompanyf0012325 2024-2024
 */

package com.bilyoner.casestudy.livebet.service;

import com.bilyoner.casestudy.livebet.model.Match;
import com.bilyoner.casestudy.livebet.payload.MatchRequest;
import com.bilyoner.casestudy.livebet.tools.MatchRateGenerator;
import com.bilyoner.casestudy.livebet.tools.ModelMapperStrict;
import com.bilyoner.casestudy.livebet.repository.IMatchRepository;
import com.bilyoner.casestudy.livebet.tools.ScheduledMatchRateGenerator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * ExpertiseService
 *
 * @author f0012325
 * @date 5.10.2024
 */
@Service
@RequiredArgsConstructor
public class MatchService {
    private final ModelMapperStrict modelMapper;
    private final IMatchRepository matchRepository;

    private final ScheduledMatchRateGenerator scheduledMatchRateGenerator;
    public Match create(@Valid MatchRequest matchRequest) {
        Match newMatch = modelMapper.map(matchRequest, Match.class);
        MatchRateGenerator matchRateGenerator = new MatchRateGenerator();
        matchRateGenerator.generateMatchRate();
        newMatch.setMs1(matchRateGenerator.getRateMs1());
        newMatch.setMs2(matchRateGenerator.getRateMs2());
        newMatch.setMsX(matchRateGenerator.getRateMsX());

        matchRepository.save(newMatch);
        matchRateGenerator.setMatchId(newMatch.getId());
        scheduledMatchRateGenerator.getMatchRateGenerators().putIfAbsent(
                newMatch.getId(), matchRateGenerator
        );
        return newMatch;
    }

    public Optional<Match> get(Long id) {
        return Optional.ofNullable(matchRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(Match.class, "id not found")));
    }

    public Optional<List<Match>> get() {
        return Optional.of(matchRepository.findAll());
    }

    public void checkMatchExists(Long matchId) {
        if (!matchRepository.existsById(matchId)) {
            throw new ObjectNotFoundException(Match.class, "match not found");
        }
    }
}
