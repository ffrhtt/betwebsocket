/**
 * Copyright © casestudy - MyCompanyf0012325 2024-2024
 */

package com.bilyoner.casestudy.livebet;

import com.bilyoner.casestudy.livebet.model.Coupon;
import com.bilyoner.casestudy.livebet.payload.CouponRequest;
import com.bilyoner.casestudy.livebet.repository.ICouponRepository;
import com.bilyoner.casestudy.livebet.service.CouponService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ExpertiseController
 *
 * @author f0012325
 * @date 4.10.2024
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/coupon")
@RequiredArgsConstructor
public class CouponController {
    private final ICouponRepository couponRepository;
    private final CouponService couponService;

    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody CouponRequest coupon) {
        couponService.createCoupon(coupon);
        return ResponseEntity.ok("Coupon created successfully");
    }

    // Read All
    @GetMapping
    public ResponseEntity<List<Coupon>> getAll() {
        return ResponseEntity.ok(couponRepository.findAll());
    }
}
