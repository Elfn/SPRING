package guru.springframework.repositories;

import guru.springframework.domain.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

/**
 * Created by jt on 6/13/17.
 */
@Service
public interface RecipeRepository extends CrudRepository<Recipe, String> {
}
