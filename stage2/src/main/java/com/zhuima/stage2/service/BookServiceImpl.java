package com.zhuima.stage2.service;

import com.zhuima.stage2.domain.Book;
import com.zhuima.stage2.domain.BookRepository;
import com.zhuima.stage2.exception.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class BookServiceImpl implements BookService{
    @Autowired
    private BookRepository bookRepository;


    /**
     * 获取一个书单信息
     * @param id
     * @return
     */
    @Override
    public Book getBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.orElse(null);
    }
}
