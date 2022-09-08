package com.demo.ecommerce_api.web.rest;

import com.demo.ecommerce_api.payload.request.SignupRequest;
import com.demo.ecommerce_api.payload.response.utils.Response;
import com.demo.ecommerce_api.payload.response.utils.ResponseUtils;
import com.demo.ecommerce_api.service.AuthService;
import com.demo.ecommerce_api.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserResource {

    private final UserService userService;
    private final AuthService authService;

    @GetMapping("")
    public ResponseEntity<Response> getAllUser(){
        return ResponseUtils.ok();
    }

    @GetMapping("/{username}")
    public ResponseEntity<Response> getUserByUsername(@PathVariable(name = "username") String username){
        return ResponseUtils.ok();
    }

    @PostMapping("")
    public ResponseEntity<Response> createUser(@Valid @RequestBody SignupRequest signupRequest){
        authService.registerUser(signupRequest);
        return ResponseUtils.created();
    }

    @PutMapping("/{username}")
    public ResponseEntity<Response> updateUserByUsername(@PathVariable(name = "username") String username){
        return ResponseUtils.ok();
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Response> deleteUser(@PathVariable(name = "username") String username){
        return ResponseUtils.ok();
    }

}
