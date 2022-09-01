package com.zhuima.springbootlearn.web;

import com.zhuima.springbootlearn.domain.Book;
import com.zhuima.springbootlearn.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v2")
public class BookApp {

    @Autowired
    private BookService bookService;


    /**
     * 查询所有清单列表
     * @return
     */
    @RequestMapping("/books")
//    public List<Book> getAll(){
    public Page<Book> getAll(@RequestParam(defaultValue ="0") int page, @RequestParam(defaultValue ="10") int size){

        Sort sort = Sort.by(Sort.Direction.DESC, "id");
//        return bookService.findAll();
        return bookService.findAllByPage(PageRequest.of(page, size, sort));
    }


    /**
     * 新增一个书单信息
     * @param name
     * @param author
     * @param description
     * @param status
     * @return
     */
    @PostMapping("/books")
    public Book save(@RequestParam String name,
                     @RequestParam String author,
                     @RequestParam String description,
                     @RequestParam int status){
        Book book = new Book();
        book.setName(name);
        book.setAuthor(author);
        book.setDescription(description);
        book.setStatus(status);
        return bookService.save(book);
    }

    /**
     * 获取一条书单信息
     * @param id
     * @return
     */
    @GetMapping("/books/{id}")
    public Book findById(@PathVariable("id") long id){
        return bookService.findOne(id);
    }

    /**
     * 更新一个书单
     * @param id
     * @param name
     * @param author
     * @param description
     * @param status
     * @return
     */
    @PutMapping("/books")
    public Book update(@RequestParam long id,
                       @RequestParam String name,
                       @RequestParam String author,
                       @RequestParam String description,
                       @RequestParam int status){
        Book book = new Book();
        book.setId(id);
        book.setName(name);
        book.setAuthor(author);
        book.setDescription(description);
        book.setStatus(status);
        return bookService.save(book);
    }

    /**
     * 删除一条书单
     * @param id
     */
    @DeleteMapping("/books/{id}")
    public void delete(@PathVariable("id") long id){
        bookService.delete(id);
    }


    @PostMapping("/books/by")
    public List<Book> findBy(@RequestParam String author, @RequestParam int status){
//        return bookService.findByAuthor(author);
        return bookService.findByAuthorAndStatus(author, status);
    }

    @PostMapping("/books/search")
    public List<Book> findByNameStartsWith(@RequestParam String name){
//        return bookService.findByAuthor(author);
        return bookService.findByNameStartsWith(name);
    }

    @PostMapping("/books/update")
    public int updateByJPQL(@RequestParam int status, @RequestParam long id){
//        return bookService.findByAuthor(author);
        return bookService.updateByJPQL(status, id);
    }



}
