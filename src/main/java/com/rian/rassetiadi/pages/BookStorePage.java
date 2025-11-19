package com.rian.rassetiadi.pages;

import com.rian.rassetiadi.util.WebUtil;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BookStorePage extends PageObject {

    @Steps
    WebUtil webUtil;

    @FindBy(xpath="//div[@class='rt-table']")
    WebElementFacade table;

    @FindBy(id="searchBox")
    WebElementFacade searchBox;

    @FindBy(id="login")
    WebElementFacade loginButton;

    @FindBy(xpath="//button[text()='Next']")
    WebElementFacade nextButton;

    @FindBy(xpath="//button[text()='Previous']")
    WebElementFacade prevButton;

    @FindBy(xpath="//select[@aria-label='rows per page']")
    WebElementFacade rowDropDown;

    @FindBy(xpath="//div[@class='rt-resizable-header-content']")
    List<WebElementFacade> columnNames;

    @FindBy(xpath = "//div[@class='pagination-bottom']")
    WebElementFacade paginationBottom;

    @FindBy(xpath = "//span[@class='-totalPages']")
    WebElementFacade totalPage;

    @FindBy(xpath = "//div[@class='rt-noData']")
    WebElementFacade tableErrorMessage;

    @FindBy(xpath="//div[@role='row']//img")
    List<WebElementFacade> displayedBook;

    @FindBy(xpath="//div[@role='rowgroup']")
    List<WebElementFacade> rows;


    public boolean isTableVisible(){
        return table.isVisible();
    }

    public Boolean isSearchBoxVisible() {
        return searchBox.isVisible();
    }

    public Boolean isLoginButtonVisible() {
        return loginButton.isVisible();
    }

    public Integer getTotalSelectedRow() {
        return Integer.parseInt(rowDropDown.getValue());
    }

    public List<String> getAllColumnName() {
        return columnNames.stream()
                .map(col->col.getText())
                .toList();
    }

    public Boolean isPaginationToolsVisible() {
        return paginationBottom.isVisible();
    }

    public void rowsDropDownSelect(String row) {
        rowDropDown.selectByValue(row);
    }

    public String getTotalPage() {
        return totalPage.getText();
    }

    public Boolean isNextButtonEnable() {
        return nextButton.isEnabled();
    }

    public Integer getTotalDisplayedBook() {
        return displayedBook.size();
    }

    public void clickNextButton() {
        nextButton.click();
    }

    public void clickPrevButton() {
        prevButton.click();
    }

    public List<Map<String, String>> getDisplayedBook() {

        List<Map<String, String>> result = new ArrayList<>();

        for(WebElementFacade row : rows){
            Map<String,String> bookInfo = new HashMap<>();


            List<WebElement> cols = row.findElements(By.xpath(".//div[@role='gridcell']"));

            bookInfo.put("Title",cols.get(1).getText());
            bookInfo.put("Author",cols.get(2).getText());
            bookInfo.put("Publisher",cols.get(3).getText());



            if(!bookInfo.get("Title").trim().isEmpty()
                    && !bookInfo.get("Author").trim().isEmpty()
                    && !bookInfo.get("Publisher").trim().isEmpty()){
                result.add(bookInfo);
            }

        }


        return result;
    }


    public void clickLink(String linkName) {
        WebElement link = getDriver().findElement(By.xpath("//a[text()='"+linkName+"']"));
        webUtil.scrollToElement(link);
        link.click();
    }

    public String getUrl() {
        return getDriver().getCurrentUrl();
    }

    public void inputSearchBox(String keyword) {
        searchBox.sendKeys(keyword);
    }

    public String getTableErrorMessage() {
        return tableErrorMessage.getText();
    }

    public void clickLoginButton() {
        loginButton.click();
    }
}
