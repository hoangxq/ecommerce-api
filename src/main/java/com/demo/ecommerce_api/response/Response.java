package com.demo.ecommerce_api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response {

    protected String source;
    protected String message;
    protected Object data;
    protected String traceId;

    public static Response of (String message, Object data) {
        return Response.builder()
                .message(message)
                .data(data)
                .build();
    }

}
