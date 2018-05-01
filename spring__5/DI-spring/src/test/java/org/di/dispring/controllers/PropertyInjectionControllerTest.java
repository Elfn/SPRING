package org.di.dispring.controllers;

import org.di.dispring.services.TextProcessImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PropertyInjectionControllerTest {

    private PropertyInjectionController propertyInjection;

    @Before
    public void setUp() throws Exception {
        //Execute before class containing injection
       this.propertyInjection = new PropertyInjectionController();
        //Execute before service injected
       this.propertyInjection.process = new TextProcessImpl();
    }

    //And after execute differents classe's methods
    @Test
    public void isPalindrome() throws Exception {
        assertEquals(TextProcessImpl.IMPL_RESULT_TRUE,propertyInjection.isPalindrome("ANNA"));
    }

    @Test
    public void aposition() throws Exception {
        assertEquals("CDA",propertyInjection.Aposition("AACDA"));
    }

}