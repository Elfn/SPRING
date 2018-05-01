package com.rcp.recipe.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity
@EqualsAndHashCode(exclude = {"ingredients","categories"})//Used to avoid circular references due to entities relationship
@Data//Lombok's feature to simplify a class
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String description ;
    private Integer prepTime;
    private Integer servings;
    private Integer cookTime ;
    private String source;
    private String url;
    @Lob
    private String directions;
    @Lob
    private Byte[] image ;

    //JPA Cascade Types Control how state changes are cascaded
    //from parent objects to child objects.
    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;
    //Value is defined as a string during persistence
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;
    @OneToMany(mappedBy = "recipe",cascade = CascadeType.ALL)
    private Set<Ingredient> ingredients = new HashSet<>();
    @ManyToMany
    @JoinTable(name = "recipe_category",joinColumns = @JoinColumn(name="recipe_id"),inverseJoinColumns = @JoinColumn(name="category_id"))
    private Set<Category> categories= new HashSet<>();

    //To make relationship between recipe and notes during persistence
    public void setNotes(Notes notes) {
        this.notes = notes;
        notes.setRecipe(this);
    }

    //To make relationship between recipe and ingredient during persistence
    public Recipe addIngredient(Ingredient ingredient){
        ingredient.setRecipe(this);
        ingredients.add(ingredient);
        return this;
    }


}
