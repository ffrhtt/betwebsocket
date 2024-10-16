package com.bilyoner.casestudy.livebet.service;

import com.bilyoner.casestudy.livebet.model.Match;
import com.bilyoner.casestudy.livebet.payload.MatchRequest;
import com.bilyoner.casestudy.livebet.repository.IMatchRepository;
import com.bilyoner.casestudy.livebet.tools.ModelMapperStrict;
import com.bilyoner.casestudy.livebet.tools.ScheduledMatchRateGenerator;
import org.hibernate.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MatchServiceTest {

    @InjectMocks
    private MatchService matchService;

    @Mock
    private IMatchRepository matchRepository;

    @Mock
    private ModelMapperStrict modelMapper;

    @Mock
    private ScheduledMatchRateGenerator scheduledMatchRateGenerator;

    private MatchRequest matchRequest;
    private Match match;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        matchRequest = new MatchRequest(); // Assuming a MatchRequest constructor
        match = new Match(); // Assuming a Match constructor
    }

    @Test
    public void testCreateMatch_HappyPath() {
        // Arrange
        when(modelMapper.map(matchRequest, Match.class)).thenReturn(match);
        when(matchRepository.save(match)).thenReturn(match);
        match.setId(1L); // Simulate an ID after saving

        // Act
        Match createdMatch = matchService.create(matchRequest);

        // Assert
        assertNotNull(createdMatch);
        assertEquals(match.getId(), createdMatch.getId());
        verify(matchRepository).save(match);
    }

    @Test
    public void testGetMatch_HappyPath() {
        // Arrange
        when(matchRepository.findById(1L)).thenReturn(Optional.of(match));

        // Act
        Optional<Match> foundMatch = matchService.get(1L);

        // Assert
        assertTrue(foundMatch.isPresent());
        assertEquals(match, foundMatch.get());
    }

    @Test
    public void testGetMatch_NotFound() {
        // Arrange
        when(matchRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ObjectNotFoundException.class, () -> matchService.get(1L));
    }

    @Test
    public void testCheckMatchExists_HappyPath() {
        // Arrange
        when(matchRepository.existsById(1L)).thenReturn(true);

        // Act & Assert
        assertDoesNotThrow(() -> matchService.checkMatchExists(1L));
    }

    @Test
    public void testCheckMatchExists_NotFound() {
        // Arrange
        when(matchRepository.existsById(1L)).thenReturn(false);

        // Act & Assert
        assertThrows(ObjectNotFoundException.class, () -> matchService.checkMatchExists(1L));
    }

    @Test
    public void testGetAllMatches_HappyPath() {
        // Arrange
        List<Match> matches = Arrays.asList(match);
        when(matchRepository.findAll()).thenReturn(matches);

        // Act
        Optional<List<Match>> result = matchService.get();

        // Assert
        assertTrue(result.isPresent());
        assertEquals(matches, result.get());
    }

    @Test
    public void testCreateMatch_EmptyRequest() {
        // Arrange
        MatchRequest emptyRequest = new MatchRequest(); // Assuming it's empty

        // Act & Assert
        assertThrows(NullPointerException.class, () -> matchService.create(emptyRequest));
    }

    // Add more edge cases as needed
}
