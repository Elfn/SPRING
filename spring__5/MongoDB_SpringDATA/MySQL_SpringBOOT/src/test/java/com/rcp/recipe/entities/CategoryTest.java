package com.rcp.recipe.entities;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Elimane on Oct, 2017, at 19:59
 */
public class CategoryTest {

    Category cat;

    @Before
    public  void initialization()
    {
        cat = new Category();
    }

    @Test
    public void getId() throws Exception {
        Long id = 10L;

        cat.setId(id);

        assertEquals(id , cat.getId());
    }

    @Test
    public void getDescription() throws Exception {
    }

    @Test
    public void getRecipes() throws Exception {
    }

}