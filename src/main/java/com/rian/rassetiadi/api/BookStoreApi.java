package com.rian.rassetiadi.api;

import com.google.gson.Gson;
import com.rian.rassetiadi.model.*;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import net.serenitybdd.core.steps.UIInteractions;

import static net.serenitybdd.rest.SerenityRest.given;
public class BookStoreApi {

    public Books getBooks(){
        return given().baseUri("https://demoqa.com")
                .basePath("/BookStore/v1/Books")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .get()
                .getBody()
                .as(Books.class, ObjectMapperType.GSON);
    }

    public Book getBookByIsbn(String isbn){
        return given().baseUri("https://demoqa.com")
                .basePath("/BookStore/v1/Book")
                .queryParam("ISBN",isbn)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .get()
                .getBody()
                .as(Book.class, ObjectMapperType.GSON);
    }

    public void postBooks(AddListOfBooks addListOfBooks, String token) {

        Response response = given().baseUri("https://demoqa.com")
                .basePath("/BookStore/v1/Books")
                .body(addListOfBooks, ObjectMapperType.GSON)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token)
                .post();

                response.getBody().prettyPrint();
    }

    public void deleteAllBook(String userId, String token) {
        given().baseUri("https://demoqa.com")
                .basePath("/BookStore/v1/Books")
                .queryParam("UserId",userId)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token)
                .delete()
                .getBody()
                .prettyPrint();
    }

    public void deleteBookByIsbn(DeleteBook deleteBook, String token) {

        Response response = given().baseUri("https://demoqa.com")
                .basePath("/BookStore/v1/Book")
                .body(deleteBook,ObjectMapperType.GSON)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token)
                .delete();

        response.getBody().prettyPrint();

    }

    public void putReplaceBook(DeleteBook deleteBook, String newIsbn, String token) {

        Response response = given().baseUri("https://demoqa.com")
                .basePath("/BookStore/v1/Books/{ISBN}")
                .pathParams("ISBN",newIsbn)
                .body(deleteBook,ObjectMapperType.GSON)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+token)
                .put();

        response.getBody().prettyPrint();


    }
}
