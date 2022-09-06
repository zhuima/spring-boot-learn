package com.zhuima.springbootapi.dto;

import com.zhuima.springbootapi.domain.Book;
import com.zhuima.springbootapi.util.CustomBeanUtils;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class BookDto {

    private String author;
    private String name;
    private String description;
    private String status;

    /**
     * 转换数据对象
     * @param book
     */
    public void convertToBook(Book book) {
        new BookCovert().convert(this, book);
    }
    private class BookCovert implements Convert<BookDto, Book>{
        @Override
        public Book convert(BookDto bookDto, Book book) {
            String[] nullPropertyNames = CustomBeanUtils.getnullPropertyNames(bookDto);
            BeanUtils.copyProperties(bookDto, book, nullPropertyNames);
            return book;
        }

        @Override
        public Book convert(BookDto bookDto) {
            return null;
        }

        @Override
        public BookDto convert(Book book) {
            return null;
        }

    }
}
