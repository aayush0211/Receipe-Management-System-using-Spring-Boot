package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
