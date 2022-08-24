package com.demo.ecommerce_api.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class JwtRespone {

    private String token;
    private String type;
    private String username;
    private List<String> roles;

}
