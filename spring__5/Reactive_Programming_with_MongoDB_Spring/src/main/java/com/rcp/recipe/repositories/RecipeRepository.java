package com.rcp.recipe.repositories;

import com.rcp.recipe.commands.RecipeCommand;
import com.rcp.recipe.entities.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

/**
 * Created by Elimane on Sep, 2017, at 19:19
 */
public interface RecipeRepository extends CrudRepository<Recipe,Long> {

    //public Set<Recipe> findAll();
    //public Recipe findRecipeById(Long id);

}
