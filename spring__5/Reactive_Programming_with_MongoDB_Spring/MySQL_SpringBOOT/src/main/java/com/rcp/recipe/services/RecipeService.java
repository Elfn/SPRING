package com.rcp.recipe.services;

import com.rcp.recipe.commands.RecipeCommand;
import com.rcp.recipe.entities.Recipe;

import java.util.Optional;
import java.util.Set;

/**
 * Created by Elimane on Sep, 2017, at 08:26
 */
public interface RecipeService {
   public Set<Recipe> getRecipes();
   public Recipe findById(Long id);
   RecipeCommand saveRecipeCommand(RecipeCommand command);
   public RecipeCommand findCommandById(Long id);
   public void deleteRecipe(String id);
}
