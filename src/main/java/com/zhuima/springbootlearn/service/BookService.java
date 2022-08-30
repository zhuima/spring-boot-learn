package com.zhuima.springbootlearn.service;

import com.zhuima.springbootlearn.domain.Book;
import com.zhuima.springbootlearn.domain.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    /**
     * 查询所有书单列表
     * @return
     */
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    /**
     * 新增一个书单信息
     * @param book
     * @return
     */
    public Book save(Book book) {
        return bookRepository.save(book);
    }


    /**
     * 获取一条书单信息
     * @param id
     * @return
     * 这里使用findOnew一直报错传入参数类型有问题，所以改成了findById, 参考 https://github.com/spring-guides/tut-rest/issues/53
     */
    public Book findOne(long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.orElse(null);
    }


    /**
     * 删除一条书单
     * @param id
     * @return
     */
    public void delete(long id) {
        bookRepository.deleteById(id);
    }


    /**
     * 根据Author查询一个书单列表
     * @param author
     * @return
     */
    public List<Book> findByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    /**
     * 根据AuthorandStatus查询一个书单列表
     * @param author
     * @param status
     * @return
     */
    public List<Book> findByAuthorAndStatus(String author, int status) {
        return bookRepository.findByAuthorAndStatus(author, status);
    }


    /**
     * 根据title模糊查询
     * @param name
     * @return
     */
    public List<Book> findByNameStartsWith(String name){
        return bookRepository.findByNameStartsWith(name);
    }
}
