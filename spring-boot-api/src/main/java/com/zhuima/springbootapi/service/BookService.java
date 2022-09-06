package com.zhuima.springbootapi.service;


import com.zhuima.springbootapi.domain.Book;

import java.util.List;

public interface BookService {
    List<Book> findAllBooks();

    Book getBookById(Long id);

    Book createBook(Book book);

    Book updateBook(Book book);

    void deleteBookById(Long id);

    void deleteAllBooks();
}
