package com.zhuima.stage2.domain;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Book {

    @Id
    @GeneratedValue
    private Long id;
    private String author;
    private String name;
    private String description;
    private String status;
}
