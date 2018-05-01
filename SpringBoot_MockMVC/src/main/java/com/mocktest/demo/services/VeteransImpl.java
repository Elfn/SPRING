package com.mocktest.demo.services;

import com.mocktest.demo.bootstrap.PersonBootstrap;
import com.mocktest.demo.classes.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Elimane on Feb, 2018, at 13:34
 */
@Component
@Qualifier("veterans")
//@Primary
public class VeteransImpl implements IPerson {

    @Autowired
    PersonBootstrap bootstrap = PersonBootstrap.getInstance();

    @Override
    public List<Person> getAllVeterans() {
        return bootstrap.getVeterans();
    }

    @Override
    public List<Person> getAllYoungs() {
        return null;
    }
}
