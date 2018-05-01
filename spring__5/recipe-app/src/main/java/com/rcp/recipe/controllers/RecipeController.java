package com.rcp.recipe.controllers;

import com.rcp.recipe.commands.RecipeCommand;
import com.rcp.recipe.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Elimane on Nov, 2017, at 13:48
 */
@Controller
@Slf4j
public class RecipeController {


    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
    @RequestMapping("/recipe/show/{id}")
    public  String showById(@PathVariable  String id, Model m)
    {
            m.addAttribute("recipe", recipeService.findById(new Long(id)));
            return "recipe/show";
    }


    @RequestMapping("/recipe/new")
    public String newRecipe(Model model){
        model.addAttribute("recipe", new RecipeCommand());

        return "recipe/recipeForm";
    }

    //Annotation that binds a method parameter or method return value to a named model attribute,
    // exposed to a web view. Supported for controller classes with @RequestMapping methods.
    //Can be used to expose command objects to a web view, using specific attribute names,
    // through annotating corresponding parameters of an @RequestMapping method.
    @PostMapping
    @RequestMapping("/recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand command){
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);

        return "redirect:/recipe/show/" + savedCommand.getId();
    }


    @RequestMapping("recipe/update/{id}")
    public String update(@PathVariable String id, Model model)
    {
       RecipeCommand command = recipeService.findCommandById(Long.valueOf(id));
       model.addAttribute("recipe",command);
        return "recipe/recipeForm";
    }

    @GetMapping
    @RequestMapping("recipe/delete/{id}")
    public String deleteById(@PathVariable String id){

        log.debug("Deleting id: " + id);

        recipeService.deleteRecipe(id);
        return "redirect:/";
    }

}
