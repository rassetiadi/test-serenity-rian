package com.rian.rassetiadi.steps;

import com.rian.rassetiadi.pages.GeneralPage;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import net.serenitybdd.annotations.Steps;
import org.springframework.beans.factory.annotation.Autowired;

public class GeneralStep {
    @Autowired
    GeneralPage generalPage;

    @Given("user open {string}")
    public void userOpen(String url) {
        generalPage.openUrl(url);
    }
}
