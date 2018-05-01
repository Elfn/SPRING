package org.di.dispring.services;

/**
 * Created by Elimane on Oct, 2017, at 15:57
 */
public class PrimaryGreetingService  implements GreetingService{

   private GreetingRepository englishRepo;

    public PrimaryGreetingService(GreetingRepository englishRepo) {
        this.englishRepo = englishRepo;
    }

    @Override
    public String sayGreeting() {
        return  englishRepo.getEnglishGreeting();
    }
}
