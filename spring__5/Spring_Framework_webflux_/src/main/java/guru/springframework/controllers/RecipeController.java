package guru.springframework.controllers;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.exceptions.NotFoundException;
import guru.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.exceptions.TemplateInputException;
//import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Created by jt on 6/19/17.
 */
@Slf4j
@Controller
public class RecipeController {

    public static final String REDIRECT_RECIPE = "redirect:/recipe/";
    private static final String RECIPE_RECIPEFORM_URL = "recipe/recipeform";
    public static final String RECIPE_ID_UPDATE = "recipe/{id}/update";
    public static final String RECIPE_NEW = "recipe/new";
    public static final String RECIPE_ID_SHOW = "/recipe/{id}/show";
    public static final String RECIPE = "recipe";
    public static final String RECIPE_ID_DELETE = "recipe/{id}/delete";
    public static final String HANDLING_NOT_FOUND_EXCEPTION = "Handling not found exception";
    public static final String ERROR = "404error";
    public static final String REDIRECT = "redirect:/";

    //Add this instead of bindingresult with webflux
    private   WebDataBinder webDataBinder;




    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        this.webDataBinder = webDataBinder;
    }

    @GetMapping(RECIPE_ID_SHOW)
    public String showById(@PathVariable String id, Model model){

        model.addAttribute("recipe", recipeService.findById(id));//.block());

        return "recipe/show";
    }

    @GetMapping(RECIPE_NEW)
    public String newRecipe(Model model){
        model.addAttribute("recipe", new RecipeCommand());

        return "recipe/recipeform";
    }

    @GetMapping(RECIPE_ID_UPDATE)
    public String updateRecipe(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findCommandById(id).block());
        return RECIPE_RECIPEFORM_URL;
    }

    @PostMapping(RECIPE)
    public String saveOrUpdate(@ModelAttribute("recipe") RecipeCommand command){

        webDataBinder.validate();

        BindingResult bindingResult = webDataBinder.getBindingResult();


        if(bindingResult.hasErrors()){

            bindingResult.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });


            return RECIPE_RECIPEFORM_URL;
        }

        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command).block();

        return REDIRECT_RECIPE + savedCommand.getId() + "/show";
    }

    @GetMapping(RECIPE_ID_DELETE)
    public String deleteById(@PathVariable String id){

        log.debug("Deleting id: " + id);

        recipeService.deleteById(id);
        return REDIRECT;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NotFoundException.class, /* Thymeleaf template for handle exceptions,Exception for errors that fit response status 400 (bad request) for use in Spring Web applications ->*/ TemplateInputException.class})
    public String handleNotFound(Exception exception, Model model){

        log.error(HANDLING_NOT_FOUND_EXCEPTION);
        log.error(exception.getMessage());

        //model.setViewName(ERROR);
        model.addAttribute("exception", exception);

        return "404error";
    }

}
