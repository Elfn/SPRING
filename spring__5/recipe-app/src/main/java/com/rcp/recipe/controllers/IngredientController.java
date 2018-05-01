package com.rcp.recipe.controllers;


import com.rcp.recipe.commands.IngredientCommand;
import com.rcp.recipe.commands.UnitOfMeasureCommand;
import com.rcp.recipe.commands.IngredientCommand;
import com.rcp.recipe.commands.RecipeCommand;
import com.rcp.recipe.commands.UnitOfMeasureCommand;
import com.rcp.recipe.services.IngredientService;
import com.rcp.recipe.services.RecipeService;
import com.rcp.recipe.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**
 * Created by Elimane on Jan, 2018, at 05:45
 */
@Controller
@Slf4j
public class IngredientController {


    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService unitOfMeasureService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService, UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @GetMapping
    @RequestMapping("/recipe/ingredients/{recipeId}")
    public String listIngredients(@PathVariable String recipeId, Model model){
        log.debug("Getting ingredient list for recipe id: " + recipeId);

        // use command object to avoid lazy load errors in Thymeleaf.
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(recipeId)));

        return "recipe/ingredient/list";
    }

    @GetMapping
    @RequestMapping("recipe/ingredient/show/{recipeId}/{ingredientId}")
    public String showRecipeIngredient(@PathVariable String recipeId,
                                       @PathVariable String ingredientId, Model model){
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(ingredientId)));
        return "recipe/ingredient/show";
    }


    @GetMapping
    @RequestMapping("recipe/ingredient/update/{recipeId}/{ingredientId}")
    public String updateRecipeIngredient(@PathVariable String recipeId,
                                         @PathVariable String ingredientId, Model model){
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(ingredientId)));

        model.addAttribute("uomList", unitOfMeasureService.listAllUoms());

        return "recipe/ingredient/ingredientform";
    }

    @GetMapping
    @RequestMapping("recipe/ingredient/delete/{recipeId}/{ingredientId}")
    public String deleteRecipeIngredient(@PathVariable String recipeId,
                                         @PathVariable String ingredientId, Model model){

        log.debug("Deleting ingredient from recipe");
        ingredientService.deleteIngredient(Long.valueOf(recipeId),Long.valueOf(ingredientId));

        return "redirect:/recipe/ingredients/"+recipeId;
    }

    @PostMapping
    @RequestMapping("recipe/ingredient/{recipeId}")
    public String saveOrUpdate(@ModelAttribute IngredientCommand command){
        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);

        log.debug("saved receipe id:" + savedCommand.getRecipeId());
        log.debug("saved ingredient id:" + savedCommand.getId());

        return "redirect:/recipe/ingredient/show/"+ savedCommand.getRecipeId()+ "/"+ savedCommand.getId();
    }


    @GetMapping
    @RequestMapping("recipe/ingredient/new/{recipeId}")
    public String newRecipe(@PathVariable String recipeId, Model model){

        //make sure we have a good id value
        RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(recipeId));
        //todo raise exception if null

        //need to return back parent id for hidden form property
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(Long.valueOf(recipeId));
        model.addAttribute("ingredient", ingredientCommand);

        //init uom
        ingredientCommand.setUom(new UnitOfMeasureCommand());

        model.addAttribute("uomList",  unitOfMeasureService.listAllUoms());

        return "recipe/ingredient/ingredientform";
    }


}
