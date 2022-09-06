package com.zhuima.springbootapi.domain;


import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Data
@Entity
public class Book {

    @Id
    @GeneratedValue
    private Long id;
    @Length(min = 3)
    private String author;
    @Length(max=20)
    private String name;
    @NotBlank
    private String description;
    @NotNull
    private String status;
}
