package org.di.dispring.services;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by Elimane on Oct, 2017, at 08:09
 */
@Primary
@Profile("de")
@Service
public class PrimaryGermanGreetingService implements GreetingService {

  private GreetingRepository germanRepo;

    public PrimaryGermanGreetingService(GreetingRepository germanRepo) {
        this.germanRepo = germanRepo;
    }

    @Override
    public String sayGreeting() {

        return germanRepo.getGermanreeting();
    }
}
