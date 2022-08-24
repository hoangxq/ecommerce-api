package com.demo.ecommerce_api.web.rest;

import com.demo.ecommerce_api.payload.request.LoginRequest;
import com.demo.ecommerce_api.payload.request.SignupRequest;
import com.demo.ecommerce_api.payload.response.utils.ErrorResponse;
import com.demo.ecommerce_api.payload.response.utils.Response;
import com.demo.ecommerce_api.payload.response.utils.ResponseUtils;
import com.demo.ecommerce_api.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthResource {

    private final AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<Response> authenticateUser (@Valid @RequestBody LoginRequest loginRequest){
        return ResponseUtils.ok(authService.authenticateUser(loginRequest));
    }

    @PostMapping("/signup")
    public ResponseEntity<Response> registerUser (@Valid @RequestBody SignupRequest signupRequest){
        if (authService.checkUser(signupRequest.getUsername()))
            return ResponseUtils.badRequest(new ErrorResponse(null, "Username or email is already taken !", null));
        authService.registerUser(signupRequest);
        return ResponseUtils.created();
    }

}
