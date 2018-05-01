package com.rcp.recipe.controllers;

import com.rcp.recipe.entities.Recipe;
import com.rcp.recipe.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
/*
* @MOCKMVC is the main entry point for server-side Spring MVC test support.
* Perform a request and return a type that allows chaining further actions,
* such as asserting expectations, on the result.
*
* **/
/**
 * Created by Elimane on Oct, 2017, at 18:02
 */
public class HomeControllerTest {

    //Dependancy injection via Mockito
    @Mock
    RecipeService service;

    @Mock
    Model model;

   // RecipeRepository repository;

    HomeController home ;

    @Before
    public void initialization()
    {
        MockitoAnnotations.initMocks(this);

        home = new HomeController(service);

    }


    @Test
    public void index() throws Exception {


//        Mockito.verify(home, times(1)).index(model);

        //------Given
        Set<Recipe> recipes = new HashSet<>();

        Recipe r1 = new Recipe();
        Recipe r2 = new Recipe();
        r1.setId(1L);
        r2.setId(2L);
        recipes.add(r1);
        recipes.add(r2);

        //Mockito match getRecipes to recipes
        when(service.getRecipes()).thenReturn(recipes);

        //Capture recipes Set that we newly created to check its integrity
        //Use it to capture argument values for further assertions
        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        //------When
        String view  = home.index(model);

        //------Then
        assertEquals("index", view);

        //Check if model and service mocks has been called only one time during test of homeController
        verify(model, times(1)).addAttribute(eq("recipes"),argumentCaptor.capture());
        verify(service,times(1)).getRecipes();
        Set<Recipe> captRecipes = argumentCaptor.getValue();
        assertEquals(2,captRecipes.size());
    }

    @Test
    public void mockMVCTest() throws Exception{

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(home).build();

        //Perform get request to test our HomeController's action "index(model)"
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));

    }
}