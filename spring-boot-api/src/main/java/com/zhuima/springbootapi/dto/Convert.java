package com.zhuima.springbootapi.dto;

import com.zhuima.springbootapi.domain.Book;

public interface Convert<S, T> {
    T convert(S s, T t);
    T convert(S s);

    BookDto convert(Book book);
}
