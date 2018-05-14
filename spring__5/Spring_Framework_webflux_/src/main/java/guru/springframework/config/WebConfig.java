package guru.springframework.config;

import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

/**
 * Created by Elimane on May, 2018, at 21:12
 */
@Configuration
public class WebConfig {

    //Function that handle request,...
    //Return a composed routing function that first invokes this function,
    // and then invokes the other function (of a different response type)
    // if this route had no result.
    @Bean
   public RouterFunction<?> routes(RecipeService recipeService){
        return RouterFunctions.route(GET("/api/recipes"),ServerRequest -> ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(recipeService.getRecipes(), Recipe.class));
    }

}
