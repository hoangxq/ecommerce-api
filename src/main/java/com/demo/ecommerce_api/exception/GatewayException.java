package com.demo.ecommerce_api.exception;

import com.demo.ecommerce_api.response.ErrorResponse;
import org.springframework.http.HttpStatus;

public class GatewayException extends RuntimeException {

    private final HttpStatus status;
    private final ErrorResponse response;

    public GatewayException(HttpStatus status, ErrorResponse response) {
        this.status = status;
        this.response= response;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public ErrorResponse getResponse () {
        return response;
    }

}
