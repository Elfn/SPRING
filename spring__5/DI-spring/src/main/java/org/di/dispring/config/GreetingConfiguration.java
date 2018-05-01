package org.di.dispring.config;

import org.di.dispring.services.GreetingRepository;
import org.di.dispring.services.GreetingService;
import org.di.dispring.services.GreetingServiceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

/**
 * Created by Elimane on Oct, 2017, at 16:11
 */
@Configuration
public class GreetingConfiguration {


    @Bean
    public GreetingServiceFactory greetingServiceFactory(GreetingRepository repository)
    {
        return  new GreetingServiceFactory(repository);
    }

    @Bean
    @Profile({"en","default"})
    @Primary
    public GreetingService primaryGrettingService(GreetingServiceFactory factory)
    {
        return factory.createGreeting("en");
    }

    @Bean
    @Profile("es")
    @Primary
    public GreetingService primarySpanishGreetingService(GreetingServiceFactory factory)
    {
        return factory.createGreeting("es");
    }

    @Bean
    @Profile("ge")
    @Primary
    public GreetingService primaryGermanGreetingService(GreetingServiceFactory factory)
    {
        return factory.createGreeting("ge");
    }

}
