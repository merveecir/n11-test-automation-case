package org.example.config;

public class ApiConfig {

    private final String auth = "Authorization";
    private final String token = "Bearer 6abbccefc57250e3cc80ef49e5e29402aa099545febb756c266a1c811a6653a0";
    private final String contentType = "Content-Type";
    private final String contentValue = "application/json";
    private final String accept = "Accept";
    private final String acceptValue = "application/json";

    public ApiConfig() {}
    public String getAuth() {
        return auth;
    }

    public String getToken() {
        return token;
    }

    public String getContentType() {
        return contentType;
    }

    public String getContentValue() {
        return contentValue;
    }

    public String getAccept() {
        return accept;
    }

    public String getAcceptValue() {
        return acceptValue;
    }
}
