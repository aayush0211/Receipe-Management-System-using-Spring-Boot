package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.DTO.RecipeDTO;
import com.app.model.Recipe;
import com.app.service.RecipeService;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
    @Autowired
    private RecipeService recipeService;

    @GetMapping("/{recipeId}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable Long recipeId) {
        Recipe recipe = recipeService.getRecipeById(recipeId);
        return new ResponseEntity<>(recipe, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Recipe>> getAllRecipes() {
        List<Recipe> recipes = recipeService.getAllRecipes();
        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Recipe> createRecipe(@PathVariable Long userId, @RequestBody RecipeDTO recipeDTO) {
        Recipe recipe = recipeService.createRecipe(userId, recipeDTO);
        return new ResponseEntity<>(recipe, HttpStatus.CREATED);
    }

    @PutMapping("/{recipeId}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable Long recipeId, @RequestBody RecipeDTO updatedRecipeDTO) {
        Recipe updatedRecipe = recipeService.updateRecipe(recipeId, updatedRecipeDTO);
        return new ResponseEntity<>(updatedRecipe, HttpStatus.OK);
    }

    @DeleteMapping("/{recipeId}")
    public ResponseEntity<String> deleteRecipe(@PathVariable Long recipeId) {
        recipeService.deleteRecipe(recipeId);
        return new ResponseEntity<>("Recipe deleted successfully", HttpStatus.OK);
    }
}
