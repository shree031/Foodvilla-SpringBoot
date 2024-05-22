package com.sample.foodvilla.service;

import com.sample.foodvilla.entity.Recipe;

import java.util.List;
import java.util.Optional;

public interface RecipeService {

    List<Recipe> getAllRecipes();

    Optional<Recipe> getRecipeById(Long id);

    Recipe addRecipe(Recipe recipe);

    Recipe updateRecipe(Recipe recipe);
}
