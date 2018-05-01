package com.mocktest.demo.services;

import com.mocktest.demo.classes.Person;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Elimane on Feb, 2018, at 13:33
 */
@Component
public interface IPerson {

    public List<Person> getAllVeterans();
    public List<Person> getAllYoungs();

}
