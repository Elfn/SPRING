package org.di.dispring.services;

import org.springframework.stereotype.Component;

/**
 * Created by Elimane on Oct, 2017, at 15:55
 */
@Component
public class GreetingRepositoryImpl implements GreetingRepository {
    @Override
    public String getEnglishGreeting() {
        return "Good morning";
    }

    @Override
    public String getSpanishGreeting() {
        return "Buenos dias";
    }

    @Override
    public String getGermanreeting() {
        return "guten tag";
    }
}
