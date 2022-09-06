package com.zhuima.stage2.controller;


import ch.qos.logback.classic.Logger;
import com.zhuima.stage2.domain.Book;
import com.zhuima.stage2.exception.BookNotFoundException;
import com.zhuima.stage2.service.BookService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/books")
public class BookController {


    @Autowired
    private BookService bookService;

    /**
     * 书单详情
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/{id}")
    public String getBook(@PathVariable long id, Model model) {
        Book book = bookService.getBookById(id);
        if (book == null) {
            throw new BookNotFoundException("书单信息不存在");
        }
        model.addAttribute("book", book);
        return "book";

    }



}
