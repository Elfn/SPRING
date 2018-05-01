package com.mocktest.demo.controllers;

import com.mocktest.demo.classes.Person;
import com.mocktest.demo.services.IPerson;
import com.mocktest.demo.services.VeteransImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Elimane on Feb, 2018, at 13:48
 * @Primary Indicates that a bean should be given preference when multiple candidates are qualified to autowire a single-valued dependency
 * @Qualifier  annotation along with @Autowired to remove the confusion by specifying which exact bean will be wired
 */
@RestController
public class PersonController {



    @Autowired
    @Qualifier("veterans")
    IPerson veterans = new VeteransImpl();

    @Autowired
    @Qualifier("young")
    IPerson young;

    @GetMapping("/")
    public List<String> index()
    {
        List<String> veteransNames = new ArrayList<>();

            veterans.getAllVeterans().forEach(person -> {veteransNames.add(person.getName()+" "+person.getID());});

      return veteransNames;
    }

}
