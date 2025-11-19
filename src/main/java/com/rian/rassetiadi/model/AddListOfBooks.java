package com.rian.rassetiadi.model;

import lombok.Data;

import java.util.List;

@Data
public class AddListOfBooks {
    String userId;
    List<Isbn> collectionOfIsbns;
}
