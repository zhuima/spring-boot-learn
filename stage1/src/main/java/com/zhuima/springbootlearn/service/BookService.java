package com.zhuima.springbootlearn.service;

import com.zhuima.springbootlearn.domain.Book;
import com.zhuima.springbootlearn.domain.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
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
     * 分页查询书单列表
     * @return
     */
    public Page<Book> findAllByPage(Pageable pageable) {
//        Sort sort =  Sort.by(Sort.Direction.DESC, "id", "name", "status", "description");
//        int page;
//        Pageable pageable = PageRequest.of(1, 5, sort);

        return bookRepository.findAll(pageable);
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

    /**
     * 自定义更新
     * @param status
     * @param id
     * @return
     */

    public int updateByJPQL(int status, long id) {
        return bookRepository.updateByJPQL(status, id);
    }
}
