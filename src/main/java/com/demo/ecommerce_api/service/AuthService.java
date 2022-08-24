package com.demo.ecommerce_api.service;

import com.demo.ecommerce_api.payload.request.LoginRequest;
import com.demo.ecommerce_api.payload.request.SignupRequest;
import com.demo.ecommerce_api.payload.response.JwtRespone;

public interface AuthService {

    JwtRespone authenticateUser(LoginRequest loginRequest);

    void registerUser (SignupRequest signupRequest);

    boolean checkUser(String username);
}
