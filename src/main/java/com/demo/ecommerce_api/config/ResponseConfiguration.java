package com.demo.ecommerce_api.config;

import brave.Tracer;
import com.demo.ecommerce_api.response.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@RequiredArgsConstructor
public class ResponseConfiguration {

    private final Tracer tracer;

    @PostConstruct
    private void setTracer() {
        ResponseUtils.setTracer(tracer);
    }

}
