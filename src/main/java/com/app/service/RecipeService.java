package com.app.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.DTO.RecipeDTO;
import com.app.exceptions.RecipeNotFoundException;
import com.app.exceptions.UserNotFoundException;
import com.app.model.Recipe;
import com.app.model.User;
import com.app.repository.RecipeRepository;
import com.app.repository.UserRepository;

@Service
@Transactional
public class RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private UserRepository userRepository;

    public Recipe getRecipeById(Long recipeId) {
        return recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RecipeNotFoundException("Recipe not found with id: " + recipeId));
    }

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public Recipe createRecipe(Long userId, RecipeDTO recipeDTO) {
        User author = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

        Recipe recipe = new Recipe();
        recipe.setAuthor(author);
        // set other properties from recipeDTO
        recipe.setTitle(recipeDTO.getTitle());
        recipe.setDescription(recipeDTO.getDescription());
        // set other properties

        // set creation date
        recipe.setCreationDate(new Date());

        return recipeRepository.save(recipe);
    }

    public Recipe updateRecipe(Long recipeId, RecipeDTO updatedRecipeDTO) {
        Recipe existingRecipe = getRecipeById(recipeId);

        // update fields
        existingRecipe.setTitle(updatedRecipeDTO.getTitle());
        existingRecipe.setDescription(updatedRecipeDTO.getDescription());
        // set other properties

        return recipeRepository.save(existingRecipe);
    }

    public void deleteRecipe(Long recipeId) {
        Recipe recipe = getRecipeById(recipeId);
        recipeRepository.delete(recipe);
    }
}
