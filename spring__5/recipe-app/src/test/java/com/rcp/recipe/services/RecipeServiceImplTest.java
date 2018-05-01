package com.rcp.recipe.services;

import com.rcp.recipe.converters.RecipeCommandToRecipe;
import com.rcp.recipe.converters.RecipeToRecipeCommand;
import com.rcp.recipe.entities.Recipe;
import com.rcp.recipe.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;


import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Elimane on Oct, 2017, at 15:57
 */
public class RecipeServiceImplTest {

//    @Spy
//    RecipeServiceImpl service ;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    private  RecipeCommandToRecipe recipeCommandToRecipe;

    @Mock
    private  RecipeToRecipeCommand recipeToRecipeCommand;


    RecipeServiceImpl service;

    @Before
    public void initialization()
    {
        //RecipeRepository Mock initialization
        MockitoAnnotations.initMocks(this);

       service = new RecipeServiceImpl(recipeRepository,recipeCommandToRecipe,recipeToRecipeCommand);

    }

    @Test
    public void getRecipeByIdTest() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        //Optional.of() is used to return r under name of recipeOptional of type Optional
        Optional<Recipe> recipeOptional = Optional.of(recipe);


        //RETURN recipeOptional when recipe find by id is found(Mockito)
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        //doReturn(rcpSet).when(service).getRecipes();

        Recipe recipeReturned = service.findById(1L);

        assertNotNull("Null recipe returned", recipeReturned);

        //We want to verify if  recipeRepository has been invocated one time to call findRecipeById method (Mockito)
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();

    }

//    @Test
//    public void getRecipeByIdTest() throws Exception {
//        Recipe recipe = new Recipe();
//        recipe.setId(1L);
//        Optional<Recipe> recipeOptional = Optional.of(recipe);
//
//        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
//
//        Recipe recipeReturned = service.findById(1L);
//
//        assertNotNull("Null recipe returned", recipeReturned);
//        verify(recipeRepository, times(1)).findById(anyLong());
//        verify(recipeRepository, never()).findAll();
//    }

    @Test
    public void getRecipes() throws Exception {

        Recipe r1 = new Recipe();
       Recipe r2 = new Recipe();
        HashSet rcpSet = new HashSet();

        rcpSet.add(r1);
      rcpSet.add(r2);

        //RETURN RCPSET WHEN GETRECIPES IS CALLED(Mockito)
        when(service.getRecipes()).thenReturn(rcpSet);

        //doReturn(rcpSet).when(service).getRecipes();

        Set<Recipe> recipes = service.getRecipes();

        assertEquals(recipes.size(), 1);

        //We want to verify if  findAll() method from serviceRepository has been called only one time(Mockito)
        verify(recipeRepository, times(1)).findAll();



    }

    @Test
    public void testDeleteById() throws Exception {

        //given
        Long idToDelete = Long.valueOf(2L);

        //when
        recipeRepository.deleteById(idToDelete);

        //no 'when', since method has void return type

        //then
        verify(recipeRepository, times(1)).deleteById(anyLong());

    }
}