/**
 * Copyright © casestudy-bilyoner - MyCompanyf0012325 2024-2024
 */

package com.bilyoner.casestudy.livebet.payload;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * CouponRequest
 *
 * @author f0012325
 * @date 15.10.2024
 */
@Getter
@Setter
public class CouponRequest {
    @NotNull(message = "Maç Seçiniz")
    private Long matchId;
    @NotNull(message = "ms1 Seçiniz")
    private Double ms1;
    @NotNull(message = "msX Seçiniz")
    private Double msX;
    @NotNull(message = "ms2 Seçiniz")
    private Double ms2;
    @NotNull(message = "msS Seçiniz")
    private String msS;
    @NotNull(message = "count Seçiniz")
    private Integer count;
}
