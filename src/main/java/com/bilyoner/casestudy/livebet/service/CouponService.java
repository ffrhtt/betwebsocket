/**
 * Copyright © casestudy-bilyoner - MyCompanyf0012325 2024-2024
 */

package com.bilyoner.casestudy.livebet.service;

import com.bilyoner.casestudy.livebet.model.Coupon;
import com.bilyoner.casestudy.livebet.payload.CouponRequest;
import com.bilyoner.casestudy.livebet.repository.ICouponRepository;
import com.bilyoner.casestudy.livebet.tools.ModelMapperStrict;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * CouponService
 *
 * @author f0012325
 * @date 15.10.2024
 */
@Service
@RequiredArgsConstructor
public class CouponService {
    private final PlatformTransactionManager transactionManager;
    private final Logger logger = LoggerFactory.getLogger(CouponService.class);
    private final ICouponRepository couponRepository;
    private final ModelMapperStrict modelMapper;
    private final MatchService matchService;
    @Value("${coupon.timeout}")
    private int timeOut;

    public void createCoupon(CouponRequest couponRequest) {
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        transactionDefinition.setTimeout(timeOut);
        TransactionStatus status = transactionManager.getTransaction(transactionDefinition);
        try {
            matchService.checkMatchExists(couponRequest.getMatchId());
            for (int i = 0; i < couponRequest.getCount(); i++) {
                Coupon coupon = modelMapper.map(couponRequest, Coupon.class);
                couponRepository.save(coupon);
            }
            logger.info("Coupon created successfully. Total :{} coupons created.", couponRequest.getCount());
            transactionManager.commit(status);
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }

    }

    public interface CouponParams {
    }
}
