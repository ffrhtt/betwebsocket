/**
 * Copyright © casestudy-bilyoner - MyCompanyf0012325 2024-2024
 */

package com.bilyoner.casestudy.livebet.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;

/**
 * Coupon
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
@Table(name = "coupon")
public class Coupon {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "match_id", referencedColumnName = "id")
    @JsonBackReference
    private Match match;
    private Float ms1;
    private Float msX;
    private Float ms2;
    // Kullanıcının seçtiği maç sonuçu: ms1, msX veya ms2
    private String msS;
}
