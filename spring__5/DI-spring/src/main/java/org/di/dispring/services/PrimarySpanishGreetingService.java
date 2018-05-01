package org.di.dispring.services;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by Elimane on Oct, 2017, at 08:09
 */
@Primary
@Profile("es")
@Service
public class PrimarySpanishGreetingService implements GreetingService {

   private GreetingRepository spanishRepo;

    public PrimarySpanishGreetingService(GreetingRepository spanishRepo) {
        this.spanishRepo = spanishRepo;
    }

    @Override
    public String sayGreeting() {
        return spanishRepo.getSpanishGreeting();
    }
}
