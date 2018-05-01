package com.rcp.recipe.converters;

import com.rcp.recipe.commands.CategoryCommand;
import com.rcp.recipe.entities.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Elimane on Dec, 2017, at 01:27
 */
public class CategoryCommandToCategoryTest {

    public static final Long ID_VALUE = new Long(1L);
    public static final String DESCRIPTION = "description";
    CategoryCommandToCategory converter;
    @Before
    public void setUp() throws Exception {
                converter = new CategoryCommandToCategory();
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new CategoryCommand()));
    }

    @Test
    public void convert() throws Exception {

        //GIVEn
        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(ID_VALUE);
        categoryCommand.setDescription(DESCRIPTION);

        //WHEN
        Category category = converter.convert(categoryCommand);

        //THEN
        assertEquals(category.getId(), ID_VALUE);
        assertEquals(category.getDescription(), DESCRIPTION);

    }

}