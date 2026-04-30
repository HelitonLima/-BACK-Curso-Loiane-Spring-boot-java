package com.loiane.model;


import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @NotNull
    @Length(min= 5, max = 200)
    @Column(length = 200, nullable = false)
    private String name;
    
    @NotNull
    @Length(max = 20)
    @Pattern(regexp = "BACK-END|FRONT-END")
    @Column(length = 20, nullable = false)
    private String category;
}
