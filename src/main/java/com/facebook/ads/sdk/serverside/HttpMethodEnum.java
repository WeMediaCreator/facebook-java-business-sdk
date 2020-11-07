package com.facebook.ads.sdk.serverside;

public enum HttpMethodEnum {

    POST("POST"),
    PUT("PUT"),
    GET("GET"),
    DELETE("DELETE");

    private final String value;

    HttpMethodEnum(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

}
