/**
 * Copyright © casestudy - MyCompanyf0012325 2024-2024
 */

package com.bilyoner.casestudy.livebet.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Fetch;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Expertise
 *
 * @author f0012325
 * @date 10.10.2024
 */
@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "match")
public class Match {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String league;
    private String homeTeam;
    private String guestTeam;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "match", cascade = CascadeType.ALL)
    @Fetch(org.hibernate.annotations.FetchMode.JOIN)
    private List<Coupon> coupons = new ArrayList<>();
    private Double ms1;
    private Double ms2;
    private Double msX;
    private Date matchDate;
}
