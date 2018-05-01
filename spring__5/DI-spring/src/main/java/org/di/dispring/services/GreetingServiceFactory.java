package org.di.dispring.services;

/**
 * Created by Elimane on Oct, 2017, at 15:43
 */
public class GreetingServiceFactory {

        private GreetingRepository repository;

    public GreetingServiceFactory(GreetingRepository repository) {
        this.repository = repository;
    }

    public GreetingService createGreeting(String lang)
    {
        switch (lang){
            case ("en"):
                return new PrimaryGreetingService(repository);
            case ("ge"):
                return new PrimaryGermanGreetingService(repository);
            case ("es"):
                return new PrimarySpanishGreetingService(repository);
            default:
                return new PrimaryGreetingService(repository);
        }
    }
}
