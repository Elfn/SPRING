package com.rcp.recipe.controllers;

import com.rcp.recipe.commands.RecipeCommand;
import com.rcp.recipe.exceptions.NotFoundException;
import com.rcp.recipe.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Created by Elimane on Nov, 2017, at 13:48
 */
@Controller
@Slf4j
public class RecipeController {

    private static final String RECIPE_RECIPEFORM_URL = "recipe/recipeForm";
    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
    @GetMapping("/recipe/show/{id}")
    public  String showById(@PathVariable  String id, Model m)
    {
            m.addAttribute("recipe", recipeService.findById(new Long(id)));
            return "recipe/show";
    }


    @RequestMapping("/recipe/new")
    public String newRecipe(Model model){
        model.addAttribute("recipe", new RecipeCommand());

        return RECIPE_RECIPEFORM_URL;
    }

    //Annotation that binds a method parameter or method return value to a named model attribute,
    // exposed to a web view. Supported for controller classes with @RequestMapping methods.
    //Can be used to expose command objects to a web view, using specific attribute names,
    // through annotating corresponding parameters of an @RequestMapping method.
    @PostMapping
    @RequestMapping("/recipe")
    public String saveOrUpdate(@Valid @ModelAttribute("recipe") RecipeCommand command, BindingResult bindingResult){
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);

        if(bindingResult.hasErrors())
        {
            bindingResult.getAllErrors().
                    forEach(objectError->{

                        log.debug("Validation error -> "+objectError.toString());

            });

            return RECIPE_RECIPEFORM_URL;
        }

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

//    //" ModelAndView" Holder for both Model and View in the web MVC framework.
//    // Note that these are entirely distinct.
//    // This class merely holds both to make it possible
//    // for a controller to return both model and view in a single return value
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ExceptionHandler(NotFoundException.class)
//    public ModelAndView handleNotFound(Exception exception){
//
//        log.error("Handling not found exception");
//
//        ModelAndView modelAndView = new ModelAndView();
//
//        modelAndView.setViewName("404error");
//
//        modelAndView.addObject("exception",exception);
//
//        return modelAndView;
//    }


    //Thrown to indicate that the application has attempted
    // to convert a string to one of the numeric types,
    // but that the string does not have the appropriate format.
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(NumberFormatException.class)
//    public ModelAndView handleNumberFormat(Exception exception){
//
//        log.error("Handling number format exception");
//
//        ModelAndView modelAndView = new ModelAndView();
//
//        modelAndView.setViewName("400error");
//
//        modelAndView.addObject("exception",exception);
//
//        return modelAndView;
//    }

}
