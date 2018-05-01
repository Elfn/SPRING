package com.rcp.recipe.services;

import com.rcp.recipe.commands.IngredientCommand;
import java.util.Set;

/**
 * Created by Elimane on Jan, 2018, at 12:19
 */
public interface IngredientService {

 public IngredientCommand findByRecipeIdAndIngredientId(Long recipeID, Long ingredientID);
 public IngredientCommand saveIngredientCommand(IngredientCommand command);
 public void deleteIngredient(Long recipeId, Long idTodelete);



}
