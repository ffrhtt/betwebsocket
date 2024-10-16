package com.bilyoner.casestudy.livebet;

import com.bilyoner.casestudy.livebet.model.Coupon;
import com.bilyoner.casestudy.livebet.repository.ICouponRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CouponControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ICouponRepository questionRepository;

    @InjectMocks
    private CouponController expertiseController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(expertiseController).build();
    }
    @Test
    void testGetExpertiseById_Found() throws Exception {
        // Arrange
        when(questionRepository.findAll()).thenReturn(List.of(new Coupon()));
        // Act & Assert
        mockMvc.perform(get("/api/v1/questions/read"))
                .andExpect(status().isOk());
    }
}