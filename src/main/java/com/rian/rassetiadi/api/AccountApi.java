package com.rian.rassetiadi.api;

import com.rian.rassetiadi.model.Login;
import com.rian.rassetiadi.model.LoginResponse;
import com.rian.rassetiadi.model.User;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import net.serenitybdd.core.steps.UIInteractions;
import org.springframework.stereotype.Component;


import static net.serenitybdd.rest.SerenityRest.given;

public class AccountApi extends UIInteractions{

    public String loginAndGetToken(String user, String password){
        Login login = new Login();
        login.setUserName(user);
        login.setPassword(password);

        LoginResponse response = given().baseUri("https://demoqa.com")
                .basePath("/Account/v1/GenerateToken")
                .body(login, ObjectMapperType.GSON)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .post()
                .getBody()
                .as(LoginResponse.class, ObjectMapperType.GSON);

        return response.getToken();

    }


    public User getUser(String token, String userId){
        Response response = given().baseUri("https://demoqa.com")
                .basePath("/Account/v1/User/"+userId)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer "+token)
                .get();

        response.getBody().prettyPrint();

        return response
            .getBody()
            .as(User.class,ObjectMapperType.GSON);
    }

}
