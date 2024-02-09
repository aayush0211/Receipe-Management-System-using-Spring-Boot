package com.app.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Recipe")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long recipeId;
    
    @Column(name = "title",nullable = false,length = 50)
    private String title;
    @Column(name = "description",nullable = false,length = 50)
    private String description;
    @Column(name = "ingredients",nullable = false,length = 50)
    private String ingredients;
    @Column(name ="instructions", nullable = false,length = 50)
    private String instructions;
    @Column(name ="difficultyLevel",nullable = false,length = 50)
    private String difficultyLevel;
    @Column(name ="cuisineType",nullable = false,length = 50)
    private String cuisineType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User author;
    @Column(name = "start_date",nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date creationDate;

   
}
