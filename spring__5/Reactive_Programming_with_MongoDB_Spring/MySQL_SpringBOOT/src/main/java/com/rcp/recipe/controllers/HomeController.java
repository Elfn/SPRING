package com.rcp.recipe.controllers;

import com.rcp.recipe.entities.Category;
import com.rcp.recipe.entities.Recipe;
import com.rcp.recipe.entities.UnitOfMeasure;
import com.rcp.recipe.repositories.CategoryRepository;
import com.rcp.recipe.repositories.UnitOfMeasureRepository;
import com.rcp.recipe.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;
import java.util.Set;

@Controller
@Slf4j
public class HomeController {

//    private UnitOfMeasureRepository uomRepository;
//    private CategoryRepository categoryRepository;
    private RecipeService recipeService;

    //Dependencies Injection


    public HomeController(RecipeService recipeService) {
//        this.uomRepository = uomRepository;
//        this.categoryRepository = categoryRepository;
        this.recipeService = recipeService;
    }

//    @RequestMapping("/")
//    public String index()
//    {
//        return "redirect:index";
//    }


    @RequestMapping({"/index","/",""})
    public String index(Model m)
    {
        log.debug("This is controller!!!");
//        Optional<UnitOfMeasure> uomList = uomRepository.findByDescription("Cup");
//        Optional<Category> categoryList = categoryRepository.findByDescription("American");
        Set<Recipe> recipeList = recipeService.getRecipes();

        m.addAttribute("recipes",recipeList);

//        System.out.println("Category ID => "+categoryList.get().getId());
//        System.out.println("UOM ID => "+uomList.get().getId());

        return "index";
    }





}
