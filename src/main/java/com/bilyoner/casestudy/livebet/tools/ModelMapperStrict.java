/**
 * Copyright © casestudy - MyCompanyf0012325 2024-2024
 */

package com.bilyoner.casestudy.livebet.tools;

import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

/**
 * ModelMapper
 *
 * @author f0012325
 * @date 5.10.2024
 */
@Component
public class ModelMapperStrict extends org.modelmapper.ModelMapper {
    public ModelMapperStrict() {
        super();
        this.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }
}
