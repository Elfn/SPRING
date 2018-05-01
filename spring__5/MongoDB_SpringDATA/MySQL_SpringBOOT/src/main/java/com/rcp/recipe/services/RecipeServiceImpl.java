package com.rcp.recipe.services;

import com.rcp.recipe.commands.RecipeCommand;
import com.rcp.recipe.converters.RecipeCommandToRecipe;
import com.rcp.recipe.converters.RecipeToRecipeCommand;
import com.rcp.recipe.entities.Recipe;
import com.rcp.recipe.exceptions.NotFoundException;
import com.rcp.recipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Created by Elimane on Sep, 2017, at 08:26
 */
@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepo;
    private final RecipeCommandToRecipe recipeCommandToRecipe;

    private final RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipeServiceImpl(RecipeRepository recipeRepo, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepo = recipeRepo;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    @Override
    public Set<Recipe> getRecipes() {
        log.debug("I'm in the getRecipes service!!!!");
        //Unique recipes collection
        Set<Recipe> setRecipe = new HashSet<>();
        //browse list from repo by processing items(add)
        recipeRepo.findAll().iterator().forEachRemaining(setRecipe::add);

        return setRecipe;
    }

    @Override
    public Recipe findById(Long id) {

        //Optional class is to define if an object is absent or present
        //in order to avoid null pointer exception
        Optional<Recipe> recipeOptional = recipeRepo.findById(id);

        if(!recipeOptional.isPresent()) {
            throw new NotFoundException("Recipe not found, for ID value "+id.toString());
        }


        return recipeOptional.get();
    }



    @Override
    @Transactional//To say that that function is a transaction
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {
        Recipe detachedRecipe = recipeCommandToRecipe.convert(command);

        Recipe savedRecipe = recipeRepo.save(detachedRecipe);
        log.debug("Saved RecipeId:" + savedRecipe.getId());
        return recipeToRecipeCommand.convert(savedRecipe);
    }

    @Override
    @Transactional
    public RecipeCommand findCommandById(Long id) {

        return  recipeToRecipeCommand.convert(findById(id));

    }

    @Override
    public void deleteRecipe(String id) {

      recipeRepo.deleteById(Long.valueOf(id));

//        Log.debug("Recipe deleted!!!");

    }


}
