package com.rcp.recipe.repositories;

import com.rcp.recipe.entities.UnitOfMeasure;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Created by Elimane on Oct, 2017, at 22:42
 * @Integration_Test is a test written by developer to demonstrate that systems differents pieces work together
 * @Unit_test is a test written and isolate which allows to a pieces of code's behaviour of our application
 * @OPTIONAL allows to encapsulate an object which can be null
 */
@RunWith(SpringRunner.class)
@DataJpaTest//Because we want to interact with Database during that test
public class UnitOfMeasureRepositoryIT {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;


    @Test
    public void findByDescription() throws Exception {

        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Cup");

        assertEquals("Cup", unitOfMeasureOptional.get().getDescription());

    }

    @Test
    public void findByDescription2() throws Exception {

        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Pinch");

        assertEquals("Pinch", unitOfMeasureOptional.get().getDescription());

    }

}