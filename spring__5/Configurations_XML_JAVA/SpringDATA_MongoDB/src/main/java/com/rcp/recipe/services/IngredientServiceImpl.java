package com.rcp.recipe.services;

import com.rcp.recipe.commands.IngredientCommand;
import com.rcp.recipe.converters.IngredientCommandToIngredient;
import com.rcp.recipe.converters.IngredientToIngredientCommand;
import com.rcp.recipe.entities.Ingredient;
import com.rcp.recipe.entities.Recipe;
import com.rcp.recipe.repositories.RecipeRepository;
import com.rcp.recipe.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by Elimane on Jan, 2018, at 12:18
 */
@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService{

            private final RecipeRepository recipeRepository;
            private final IngredientToIngredientCommand ingredientToIngredientCommand;
            private final UnitOfMeasureRepository unitOfMeasureRepository;
            private final IngredientCommandToIngredient ingredientCommandToIngredient;



    public IngredientServiceImpl(RecipeRepository recipeRepository, IngredientToIngredientCommand ingredientToIngredientCommand, UnitOfMeasureRepository unitOfMeasureRepository, IngredientCommandToIngredient ingredientCommandToIngredient) {
        this.recipeRepository = recipeRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeID, Long ingredientID) {

        //We need recipe object to get ingredient
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeID);

        //We must check if recipe has been found
        if(!recipeOptional.isPresent())
        {
            //todo impl error handling
            log.error("recipe id not found. Id: " + recipeID);
        }

        //else we get recipe
        Recipe recipe = recipeOptional.get();

        //we must use recipe to get ingredient throught a sequence of operations
        Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientID))

                //map() - lets you convert an object to something else
                //findFirst() - returns an Optional describing the first element of this stream, or an empty Optional if the stream is empty.
                .map(ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();


        if(!ingredientCommandOptional.isPresent()){
            //todo impl error handling
            log.error("Ingredient id not found: " + ingredientID);
        }

        return ingredientCommandOptional.get();

    }

    @Override
    public IngredientCommand saveIngredientCommand(IngredientCommand command) {

        Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());

        if(!recipeOptional.isPresent()){

            //todo toss error if not found!
            log.error("Recipe not found for id: " + command.getRecipeId());
            return new IngredientCommand();
        } else {
            Recipe recipe = recipeOptional.get();

            Optional<Ingredient> ingredientOptional = recipe
                    .getIngredients()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(command.getId()))
                    .findFirst();

            if(ingredientOptional.isPresent()){
                Ingredient ingredientFound = ingredientOptional.get();
                ingredientFound.setDescription(command.getDescription());
                ingredientFound.setAmount(command.getAmount());
                ingredientFound.setUom(unitOfMeasureRepository
                        .findById(command.getUom().getId())
                        .orElseThrow(() -> new RuntimeException("UOM NOT FOUND"))); //todo address this
            } else {
                //add new Ingredient
                Ingredient ingredient = ingredientCommandToIngredient.convert(command);
                ingredient.setRecipe(recipe);
                recipe.addIngredient(ingredient);
            }

            Recipe savedRecipe = recipeRepository.save(recipe);

            Optional<Ingredient> savedIngredientOptional = savedRecipe.getIngredients().stream()
                    .filter(recipeIngredients -> recipeIngredients.getId().equals(command.getId()))
                    .findFirst();

            //check by description
            if(!savedIngredientOptional.isPresent()){
                //not totally safe... But best guess
                savedIngredientOptional = savedRecipe.getIngredients().stream()
                        .filter(recipeIngredients -> recipeIngredients.getDescription().equals(command.getDescription()))
                        .filter(recipeIngredients -> recipeIngredients.getAmount().equals(command.getAmount()))
                        .filter(recipeIngredients -> recipeIngredients.getUom().getId().equals(command.getUom().getId()))
                        .findFirst();
            }

            //to do check for fail
            return ingredientToIngredientCommand.convert(savedIngredientOptional.get());
        }

    }

    @Override
    public void deleteIngredient(Long recipeId, Long idTodelete) {

        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if(recipeOptional.isPresent())
        {
            log.debug("found Recipe");
            log.debug("processing deletion of ingredient with id: "+idTodelete);

            Recipe recipe = recipeOptional.get();

            Optional<Ingredient> ingredientOptional = recipe.getIngredients().stream()
                    .filter(ingredient -> ingredient.getId().equals(idTodelete))
                    .findFirst();

            if(ingredientOptional.isPresent())
            {
                log.debug("found Ingredient");
                Ingredient ingredientTodelete = ingredientOptional.get();
                ingredientTodelete.setRecipe(null);
                recipe.getIngredients().remove(ingredientTodelete);

                recipeRepository.save(recipe);
            }
        }
        else
        {
            log.debug("Recipe Id Not found. Id:" + recipeId);
        }


    }


}

