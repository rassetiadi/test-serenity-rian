package com.rian.rassetiadi.steps;

import com.google.gson.Gson;
import com.rian.rassetiadi.api.AccountApi;
import com.rian.rassetiadi.api.BookStoreApi;
import com.rian.rassetiadi.data.BookPageData;
import com.rian.rassetiadi.model.*;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class BookStoreAPIStep {

    @Steps
    BookPageData bookPageData;

    @Steps
    AccountApi accountApi;

    @Steps
    BookStoreApi bookStoreApi;

    @Given("user login with user {} and password {}")
    public void userLoginWithUserAndPassword(String user, String password) {
        bookPageData.setToken(accountApi.loginAndGetToken(user,password));
    }

    @And("user hit get books api")
    public void userHitGetBooksApi() {
        bookPageData.setBooks(bookStoreApi.getBooks());
    }

    @Then("user can verify all of these books")
    public void userCanVerifyAllOfTheseBooks(List<Map<String, String>> input) {
        Gson gson = new Gson();
        List<Book> expectedBooks = new ArrayList<>();

        for (Map<String, String> map : input) {
            String json = gson.toJson(map);
            Book book = gson.fromJson(json, Book.class);
            expectedBooks.add(book);
        }

        List<Book> actualBooks = bookPageData.getBooks().getBooks();

        for(Book expectedBook : expectedBooks) {

            Book tempBook = null;
            for(Book actualBook : actualBooks){
                if(actualBook.getIsbn().equals(expectedBook.getIsbn())){
                    tempBook = actualBook;
                    break;
                }
            }

            assertThat("book not found "+expectedBook.getIsbn(),tempBook!=null);

            assertThat(tempBook.getIsbn(),equalTo(expectedBook.getIsbn()));
            assertThat(tempBook.getTitle(),equalTo(expectedBook.getTitle()));
            assertThat(tempBook.getSubTitle(),equalTo(expectedBook.getSubTitle()));
            assertThat(tempBook.getAuthor(),equalTo(expectedBook.getAuthor()));
            assertThat(tempBook.getPublisher(),equalTo(expectedBook.getPublisher()));
            assertThat(tempBook.getPages(),equalTo(expectedBook.getPages()));
            assertThat(tempBook.getWebsite(),equalTo(expectedBook.getWebsite()));
            assertThat(tempBook.getDescription().trim(),equalTo(expectedBook.getDescription().trim()));
            assertThat(tempBook.getPublish_date(),equalTo(expectedBook.getPublish_date()));

        }







    }

    @When("user hit post books api with list of isbns")
    public void userHitPostBooksApiWithUserIdAndListOfIsbns(List<String> isbns) {
        AddListOfBooks addListOfBooks = new AddListOfBooks();
        addListOfBooks.setUserId(bookPageData.getUserId());
        addListOfBooks.setCollectionOfIsbns(isbns.stream().map(isbn->{
            Isbn newIsbn = new Isbn();
            newIsbn.setIsbn(isbn);
            return newIsbn;
        }).toList());



        bookStoreApi.postBooks(addListOfBooks, bookPageData.getToken());

    }

    @And("user get user profile information")
    public void userGetUserProfileInformation() {
        bookPageData.setUser(accountApi.getUser(bookPageData.getToken(),bookPageData.getUserId()));
        Books books = new Books();
        books.setBooks(bookPageData.getUser().getBooks());
        bookPageData.setBooks(books);
    }



    @When("user hit delete all books api")
    public void userHitDeleteAllBooksApiWithUserId() {
        bookStoreApi.deleteAllBook(bookPageData.getUserId(), bookPageData.getToken());
    }

    @Then("user books count should be {int}")
    public void userBooksCountShouldBe(int bookCount) {
        assertThat("User still have books",bookPageData.getUser().getBooks().size(),equalTo(bookCount));
    }

    @When("user hit get books api by isbn {}")
    public void userHitGetBooksApiByIsbn(String isbn) {
        bookPageData.setBook(bookStoreApi.getBookByIsbn(isbn));
    }

    @And("user add the book to list of books")
    public void userAddTheBookToListOfBooks() {
        Books books = bookPageData.getBooks();

        if(books==null){
            books = new Books();
            books.setBooks(new ArrayList<Book>());
        }

        books.getBooks().add(bookPageData.getBook());
        bookPageData.setBooks(books);
    }

    @When("user hit delete book by isbn {}")
    public void userHitDeleteBookByIsbnWithUserIdAndIsbn(String isbn) {
        DeleteBook deleteBook = new DeleteBook();
        deleteBook.setIsbn(isbn);
        deleteBook.setUserId(bookPageData.getUserId());

        bookStoreApi.deleteBookByIsbn(deleteBook, bookPageData.getToken());

    }

    @And("user set userId with {}")
    public void userSetUserIdWith(String userId) {
        bookPageData.setUserId(userId);
    }

    @Then("user book with isbn {} existence is {}")
    public void userBookWithIsbnExistenceIsFalse(String isbn, boolean existence) {

        List<Book> listBook = bookPageData.getUser().getBooks().stream().filter(x->x.getIsbn().equals(isbn)).toList();

        if(existence){
            assertThat("book is missing",!listBook.isEmpty());
        }else{
            assertThat("book is exist",listBook.isEmpty());
        }


    }


    @When("user hit put replace book by isbn {} with {}")
    public void userHitPutReplaceBookByIsbnWith(String oldIsbn, String newIsbn) {

        DeleteBook deleteBook = new DeleteBook();
        deleteBook.setUserId(bookPageData.getUserId());
        deleteBook.setIsbn(oldIsbn);

        bookStoreApi.putReplaceBook(deleteBook, newIsbn, bookPageData.getToken());

    }
}
