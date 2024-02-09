package com.app.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name = "User")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long userId;
    
    @Column(unique = true,name="Name",nullable = false,length = 50)
    private String username;
    @Column(unique = true,name = "Email",nullable = false,length = 50)
    private String email;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonManagedReference
    private List<Recipe> recipes;

   
}
