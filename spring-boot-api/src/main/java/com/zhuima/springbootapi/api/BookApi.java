package com.zhuima.springbootapi.api;

import com.zhuima.springbootapi.domain.Book;
import com.zhuima.springbootapi.dto.BookDto;
import com.zhuima.springbootapi.exception.InvalidRequestException;
import com.zhuima.springbootapi.exception.NotFoundException;
import com.zhuima.springbootapi.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BookApi {
    @Autowired
    private BookService bookService;


    /**
     * 获取书单列表
     * @return
     */
    @GetMapping("/books")
    // 对象封装实现统一返回数据结构
    public ResponseEntity<?> listAllBooks() {
        List<Book> books = bookService.findAllBooks();
        if (books.isEmpty()){
            throw new NotFoundException("Books not found");
        }
        return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
    }


    /**
     * 获取单个书单
     * @param id
     * @return
     */
    @GetMapping("/books/{id}")
    public ResponseEntity<?> getBook(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        if (book == null){
            throw new NotFoundException(String.format("book by id %s not found", id));
        }
        return new ResponseEntity<Object>(book, HttpStatus.OK);
    }


    /**
     * 创建一个书单
     * @param book
     * @return
     */
    // 默认是接受表单，使用@RequestBody 之后可以接收json数据
    @PostMapping("/books")
    public ResponseEntity<?> saveBook(@Valid @RequestBody Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidRequestException("Invalid parameters", bindingResult);
        }
        Book book1 = bookService.createBook(book);
        return new ResponseEntity<Object>(book1, HttpStatus.CREATED);
    }


    /**
     * 更新一个书单
     * @param book
     * @return
     */
    // 这里要特别注意，先查询后更新，避免是新建的情况
    @PutMapping("/books/{id}")
    public ResponseEntity<?> updateBook(@Valid @RequestBody BookDto bookDto, @PathVariable Long id, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidRequestException("Invalid parameters", bindingResult);
        }

        Book currentBook = bookService.getBookById(id);
        if (currentBook == null){
            throw new NotFoundException(String.format("book by id %s not found", id));
        }
        bookDto.convertToBook(currentBook);
        Book book1 = bookService.updateBook(currentBook);
        return new ResponseEntity<Object>(book1, HttpStatus.OK);
    }


    /**
     * 删除一个书单
     * @param id
     * @return
     */
    @DeleteMapping("/books/{id}")
    public ResponseEntity<?> deleteBookById(@PathVariable Long id) {
        bookService.deleteBookById(id);
        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }

    /**
     * 删除所有书单
     * @return
     */
    @DeleteMapping("/books")
    public ResponseEntity<?> deleteAllBooks() {
        bookService.deleteAllBooks();
        return new ResponseEntity<Object>( HttpStatus.NO_CONTENT);
    }
}
