package com.rcp.recipe.converters;



import com.rcp.recipe.commands.CategoryCommand;
import com.rcp.recipe.entities.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;


@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {

    //Almost exactly like putting the 'synchronized' keyword on a method, except will synchronize on a private internal Object,
    // so that other code not under your control doesn't meddle with your thread management by locking on your own instance.
    @Synchronized
    @Nullable
    @Override
    public Category convert(CategoryCommand source) {
        if (source == null) {
            return null;
        }

        final Category category = new Category();
        category.setId(source.getId());
        category.setDescription(source.getDescription());
        return category;
    }
}
