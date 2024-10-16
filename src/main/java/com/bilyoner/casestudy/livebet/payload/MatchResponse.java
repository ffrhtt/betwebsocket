/**
 * Copyright © casestudy - MyCompanyf0012325 2024-2024
 */

package com.bilyoner.casestudy.livebet.payload;

import com.bilyoner.casestudy.livebet.model.Coupon;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ExpertiseRequest
 *
 * @author f0012325
 * @date 4.10.2024
 */
@Getter
@Setter
public class MatchResponse {
    private Long id;
    private String league;
    private String homeTeam;
    private String guestTeam;
    private List<Coupon> coupons = new ArrayList<>();
    private Double ms1;
    private Double ms2;
    private Double msX;
    private Date matchDate;

}
