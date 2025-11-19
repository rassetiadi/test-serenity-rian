package com.rian.rassetiadi.model;

import lombok.Data;

import java.util.Date;

@Data
public class Book {
    String isbn;
    String title;
    String subTitle;
    String author;
    String publisher;
    String description;
    String website;
    int pages;
    Date publish_date;
}
