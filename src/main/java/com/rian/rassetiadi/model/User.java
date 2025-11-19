package com.rian.rassetiadi.model;

import lombok.Data;

import java.util.List;

@Data
public class User {
    List<Book> books;
    String userId;
    String username;
}
