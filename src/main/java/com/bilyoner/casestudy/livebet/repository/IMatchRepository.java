/**
 * Copyright © casestudy - MyCompanyf0012325 2024-2024
 */

package com.bilyoner.casestudy.livebet.repository;

import com.bilyoner.casestudy.livebet.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

/**
 * ExpertiseService
 *
 * @author f0012325
 * @date 4.10.2024
 */
@Repository
@EnableJpaRepositories(basePackages = "com.bilyoner.casestudy.livebet")
public interface IMatchRepository extends JpaRepository<Match, Long> {
}
