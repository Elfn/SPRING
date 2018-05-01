package com.rcp.recipe.repositories;

import com.rcp.recipe.entities.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created by Elimane on Sep, 2017, at 19:22
 */
public interface CategoryRepository extends CrudRepository<Category,Long> {

    //Optional is used to wrapped an object which can be null
    Optional<Category> findByDescription(String description);
}
