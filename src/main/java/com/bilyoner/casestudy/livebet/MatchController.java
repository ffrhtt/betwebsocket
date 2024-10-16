/**
 * Copyright © casestudy - MyCompanyf0012325 2024-2024
 */

package com.bilyoner.casestudy.livebet;

import com.bilyoner.casestudy.livebet.model.Match;
import com.bilyoner.casestudy.livebet.payload.MatchResponse;
import com.bilyoner.casestudy.livebet.payload.MatchRequest;
import com.bilyoner.casestudy.livebet.service.MatchService;
import com.bilyoner.casestudy.livebet.tools.ModelMapperStrict;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * ExpertiseController
 *
 * @author f0012325
 * @date 4.10.2024
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/match")
@RequiredArgsConstructor
public class MatchController {
    private final MatchService matchService;
    private final ModelMapperStrict modelMapper;

    // Create
    @PostMapping
    public ResponseEntity<MatchResponse> createMatch(@Valid @RequestBody MatchRequest matchRequest) {
        Match createdMatch = matchService.create(matchRequest);
        return ResponseEntity.ok(modelMapper.map(createdMatch, MatchResponse.class));
    }

    // Read One
    @GetMapping("/{id}")
    public ResponseEntity<Match> get(@PathVariable Long id) {
        Optional<Match> data = matchService.get(id);
        return (data.isPresent())?  ResponseEntity.ok(data.get()) : ResponseEntity.notFound().build();
    }

    // Read One
    @GetMapping
    public ResponseEntity<List<Match>> getAll() {
        Optional<List<Match>> expertise = matchService.get();
        return (expertise.isPresent())?  ResponseEntity.ok(expertise.get()) : ResponseEntity.notFound().build();
    }
}
