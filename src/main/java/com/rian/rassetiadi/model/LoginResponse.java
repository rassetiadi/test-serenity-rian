package com.rian.rassetiadi.model;

import lombok.Data;

import java.util.Date;

@Data
public class LoginResponse {
    String token;
    Date expires;
    String status;
    String result;
}
