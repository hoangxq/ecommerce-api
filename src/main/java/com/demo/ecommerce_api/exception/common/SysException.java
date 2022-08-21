package com.demo.ecommerce_api.exception.common;

import java.util.LinkedHashMap;

/**
 * thing need logging only
 * @author Admin
 *
 */
public class SysException extends ServiceException {

    private final String printMessage;
    
    /**
     * 3374372229890332024L
     */
    private static final long serialVersionUID = 3374372229890332024L;

    protected SysException(ServiceError err, Throwable ex, LinkedHashMap<String, Object> params) {
        super(err, ex, params);
        printMessage = "";
    }

    public SysException(String printMessage, Throwable ex) {
        super(ServiceError.UNEXPECTED_EXCEPTION, ex, null);
        this.printMessage = printMessage;
    }

    public SysException(String printMessage) {
        this(printMessage, null);
    }

    public String getPrintMessage() {
        return printMessage;
    }
}