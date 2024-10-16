/**
 * Copyright © casestudy - MyCompanyf0012325 2024-2024
 */

package com.bilyoner.casestudy.livebet.payload;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * ExpertiseRequest
 *
 * @author f0012325
 * @date 10.10.2024
 */
@Getter
@Setter
public class MatchRequest implements Serializable {
    @NotBlank(message = "Lig giriniz!")
    private String league;
    @NotBlank(message = "Ev sahibi takım adı zorunludur!")
    private String homeTeam;
    @NotBlank(message = "Deplasman takım adı zorunludur!")
    private String guestTeam;
    @NotNull(message = "Maç Tarihi zorunludur!")
    private Date matchDate;
}
