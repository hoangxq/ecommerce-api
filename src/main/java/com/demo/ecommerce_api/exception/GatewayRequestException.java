package com.demo.ecommerce_api.exception;



import com.demo.ecommerce_api.exception.common.BusinessException;
import com.demo.ecommerce_api.exception.common.ServiceError;

import java.util.LinkedHashMap;

public class GatewayRequestException extends BusinessException {
    public GatewayRequestException(ServiceError err, Throwable ex, LinkedHashMap<String, Object> params) {
        super(err, ex, params);
    }
}
