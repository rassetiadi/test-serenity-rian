package com.rian.rassetiadi.util;


import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

public class WebUtil extends PageObject {

    public void scrollToElement(WebElement element){
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
    }

}
