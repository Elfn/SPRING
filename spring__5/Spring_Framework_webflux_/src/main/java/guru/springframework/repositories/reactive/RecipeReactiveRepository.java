package guru.springframework.repositories.reactive;

import guru.springframework.domain.Recipe;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * Created by Elimane on May, 2018, at 21:24
 */
public interface RecipeReactiveRepository extends ReactiveMongoRepository<Recipe, String> {
}
