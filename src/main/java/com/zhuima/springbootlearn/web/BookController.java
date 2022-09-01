package com.zhuima.springbootlearn.web;

import com.zhuima.springbootlearn.domain.Book;
import com.zhuima.springbootlearn.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;


    /**
     * 获取书单列表
     * @param model
     * @return
     */
    @GetMapping("/books")
    public String list(Model model,
                       @PageableDefault(size = 10, sort={"id"}, direction=Sort.Direction.DESC) Pageable pageable){
//        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Page<Book> page1 = bookService.findAllByPage(pageable);
        model.addAttribute("page", page1);
        return "books";
    }


    /**
     * 获取书单详情
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/book/{id}")
    public String detail(@PathVariable long id, Model model) {
        Book book = bookService.findOne(id);
        // 针对null数据重新定义为空对象
        if (book == null) {
            book = new Book();
        }
        model.addAttribute("book", book);
       return "book";
    }

    /**
     * 跳转input提交页面
     * @return
     */
    @GetMapping("/books/input")
    public String addPage(Model model) {
        model.addAttribute("book", new Book());
        return "input";
    }


    /**
     *
     * 更新一个表单
     * @param id
     * @return
     */
    @GetMapping("/books/{id}/input")
    public String editPage(@PathVariable long id, Model model) {
        Book book = bookService.findOne(id);
        model.addAttribute("book", book);
        return "input";
    }

    /**
     * 提交一个书单
     * @param book
     * @return
     */
    @PostMapping("/books")
    public String save(Book book, final RedirectAttributes attributes) {

        Book book1 = bookService.save(book);
        if (book1 != null) {
            attributes.addFlashAttribute("message", book1.getName() );
        }
        return "redirect:/books";
    }
}
