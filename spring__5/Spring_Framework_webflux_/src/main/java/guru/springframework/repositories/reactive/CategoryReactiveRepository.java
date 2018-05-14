package guru.springframework.repositories.reactive;

import guru.springframework.domain.Category;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

/**
 * Created by Elimane on May, 2018, at 21:24
 */
public interface CategoryReactiveRepository extends ReactiveMongoRepository<Category,String> {

    Mono<Category> findByDescription(String description);
}
