package org.di.dispring.controllers;

import org.di.dispring.services.TextProcessImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GetterInjectionControllerTest {

    private GetterInjectionController getterInjection;

    @Before
    public void setUp() throws Exception {
        this.getterInjection = new GetterInjectionController();
        this.getterInjection.setProcess(new TextProcessImpl());
    }


    @Test
    public void isPalindrome() throws Exception {
        assertEquals(TextProcessImpl.IMPL_RESULT_TRUE,getterInjection.isPalindrome("ANNA"));
        assertFalse(getterInjection.isPalindrome("ANIA"));
    }

    @Test
    public void aposition() throws Exception {
        assertEquals("CDA",getterInjection.Aposition("AACDA"));


    }

}