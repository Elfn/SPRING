package com.mocktest.demo.controllers;

import com.mocktest.demo.bootstrap.PersonBootstrap;
import com.mocktest.demo.classes.Departments;
import com.mocktest.demo.classes.Person;
import com.mocktest.demo.services.IPerson;
import com.mocktest.demo.services.VeteransImpl;
import com.mocktest.demo.services.YoungImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Elimane on Feb, 2018, at 14:25
 */
//@RunWith(SpringRunner.class)
//@WebMvcTest(PersonController.class)
@ContextConfiguration(classes = {VeteransImpl.class, YoungImpl.class, PersonController.class})
public class PersonControllerTest {
//    @Autowired
//    private MockMvc mockMvc;


    //Dependancy injection via Mockito
    @Mock
    @Qualifier("veterans")
    IPerson veteransService;

    @Mock
    @Qualifier("young")
    IPerson youngsService;


    PersonController controller;

    @Mock
    PersonBootstrap bootstrap;

    MockMvc mockMvc;

    //Mocks initialization
    @Before
    public void initialization()
    {
        MockitoAnnotations.initMocks(this);

        controller = new PersonController();

        bootstrap = PersonBootstrap.getInstance();

         mockMvc = MockMvcBuilders.standaloneSetup(controller).build();


    }

    @Test
    public void index() throws Exception {


        //GIVEN
        List<Person> personList = new ArrayList<>();
        personList.add(bootstrap.createPerson("Elimane", Departments.A,45));
        personList.add(bootstrap.createPerson("Sam", Departments.B,25));
        personList.add(bootstrap.createPerson("Yann", Departments.C,32));
        personList.add(bootstrap.createPerson("Ericks", Departments.A,28));

        List<Person> veterans = new ArrayList<>();

        personList.forEach(person -> {bootstrap.addPerson(person);});

        bootstrap.getVeterans().forEach(person -> {veterans.add(person);});



        //WHEN


        //Mockito match getRecipes to recipes
        when(veteransService.getAllVeterans()).thenReturn(veterans);


        //THEN
        assertEquals(2,veterans.size());

    }


    @Test
    public void mockMVCTest() throws Exception{


        //Perform get request to test our HomeController's action "index(model)"
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        //Check if model and service mocks has been called only one time during test of homeController
        //verify(veteransService,times(0)).getAllVeterans();

        //assertEquals(mvcResult.getResponse().getContentAsString(), controller.index());

    }

}