package com.zhuima.springbootlearn.web;

import com.zhuima.springbootlearn.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
//import java.awt.print.Book;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
//@Controller
@RequestMapping("/v1")
public class HelloController {

//    @Autowired
//    private Book book;



//    @RequestMapping("/hello")
    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello, this is the first interface for springframework";
    }



    // 处理分页
    @GetMapping("/books")
//    @ResponseBody
    public Object getAll(@RequestParam("page") int page,
                         @RequestParam("size") int size)  {
        Map<String, String> book = new HashMap<>();
        book.put("title", "Spring Framework");
        book.put("author", "Zhuima");
        book.put("pages", "1");

        Map<String, String> book2 = new HashMap<>();
        book2.put("title", "golang Framework");
        book2.put("author", "akle");
        book2.put("pages", "1");

        List<Map> contents = new ArrayList<>();
        contents.add(book);
        contents.add(book2);

        Map<String, Object> pagemap = new HashMap<>();
        pagemap.put("page", page);
        pagemap.put("size", size);
        pagemap.put("contents", contents);
        return pagemap;
    }

    @GetMapping("/books/{id}")
    public Object getBook(@PathVariable long id) {
//        System.out.println(id);
//        Map<String, String> book = new HashMap<>();
//        book.put("title", name);
//        book.put("author", author);
//        book.put("pages", "1");
        return null;
    }

    // 接收表单数据
    @PostMapping("/books")
    public Object post(@RequestParam("title") String title,
                       @RequestParam("author") String author) {
        Map<String, String> book = new HashMap<>();
        book.put("title", title);
        book.put("author", author);
        return book;
    }
}
