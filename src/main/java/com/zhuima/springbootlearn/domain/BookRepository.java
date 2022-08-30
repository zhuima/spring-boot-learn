package com.zhuima.springbootlearn.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByAuthor(String author);

    List<Book> findByAuthorAndStatus(String author, int status);

    List<Book> findByNameStartsWith(String searchname);
}
