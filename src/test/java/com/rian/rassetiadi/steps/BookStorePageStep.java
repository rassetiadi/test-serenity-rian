package com.rian.rassetiadi.steps;

import com.rian.rassetiadi.pages.BookStorePage;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class BookStorePageStep {

    @Autowired
    BookStorePage bookStorePage;

    @When("ui fully loaded")
    public void uiFullyLoaded() {
        assertThat("Page not loaded",bookStorePage.isTableVisible(),equalTo(true));
    }

    @Then("user can see search box")
    public void userCanSeeSearchBox() {
        assertThat("Search box is missing",bookStorePage.isSearchBoxVisible(),equalTo(true));
    }

    @And("user can see login button")
    public void userCanSeeLoginButton() {
        assertThat("Login Button is missing",bookStorePage.isLoginButtonVisible(),equalTo(true));
    }

    @And("user can see book table")
    public void userCanSeeBookTable() {
        assertThat("Book table is missing",bookStorePage.isTableVisible(),equalTo(true));
    }

    @And("user can see default rows is {int}")
    public void userCanSeeDefaultRowsIs(int defaultRow) {
        assertThat("Default row mismatch",bookStorePage.getTotalSelectedRow(),equalTo(defaultRow));
    }

    @Then("user can see the table contains these columns")
    public void userCanSeeTheTableContainsTheseColumns(List<String> expectedColumnName) {

        List<String> actualColumnName = bookStorePage.getAllColumnName();

        for(String column : expectedColumnName){
            assertThat("missing column "+column,actualColumnName.contains(column));
        }
    }

    @And("user can see pagination tools in the botom of the page")
    public void userCanSeePaginationToolsInTheBotomOfThePage() {
        assertThat("pagination tools not exist", bookStorePage.isPaginationToolsVisible(),equalTo(true));
    }

    @Then("user click rows drop down and select {}")
    public void userClickRowsDropDownAndSelectRow(String row) {
        bookStorePage.rowsDropDownSelect(row);
    }

    @And("user can see total page should be {}")
    public void userCanSeeTotalPageShouldBeTotalPage(String totalPage) {
        assertThat("total page is wrong",bookStorePage.getTotalPage(),equalTo(totalPage));
    }

    @And("user can see next button visiblitiy is {}")
    public void userCanSeeNextButtonVisiblitiyIsNext(boolean nextButtonState) {
        assertThat("nextButton state is incorrect", bookStorePage.isNextButtonEnable(),equalTo(nextButtonState));
    }

    @And("user can see total displayed data is {}")
    public void userCanSeeTotalDisplayedDataIsTotalDisplayedData(Integer totalDisplayedData) {
        assertThat("total displayed data incorrect", bookStorePage.getTotalDisplayedBook(),equalTo(totalDisplayedData));

        if(totalDisplayedData == 0){
            assertThat("error message not shown",bookStorePage.getTableErrorMessage(),equalTo("No rows found"));
        }

    }

    @Then("user click next button")
    public void userClickNextButton() {
        bookStorePage.clickNextButton();
    }

    @Then("user can see this books")
    public void userCanSeeThisBooks(List<Map<String,String>> expectedBooks) {

        List<Map<String,String>> actualBooks = bookStorePage.getDisplayedBook();

        assertThat("Total displayed book not matched",actualBooks.size(),equalTo(expectedBooks.size()));

        for(int i=0;i<expectedBooks.size();i++){
            Map<String,String> expected = expectedBooks.get(i);
            Map<String,String> actual = actualBooks.get(i);

            for(var entry: expected.entrySet()){
                assertThat(entry.getKey()+ " not matched",expected.get(entry.getKey()),equalTo(actual.get(entry.getKey())));
            }
        }


    }

    @And("user click prev button")
    public void userClickPrevButton() {
        bookStorePage.clickPrevButton();
    }

    @And("user click book title {string}")
    public void userClickBookTitle(String linkName) {
        bookStorePage.clickLink(linkName);
    }

    @And("user redirected to {}")
    public void userRedirectedToTheBookDetailPage(String url) {
        assertThat("wrong page",bookStorePage.getUrl(),containsString(url));

    }

    @And("user fill search box with {}")
    public void userFillSearchBoxWithKeyword(String keyword) {
        bookStorePage.inputSearchBox(keyword);
    }

    @And("user click login button")
    public void userClickLoginButton() {
        bookStorePage.clickLoginButton();
    }
}
