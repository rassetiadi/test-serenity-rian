package com.rian.rassetiadi.data;

import com.rian.rassetiadi.model.Book;
import com.rian.rassetiadi.model.Books;
import com.rian.rassetiadi.model.User;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class BookPageData {
    Books books;
    Book book;
    User user;
    String token;
    String userId;

}
