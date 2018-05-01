package org.di.dispring.services;

import org.springframework.stereotype.Service;

/**
 * Created by Elimane on Oct, 2017, at 08:09
 */
@Service
public class GreetingServiceImpl implements GreetingService {
    @Override
    public String sayGreeting() {
        return "HelloWorld";
    }
}
