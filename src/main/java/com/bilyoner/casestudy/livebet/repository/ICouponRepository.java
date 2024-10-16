package com.bilyoner.casestudy.livebet.repository;

import com.bilyoner.casestudy.livebet.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICouponRepository extends JpaRepository<Coupon, Long> {
}
