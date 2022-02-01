package com.example.testrest1.connections.utils;

public class BinanceServerException extends RuntimeException{

    private final int httpStatusCode;

    public BinanceServerException(String fullErrMsg) {
        super(fullErrMsg);
        this.httpStatusCode = -1;
    }

    public BinanceServerException(String fullErrMsg, int httpStatusCode) {
        super(fullErrMsg);
        this.httpStatusCode = httpStatusCode;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }
}
