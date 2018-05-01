package org.di.dispring.controllers;

import org.di.dispring.services.TextProcessImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConstructorInjectionControllerTest {

    private ConstructorInjectionController contructorInjection;
    @Before
    public void setUp() throws Exception {

        this.contructorInjection = new ConstructorInjectionController(new TextProcessImpl());

    }

    @Test
    public void isPalindrome() throws Exception {
        assertEquals(TextProcessImpl.IMPL_RESULT_TRUE,contructorInjection.isPalindrome("ANNA"));
        assertTrue(contructorInjection.isPalindrome("ENNNNNNNNNNNNNE"));
        assertFalse(contructorInjection.isPalindrome("ANIA"));

    }

    @Test
    public void aposition() throws Exception {
        assertEquals("CC",contructorInjection.Aposition("AACC"));
    }

}